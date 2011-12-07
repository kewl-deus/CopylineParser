/*
 * Created on 14.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.sql;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AlphanumericSqlType extends SqlType
{
    private static String NAME_VARIABLE_LENGTH = "VARCHAR";

    private static String NAME_CONSTANT_LENGTH = "CHAR";

    private boolean variableLength;

    /**
     * @param name
     * @param length
     */
    public AlphanumericSqlType(int length, boolean variableLength)
    {
        super(variableLength ? NAME_VARIABLE_LENGTH : NAME_CONSTANT_LENGTH,
                length);
        this.variableLength = variableLength;
    }
    
    public AlphanumericSqlType(int length)
    {
        this(length, true);
    }

    public boolean isAlphanumeric()
    {
        return true;
    }

    /**
     * @return Returns the variableLength.
     */
    public boolean isVariableLength()
    {
        return variableLength;
    }

    /**
     * @param variableLength
     *            The variableLength to set.
     */
    public void setVariableLength(boolean variableLength)
    {
        this.variableLength = variableLength;
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.model.sql.SqlType#formatValue(java.lang.String)
     */
    public String formatValue(String value)
    {
        if (!value.startsWith("'"))
            value = "'" + value;
        if (!value.endsWith("'"))
            value = value + "'";

        return value;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(this.getName());
        buf.append("(");
        buf.append(this.getLength());
        buf.append(")");
        return buf.toString();
    }
}
