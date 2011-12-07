/*
 * Created on 05.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import copylineparser.logic.model.cobol.AlphanumericFieldType;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldValue;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;
import copylineparser.logic.model.java.JavaAttributeDefinition;
import copylineparser.logic.model.java.JavaClassDefinition;

/**
 * @author 010627
 * 
 */
public class JavaSourceGenerator extends SourceGenerator
{

    private JavaClassDefinition classDef;

    public static String convertToJavaClassName(String cobolFieldname)
    {
        String firstLetter = cobolFieldname.substring(0, 1).toUpperCase();
        return firstLetter
                + convertToJavaVarName(cobolFieldname.substring(1), false);
    }

    /*
     * Macht aus Verbindung mit '-' die JavaKonvention der GROSS-Schreibung
     */
    public static String convertToJavaVarName(String cobolFieldname,
            boolean constant)
    {
        if (constant)
        {
            return cobolFieldname.replace('-', '_').toUpperCase();
        }
        else
        {
            StringBuffer buf = new StringBuffer();
            char[] letters = cobolFieldname.toLowerCase().toCharArray();
            for (int i = 0; i < letters.length; i++)
            {
                if (letters[i] == '-')
                {
                    try
                    {
                        // überspringen, nächstes Zeichen GROSS schreiben
                        // und anhängen
                        buf.append(Character.toUpperCase(letters[++i]));
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
                else
                {
                    buf.append(letters[i]);
                }
            }
            return buf.toString();
        }
    }

    public static String getJavaRepresentation(FieldType type)
    {
        if (type instanceof AlphanumericFieldType)
        {
            return "String";
        }
        if (type instanceof NumericFieldType)
        {
            NumericFieldType numType = (NumericFieldType) type;
            if (numType.isDecimal())
            {
                return "double";
            }
            else
            {
                return "long";
            }
        }
        else
        {
            return "Object";
        }
    }

    public JavaSourceGenerator()
    {
        super();
    }

    /*
     * Erzeugt temporär einen DummyParent wenn field.parent == null
     */
    public synchronized String generateSource(Field field)
    {
        this.classDef = null;
        this.handle(field);
        return this.classDef.toString();
    }

    public JavaClassDefinition getClassDefinition()
    {
        return this.classDef;
    }

    /*
     * TODO: ArrayLength setzen!!!
     */
    protected void handle(Field field)
    {
        this.classDef = new JavaClassDefinition(convertToJavaClassName(field
                .getName()));

        List<Field> fieldsForConversion = new ArrayList<Field>();
        fetchFieldsWithoutArrays(field, fieldsForConversion);

        for (Field convField : fieldsForConversion)
        {
            if (convField.isArray())
            {
                if (convField.getSubFieldCount() == 1)
                {
                    Field subField = convField.iterator().next();
                    JavaAttributeDefinition arrayAttr = new JavaAttributeDefinition(
                            convertToJavaVarName(subField.getName(), false),
                            getJavaRepresentation(subField.getType()));
                    // arrayLength == Occurence von Gruppenfeld
                    arrayAttr.setArrayLength(convField.getOccurence());
                    this.classDef.addAttribute(arrayAttr);
                }
                else
                {
                    JavaClassDefinition arrayClass = new JavaClassDefinition(
                            convertToJavaClassName(convField.getName()));

                    List<Field> arrayClassSubFields = new ArrayList<Field>();
                    fetchElementaryFields(convField, arrayClassSubFields);

                    for (Field arrayClassSubField : arrayClassSubFields)
                    {
                        this.attachAttribute(arrayClass, arrayClassSubField);
                    }
                    // arrayClass referenzieren == Assoziation hinzufügen
                    JavaAttributeDefinition association = new JavaAttributeDefinition(
                            "my" + arrayClass.getName(), arrayClass.getName());
                    association.setArrayLength(convField.getOccurence());
                    this.classDef.addAttribute(association);
                    // arrayClassDefinition als innere Klasse hinzufügen
                    this.classDef.addInnerClass(arrayClass);
                }
            }
            else
            {
                attachAttribute(this.classDef, convField);

                if (convField.isSwitchGroup())
                {
                    for (Field switchField : convField)
                    {
                        this.attachAttribute(this.classDef, switchField);
                    }
                }
            }

        }

    }

    private void fetchFieldsWithoutArrays(Field field, List<Field> storage)
    {
        if (field.isFiller() || field.isRedefinition())
        {
            return;
        }
        if (field.isGroup() & !field.isSwitchGroup() & !field.isArray())
        {
            for (Field kid : field)
            {
                fetchFieldsWithoutArrays(kid, storage);
            }
        }
        else
        {
            storage.add(field);
        }
    }

    /*
     * sortiert aus: Filler, Redefinitionen, Gruppen liefert: alle
     * Nicht-Gruppen, aber auch Switchgruppen und ihre Switchfelder! Arrays
     * werden nicht aufgelöst, sondern nur 1x das Feld zurückgegeben
     */
    private void fetchElementaryFields(Field field, List<Field> storage)
    {
        if (field.isFiller() || field.isRedefinition())
        {
            return;
        }
        if (field.isGroup() & !field.isSwitchGroup())
        {
            for (Field kid : field)
            {
                fetchElementaryFields(kid, storage);
            }
        }
        else
        {
            storage.add(field);
        }
    }

    private void attachAttribute(JavaClassDefinition clazz, Field field)
    {
        JavaAttributeDefinition classMember = new JavaAttributeDefinition(
                convertToJavaVarName(field.getName(), field.isSwitch()),
                getJavaRepresentation(field.getType()));

        classMember.setConstant(field.isSwitch());

        this.buf = new StringBuffer();

        if (field.getDefaultValueCount() > 1)
        {
            classMember.setArrayLength(field.getDefaultValueCount());
            buf.append("{");

            Iterator<FieldValue> valIt = field.defaultValues().iterator();
            while (valIt.hasNext())
            {
                FieldValue val = valIt.next();
                this.handle(val, field.getType());
                if (valIt.hasNext())
                {
                    buf.append(", ");
                }
            }
            buf.append("}");
        }
        else if (field.getDefaultValueCount() == 1)
        {
            FieldValue val = field.defaultValues().iterator().next();
            if (val instanceof FieldValueRange)
            {
                FieldValueRange range = (FieldValueRange) val;
                long start = parseNumber(range.getStart());
                long end = parseNumber(range.getEnd());
                classMember.setArrayLength((int) (end - start));
            }
            this.handle(val, field.getType());
        }
        classMember.setValue(buf.toString());
        clazz.addAttribute(classMember);
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generation.SourceGenerationhandleor#handle(copylineparser.logic.model.AlphanumericFieldType)
     */
    protected void handle(AlphanumericFieldType type, int fieldLength)
    {
        buf.append("String");
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generation.SourceGenerationhandleor#handle(copylineparser.logic.model.NumericFieldType)
     */
    protected void handle(NumericFieldType type, int fieldLength)
    {
        if (type.isDecimal())
        {
            buf.append("double");
        }
        else
        {
            buf.append("long");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generation.SourceGenerationhandleor#handle(copylineparser.logic.model.SimpleFieldValue)
     */
    protected void handle(SimpleFieldValue value, FieldType type)
    {
        String content = value.getContent();
        if (content.equalsIgnoreCase("ZERO")
                || content.equalsIgnoreCase("ZEROES"))
        {
            buf.append("0");
            return;
        }
        else if (content.equalsIgnoreCase("SPACE")
                || content.equalsIgnoreCase("SPACES"))
        {
            buf.append("\" \"");
            return;
        }
        try
        {
            if (type.isNumericType())
            {
                buf.append(value.getContent());
            }
            else
            {
                buf.append("\"");
                buf.append(value.getContent());
                buf.append("\"");
            }
        }
        catch (NullPointerException e)
        {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generation.SourceGenerationhandleor#handle(copylineparser.logic.model.FieldValueRange)
     */
    protected void handle(FieldValueRange range, FieldType typ)
    {
        long start = parseNumber(range.getStart());
        long end = parseNumber(range.getEnd());
        buf.append("{ ");
        for (long i = start; i < end; i++)
        {
            buf.append(String.valueOf(i));
            buf.append(", ");
        }
        // end extra, weil danach kein Komma folgt
        buf.append(end);
        buf.append(" }");
    }

    private long parseNumber(SimpleFieldValue val)
    {
        String content = val.getContent();
        if (content.equalsIgnoreCase("ZERO")
                || content.equalsIgnoreCase("ZEROES"))
        {
            return 0;
        }
        else
        {
            return Long.parseLong(content);
        }
    }

}
