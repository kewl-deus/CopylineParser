/*
 * Created on 25.01.2005
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
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StringCommandGenerator extends SourceGenerator
{
    private List<Field> atomicFields = new ArrayList<Field>();
    
    private String separator;
    
    /**
     * 
     */
    public StringCommandGenerator(char separator)
    {
        super();
        this.separator = new String("\"" + separator + "\"");
    }
    
    
    /* (non-Javadoc)
     * @see copylineparser.logic.generators.SourceGenerator#generateSource(copylineparser.logic.model.cobol.Field)
     */
    public String generateSource(Field field)
    {
        this.buf = new StringBuffer();
        this.handle(field);
        this.writeHeadline();
        this.buf.append("\n\n");
        this.writeStringCommand();
        return this.buf.toString();
    }
    
    //writes in internal Buffer
    private void writeHeadline()
    {
        buf.append("STRING \n");
        Iterator<Field> it = this.atomicFields.iterator();
        while (it.hasNext())
        {
            buf.append("  ");
            buf.append("\"");
            buf.append(SqlSourceGenerator.convertToSqlName(it.next().getName()));
            buf.append("\"");
            if (it.hasNext())
            {
                buf.append(" ");
                buf.append(this.separator);
            }
            buf.append("\n");
        }
        buf.append("  DELIMITED BY SIZE\n  INTO KOPF-ZEILE\n  END-STRING");
    }
    
    private void writeStringCommand()
    {
        buf.append("STRING \n");
        for (Field field : this.atomicFields)
        {
            buf.append("  ");
            buf.append(field.getName());
            buf.append(" ");
            buf.append(this.separator);
            buf.append("\n");
        }
        buf.append("  DELIMITED BY SIZE\n  INTO CSV-ZEILE\n  END-STRING");
    }

    /*
     * extract atomic Fields
     */
    protected void handle(Field field)
    {
        if (field.isFiller() || field.isRedefinition())
        {
            return;
        }
        if (field.isGroup() & ! field.isSwitchGroup())
        {
            for (Field kid : field)
            {
                handle(kid);
            }
        }
        else
        {
            this.atomicFields.add(field);
        }
    }

    /* (non-Javadoc)
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.AlphanumericFieldType, int)
     */
    protected void handle(AlphanumericFieldType type, int fieldLength)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.NumericFieldType, int)
     */
    protected void handle(NumericFieldType type, int fieldLength)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.SimpleFieldValue, copylineparser.logic.model.cobol.FieldType)
     */
    protected void handle(SimpleFieldValue value, FieldType type)
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.FieldValueRange, copylineparser.logic.model.cobol.FieldType)
     */
    protected void handle(FieldValueRange range, FieldType type)
    {
        // TODO Auto-generated method stub

    }

}
