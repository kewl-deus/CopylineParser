/*
 * Created on 13.01.2005
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
public class SqlColumnDefinition implements Comparable<SqlColumnDefinition>
{

    protected String name;

    protected SqlType type;
    
    protected String defaultValue;
    
    protected boolean required;
    
    protected boolean primaryKey;

    public SqlColumnDefinition(String name, SqlType type)
    {
        this.name = name;
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(SqlColumnDefinition other)
    {
        return this.getName().compareTo(other.getName());
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(this.getName());
        buf.append(" ");
        buf.append(this.getType().toString());
        
        if (this.hasDefaultValue())
        {
            buf.append(" default ");
            buf.append(this.getDefaultValue());
        }
        
        return buf.toString();
    }
    
    public String toStringXML()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<column name='");
        buf.append(this.getName());
        buf.append("' primaryKey='");
        buf.append(this.isPrimaryKey());
        buf.append("' required='");
        buf.append(this.isRequired());
        buf.append("' type='");
        buf.append(this.getType().getName());
        buf.append("'");
        if (this.getType().isAlphanumeric())
        {
            buf.append(" size='");
            buf.append(this.getType().getLength());
            buf.append("'");
        }
        buf.append("/>");
        return buf.toString();
    }
    
    /**
     * @return Returns the length.
     */
    public int getLength()
    {
        return this.type.getLength();
    }
    /**
     * @param length The length to set.
     */
    public void setLength(int length)
    {
        this.type.setLength(length);
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
    /**
     * @return Returns the type.
     */
    public SqlType getType()
    {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(SqlType type)
    {
        this.type = type;
    }
    
    
    /**
     * @return Returns the defaultValue.
     */
    public String getDefaultValue()
    {
        return defaultValue;
    }
    /**
     * @param defaultValue The defaultValue to set.
     */
    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }
    
    public boolean hasDefaultValue()
    {
        if (this.defaultValue == null)
            return false;
        else
            return this.defaultValue.length() > 0;
    }
    
    
    public boolean isPrimaryKey()
    {
        return primaryKey;
    }
    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }
    public boolean isRequired()
    {
        return required;
    }
    public void setRequired(boolean required)
    {
        this.required = required;
    }
}
