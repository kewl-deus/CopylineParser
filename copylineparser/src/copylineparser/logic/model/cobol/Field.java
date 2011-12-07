/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.cobol;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Field implements Iterable<Field>
{
    public final int SWITCH_NUMBER = 88;

    private int stepNumber;

    private String name;

    private String commentary;

    private int occurence;

    private int length;

    private FieldType type;

    private Field parent;

    private Field redefinedField;

    private Vector<Field> subFields = new Vector<Field>();

    private Set<FieldValue> defaultValues = new HashSet<FieldValue>();

    private String content;

    public static Field findByPosition(Field parent, int position)
    {
        for (Field kid : parent)
        {
            int kidStartPos = kid.getStartPosition();
            if ((position >= kidStartPos)
                    && (position < kidStartPos + kid.length()))
            {
                return findByPosition(kid, position);
            }
        }
        // diese stelle wird nicht erreicht, wenn kid vorhanden!
        int startPos = parent.getStartPosition();
        if ((position >= startPos) && (position < (startPos + parent.length())))
        {
            return parent;
        }
        else
        {
            return null;
        }
    }

    public static Field findByName(Field parent, String subFieldName)
    {
        // 1. Durchgang = Oberflächliche Suche
        // == nur eigene Kindliste durchsuchen
        for (Field kid : parent)
        {
            if (kid.getName().contains(subFieldName))
            {
                return kid;
            }
        }
        // 2. Durchgang: Tiefe Suche mit Kindeskindern
        Field fieldToFind = null;
        for (Field kid : parent)
        {
            fieldToFind = findByName(kid, subFieldName);
            if (fieldToFind != null)
                return fieldToFind;
        }
        return fieldToFind;
    }

    public Field(int stepNumber, String name, String commentary, int occurence)
    {
        this.setStepNumber(stepNumber);
        this.setName(name);
        this.setCommentary(commentary);
        this.setOccurence(occurence);
    }

    public Field(int stepNumber, String name, String commentary)
    {
        this(stepNumber, name, commentary, 1);
    }

    public Field(int stepNumber, String name)
    {
        this(stepNumber, name, "");
    }

    public void setContent(String input)
    {
        int from = this.getStartPosition() - 1;
        int to = from + this.length();
        if (to > input.length())
        {
            to = input.length();
        }

        if (this.isGroup())
        {
            for (Field kid : this)
            {
                kid.setContent(input);
            }

            if (this.isSwitchGroup())
            {
                this.content = input.substring(from, to);
            }
            else
            {
                this.content = "";
            }
        }
        else
        {
            if (this.isSwitch())
            {
                String valToCompare = input.substring(from, to);
                this.content = "FALSE";
                for (FieldValue val : this.defaultValues())
                {
                    if (val.equals(valToCompare))
                    {
                        this.content = "TRUE";
                        break;
                    }
                }
            }
            else
            {
                this.content = input.substring(from, to);
            }
        }

        // System.out.println(this.name + " = " + this.content + "(" + from +
        // ":"
        // + to + ")");

    }

    /**
     * @param groupsRecursive
     *            Holt bei Gruppen den Inhalt rekursiv über untergeordnete
     *            Felder, Ausnahme: Schalter(-Gruppen)
     * @return
     */
    public String getContent(boolean groupsRecursive)
    {
        String returnContent = this.content;
        if (groupsRecursive & this.isGroup())
        {
            if (!this.isSwitchGroup())
            {
                StringBuffer buf = new StringBuffer();
                for (Field child : this)
                {
                    buf.append(child.getContent(true));
                }
                returnContent = buf.toString();
            }
        }

        if (returnContent == null)
        {
            returnContent = "";
        }
        return returnContent;
    }

    /**
     * @return Startposition dieses Feldes in der Copystrecke, d.h. die Länge
     *         der Copystrecke bis dieses Feld anfängt
     */
    public int getStartPosition()
    {
        int pos = 1;
        Field field = this;
        while (field.hasParent())
        {
            if (field.isRedefinition())
            {
                field = field.getRedefinedField();
            }
            for (Field prevField : field.getDirectPreviousFields())
            {
                if (!(prevField.isSwitch() || prevField.isRedefinition()))

                {
                    pos += prevField.length();
                }
            }
            field = field.getParent();
        }
        return pos;
    }

    private List<Field> getDirectPreviousFields()
    {
        try
        {
            return parent.subFields.subList(parent.subFields
                    .indexOf(parent.subFields.firstElement()), parent.subFields
                    .indexOf(this));
        }
        // IndexOutOfBoundsException, IllegalArgumentException,
        // NullPointerException (= no Parent)
        catch (Exception e)
        {
            return new Vector<Field>();
        }
    }

    public void setRedefinition(Field redefinedField)
    {
        this.redefinedField = redefinedField;
    }

    public boolean isRedefinition()
    {
        return this.redefinedField != null;
    }

    public Field getRedefinedField()
    {
        return this.redefinedField;
    }

    public void addDefaultValues(FieldValue[] values)
    {
        for (FieldValue val : values)
        {
            this.addDefaultValue(val);
        }
    }

    public void addDefaultValues(Collection<FieldValue> col)
    {
        this.defaultValues.addAll(col);
    }

    public void addDefaultValue(FieldValue value)
    {
        this.defaultValues.add(value);
    }

    public boolean hasDefaultValue()
    {
        return !this.defaultValues.isEmpty();
    }

    public int getDefaultValueCount()
    {
        return this.defaultValues.size();
    }

    public void removeDefaultValues()
    {
        this.defaultValues.clear();
    }

    public Iterable<FieldValue> defaultValues()
    {
        return this.defaultValues;
    }

    public int getStepNumber()
    {
        return this.stepNumber;
    }

    public boolean hasCommentary()
    {
        if (this.getCommentary() == null)
            return false;
        else
            return this.getCommentary().length() > 0;
    }

    public String getCommentary()
    {
        return commentary;
    }

    public Field getParent()
    {
        return parent;
    }

    public void setParent(Field parent)
    {
        this.parent = parent;
    }

    public boolean hasParent()
    {
        return this.parent != null;
    }

    public void removeSubFields()
    {
        this.subFields.clear();
    }

    public void remove(Field subField)
    {
        this.subFields.remove(subField);
    }

    public String getName()
    {
        return name;
    }

    public void setCommentary(String string)
    {
        commentary = string;
    }

    public void setName(String string)
    {
        name = string;
    }

    public void setStepNumber(int i)
    {
        stepNumber = i;
    }

    public int getOccurence()
    {
        return occurence;
    }

    public boolean setOccurence(int oc)
    {
        if (oc > 0)
        {
            this.occurence = oc;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isArray()
    {
        return this.occurence > 1;
    }

    public int getSubFieldCount()
    {
        return this.subFields.size();
    }

    public FieldType getType()
    {
        if (this.isSwitch())
            return parent.getType();

        return type;
    }

    public void setType(FieldType type)
    {
        this.type = type;
    }

    public boolean isSwitch()
    {
        return this.getStepNumber() == SWITCH_NUMBER;
    }

    // public int length()
    // {
    // if (this.isSwitch())
    // {
    // return this.parent.length();
    // }
    // else if (this.length <= 0)
    // {
    // return this.calculateLength();
    // }
    // else
    // {
    // return this.length;
    // }
    // }

    public int length()
    {
        if (this.isSwitch())
        {
            return this.parent.length();
        }
        else if (this.length <= 0)
        {
            return this.calculateLength();
        }
        else
        {
            int len = this.length;

            if (this.getType() instanceof NumericFieldType)
            {
                NumericFieldType nft = (NumericFieldType) this.getType();
                if (nft.isSigned() && nft.isPrintedSymbol(nft.getSignSymbol()))
                {
                    len++;
                }
                if (nft.isDecimal()
                        && nft.isPrintedSymbol(nft.getCommaSymbol()))
                {
                    len++;
                }
            }
            return len * this.occurence;
        }
    }

    private int calculateLength()
    {
        int length = 0;
        for (Field f : this)
        {
            if (!(f.isSwitch() || f.isRedefinition()))
            {
                length += f.length();
            }
        }

        length *= this.occurence;

        return length;
    }

    public int sizeInBytes()
    {
        if (this.getType() instanceof NumericFieldType)
        {
            NumericFieldType nft = (NumericFieldType) this.getType();
            if (nft.isPacked())
            {
                // mit 10 multiplizieren um mit int zu rechnen, statt float
                int packedLength = this.length * 10;
                // gepackt wird nur die Hälfte Platz benötigt
                packedLength >>= 1; // durch 2
                // Halb-Byte für Vorzeichen bzw. Endezeichen aufaddieren
                packedLength += 5;
                // wenn ungerade (nicht durch 2 teilbar)
                // noch ein leeres Halb-Byte aufaddieren
                if ((packedLength & 0x01) == 1)
                {
                    // true == nicht durch 2 teilbar
                    // weil niedrigstes bit=1
                    packedLength += 5;
                }
                // rücknahme des 10er-Faktors
                packedLength /= 10;
                return packedLength;
            }
            else
            {
                return this.length();
            }
        }
        else
        {
            return this.length();
        }
    }

    public void setLength(int len)
    {
        if (len > 0)
        {
            this.length = len;
        }
    }

    public String toString()
    {
        return this.getName();
    }

    public boolean isSwitchGroup()
    {
        for (Field f : this)
        {
            if (f.isSwitch())
            {
                return true;
            }
        }
        return false;
    }

    public boolean isGroup()
    {
        return !this.subFields.isEmpty();
    }

    public boolean isFiller()
    {
        return this.getName().equalsIgnoreCase("FILLER");
    }

    public void add(Field f)
    {
        this.subFields.add(f);
    }

    public Iterator<Field> iterator()
    {
        return this.subFields.iterator();
    }

    public Field[] getSubFields()
    {
        return this.subFields.toArray(new Field[this.subFields.size()]);
    }

    public SortedSet<Field> getSubFieldsSortByName()
    {
        TreeSet<Field> fieldSet = new TreeSet<Field>(new Comparator<Field>()
        {
            public int compare(Field f1, Field f2)
            {
                return f1.getName().compareTo(f2.getName());
            }
        });

        fieldSet.addAll(this.subFields);

        return fieldSet;
    }
}
