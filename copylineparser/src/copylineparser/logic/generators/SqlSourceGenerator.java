/*
 * Created on 13.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import copylineparser.Copyline;
import copylineparser.logic.model.cobol.AlphanumericFieldType;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldValue;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;
import copylineparser.logic.model.sql.AlphanumericSqlType;
import copylineparser.logic.model.sql.NumericSqlType;
import copylineparser.logic.model.sql.SqlColumnDefinition;
import copylineparser.logic.model.sql.SqlTableDefinition;
import copylineparser.logic.model.sql.SqlType;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SqlSourceGenerator extends SourceGenerator
{
    private SqlTableDefinition tableDef;

    private Hashtable<Field, SqlColumnDefinition> fieldToColumnTable = new Hashtable<Field, SqlColumnDefinition>();

    public static String convertToSqlName(String cobolFieldname)
    {
        return cobolFieldname.replace('-', '_').toUpperCase();
    }

    public SqlSourceGenerator()
    {
        super();
    }

    public String generateInsertStatements(Collection<Copyline> copylines,
            Field copylineRootField)
    {
        this.fieldToColumnTable.clear();
        this.tableDef = null;
        this.handle(copylineRootField);
        
        
        //Felder namentlich sortieren
        List<Field> elementaryFields = new ArrayList<Field>(
                this.fieldToColumnTable.keySet());

        Collections.sort(elementaryFields, new Comparator<Field>()
        {
            public int compare(Field f1, Field f2)
            {
                return f1.getName().compareToIgnoreCase(f2.getName());
            }

        });

        StringBuffer tmp = new StringBuffer();
        tmp.append("INSERT INTO ");
        tmp.append(this.tableDef.getName());
        tmp.append("(");
        tmp.append(this.tableDef.generateSeparatedColumnNames(","));
        tmp.append(")");
        tmp.append(" VALUES (");

        String stmtBeginning = tmp.toString();
        String stmtEnding = ");\n";

        StringBuffer stmt = new StringBuffer();

        for (Copyline copyline : copylines)
        {
            stmt.append(stmtBeginning);
            copylineRootField.setContent(copyline.getContent());
            Iterator<Field> fieldIt = elementaryFields.iterator();
            while (fieldIt.hasNext())
            {
                Field f = fieldIt.next();
                SqlType type = this.fieldToColumnTable.get(f).getType();
                stmt.append(type.formatValue(f.getContent(false)));
                if (fieldIt.hasNext())
                    stmt.append(",");
            }

            stmt.append(stmtEnding);
        }

        return stmt.toString();
    }

    public SqlTableDefinition getTableDefinition()
    {
        return this.tableDef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#generateSource(copylineparser.logic.model.cobol.Field)
     */
    public String generateSource(Field field)
    {
        this.handle(field);
        return this.tableDef.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.Field)
     */
    protected void handle(Field field)
    {
        this.tableDef = new SqlTableDefinition(
                convertToSqlName(field.getName()));

        List<Field> elementaryFields = new ArrayList<Field>();
        fetchFields(field, elementaryFields);

        for (Field elemField : elementaryFields)
        {
            SqlColumnDefinition col = this.getSqlRepresentation(elemField);

            if (elemField.hasDefaultValue())
            {
                FieldValue firstValue = elemField.defaultValues().iterator()
                        .next();
                if (firstValue instanceof SimpleFieldValue)
                {
                    SimpleFieldValue simpleFirstValue = (SimpleFieldValue) firstValue;
                    col.setDefaultValue(col.getType().formatValue(
                            simpleFirstValue.getContent()));
                }
            }

            // an Tabelle binden
            this.tableDef.addColumn(col);

            // mapping speichern
            this.fieldToColumnTable.put(elemField, col);
        }

    }

    public SqlColumnDefinition getSqlRepresentation(Field field)
    {
        FieldType cobType = field.getType();
        if (cobType == null & field.isGroup())
        {
            cobType = new AlphanumericFieldType();
        }

        SqlType sqlType = null;
        if (cobType.isNumericType())
        {
            NumericFieldType numType = (NumericFieldType) cobType;
            sqlType = new NumericSqlType(field.length()
                    - numType.getPostCommaLength(), numType
                    .getPostCommaLength());
        }
        else
        {
            sqlType = new AlphanumericSqlType(field.length());
        }

        return new SqlColumnDefinition(convertToSqlName(field.getName()),
                sqlType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.AlphanumericFieldType,
     *      int)
     */

    protected void handle(AlphanumericFieldType type, int fieldLength)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.NumericFieldType,
     *      int)
     */
    protected void handle(NumericFieldType type, int fieldLength)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.SimpleFieldValue,
     *      copylineparser.logic.model.cobol.FieldType)
     */
    protected void handle(SimpleFieldValue value, FieldType type)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.FieldValueRange,
     *      copylineparser.logic.model.cobol.FieldType)
     */
    protected void handle(FieldValueRange range, FieldType type)
    {
        // TODO Auto-generated method stub

    }
    
    /*
     * sortiert aus: Filler, Redefinitionen, Gruppen liefert: alle
     * Nicht-Gruppen, aber auch Switchgruppen und ihre Switchfelder! Arrays
     * werden nicht aufgelöst, sondern nur 1x das Feld zurückgegeben
     */
    private void fetchFields(Field field, List<Field> storage)
    {
        if (field.isFiller() || field.isRedefinition())
        {
            return;
        }
        if (field.isGroup() & !field.isSwitchGroup())
        {
            for (Field kid : field)
            {
                fetchFields(kid, storage);
            }
        }
        else
        {
            storage.add(field);
        }
    }

}
