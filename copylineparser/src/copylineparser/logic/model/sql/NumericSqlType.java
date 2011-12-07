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
public class NumericSqlType extends SqlType
{
    private static String NAME_INTEGER = "INT";

    private static String NAME_DECIMAL = "DECIMAL";

    private int preCommaLength;

    private int postCommaLength;

    /**
     * @param name
     * @param length
     */
    public NumericSqlType(int preCommaLength, int postCommaLength)
    {
        super(postCommaLength == 0 ? NAME_INTEGER : NAME_DECIMAL, preCommaLength + postCommaLength);
        this.preCommaLength = preCommaLength;
        this.postCommaLength = postCommaLength;
    }

    public boolean isNumeric()
    {
        return true;
    }

    public boolean isDecimal()
    {
        return this.postCommaLength > 0;
    }

    public boolean isInteger()
    {
        return this.postCommaLength == 0;
    }

    /**
     * @return Returns the postCommaLength.
     */
    public int getPostCommaLength()
    {
        return postCommaLength;
    }

    /**
     * @param postCommaLength
     *            The postCommaLength to set.
     */
    public void setPostCommaLength(int postCommaLength)
    {
        if (postCommaLength < 0)
            return;

        this.postCommaLength = postCommaLength;
        super.setLength(this.getPreCommaLength() + this.getPostCommaLength());

        if (this.postCommaLength == 0)
            this.setName(NAME_INTEGER);
        else
            this.setName(NAME_DECIMAL);
    }

    /**
     * @return Returns the preCommaLength.
     */
    public int getPreCommaLength()
    {
        return preCommaLength;
    }

    /**
     * @param preCommaLength
     *            The preCommaLength to set.
     */
    public void setPreCommaLength(int preCommaLength)
    {
        if (preCommaLength <= 0)
            return;

        this.preCommaLength = preCommaLength;
        super.setLength(this.getPreCommaLength() + this.getPostCommaLength());
    }

    /*
     * (non-Javadoc)
     * 
     * @see copylineparser.logic.model.sql.SqlType#formatValue(java.lang.String)
     */
    public String formatValue(String value)
    {
        StringBuffer buf = new StringBuffer();
        char[] ca = value.toCharArray();
        for (int i = 0; i < ca.length; i++)
        {
            if (Character.isDigit(ca[i]))
                buf.append(ca[i]);
        }
        
        
        if (this.isDecimal())
        {
            int commaPos;
            commaPos = value.indexOf(",");
            if (commaPos == -1) commaPos = value.indexOf(".");
            if (commaPos >= 0)
            {
                buf.insert(commaPos, '.');
            }
        }
        
        return buf.toString();

    }
    
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(this.getName());
        buf.append("(");
        if (this.isDecimal())
        {
            buf.append(this.getPreCommaLength());
            buf.append(":");
            buf.append(this.getPostCommaLength());
        }
        else
        {
            buf.append(this.getLength());
        }
        buf.append(")");
        
        return buf.toString();
    }
}
