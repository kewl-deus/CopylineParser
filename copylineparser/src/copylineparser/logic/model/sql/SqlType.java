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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class SqlType
{
    private String name;
    private int length;
    
    
    
    /**
     * @param name
     * @param length
     */
    public SqlType(String name, int length)
    {
        super();
        this.name = name;
        this.length = length;
    }
    
    public abstract String formatValue(String value);
    
    public abstract String toString();
    
    
    /**
     * @return Returns the length.
     */
    public int getLength()
    {
        return length;
    }
    /**
     * @param length The length to set.
     */
    public void setLength(int length)
    {
        if (length > 0)
            this.length = length;
    }
    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isAlphanumeric()
    {
        return false;
    }
    
    public boolean isNumeric()
    {
        return false;
    }
}
