/*
 * Created on 25.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.generators;

import copylineparser.logic.model.cobol.AlphanumericFieldType;
import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.FieldValueRange;
import copylineparser.logic.model.cobol.NumericFieldType;
import copylineparser.logic.model.cobol.SimpleFieldValue;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CopyCommandGenerator extends SourceGenerator
{

    private String sourceNamespace;

    private String targetNamespace;

    public CopyCommandGenerator(String sourceNamespace, String targetNamespace)
    {
        super();
        this.sourceNamespace = sourceNamespace;
        this.targetNamespace = targetNamespace;
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#generateSource(copylineparser.logic.model.cobol.Field)
     */
    public String generateSource(Field field)
    {
        this.buf = new StringBuffer();
        this.handle(field);
        return this.buf.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.generators.SourceGenerator#handle(copylineparser.logic.model.cobol.Field)
     */
    protected void handle(Field field)
    {
        if (field.isFiller() || field.isRedefinition())
        {
            return;
        }
        if (field.isGroup() && !field.isSwitchGroup())
        {
            for (Field kid : field)
            {
                handle(kid);
            }
        }
        else
        {
            buf.append("MOVE ");
            buf.append(field.getName());
            buf.append(" OF ");
            buf.append(sourceNamespace);
            buf.append("\n  ");
            buf.append(" TO ");
            buf.append(field.getName());
            buf.append(" OF ");
            buf.append(targetNamespace);
            buf.append("\n");
        }

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

}
