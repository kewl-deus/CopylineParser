/*
 * Created on 11.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.java;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JavaAttributeDefinition implements
        Comparable<JavaAttributeDefinition>
{

    static final String ARRAY_CONTAINS_NUMBER_METHOD = "\tpublic boolean contains(long[] array, long value)\n\t{\n\t\tfor(int i=0; i<array.length; i++)\n\t\t{\n\t\t\tif (array[i] == value)\n\t\t\t{\n\t\t\t\treturn true;\n\t\t\t}\n\t\t}\n\t\treturn false;\n\t}";

    static final String ARRAY_CONTAINS_OBJECT_METHOD = "\tpublic boolean contains(Object[] array, Object value)\n\t{\n\t\tfor(int i=0; i<array.length; i++)\n\t\t{\n\t\t\tif (array[i].equals(value))\n\t\t\t{\n\t\t\t\treturn true;\n\t\t\t}\n\t\t}\n\t\treturn false;\n\t}";

    static final String TYPE_STRING = "String";

    static final String TYPE_NUMBER = "long";

    protected String name;

    protected String type;

    protected boolean constant;

    protected int arrayLength;

    protected String value;

    public JavaAttributeDefinition(String name, String type)
    {
        this.name = name;
        this.type = type;
        this.arrayLength = 1;
        this.constant = false;
        this.value = "";
    }

    /**
     * @return Returns the array.
     */
    public boolean isArray()
    {
        return this.arrayLength > 1;
    }

    /**
     * @param array
     *            The array to set.
     */
    public void setArrayLength(int arrayLength)
    {
        this.arrayLength = arrayLength;
    }

    /**
     * @return Returns the constant.
     */
    public boolean isConstant()
    {
        return constant;
    }

    /**
     * @param constant
     *            The constant to set.
     */
    public void setConstant(boolean constant)
    {
        this.constant = constant;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(JavaAttributeDefinition other)
    {
        return this.name.compareTo(other.name);
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();

        if (this.isConstant())
        {
            buf.append("final ");
        }

        buf.append(this.getType().toString());
        
        
        String varInitStatement = "";
        if (this.isArray())
        {
            buf.append("[]");
            varInitStatement = " = new " + this.getType().toString() + "["
                    + this.arrayLength + "]";
        }
        buf.append(" ");
        buf.append(this.name);
        if (this.hasValue())
        {
            varInitStatement = " = " + this.value;
        }
        buf.append(varInitStatement);

        buf.append(";");
        return buf.toString();
    }

    public boolean hasValue()
    {
        if (this.value == null)
        {
            return false;
        }
        else
        {
            return this.value.length() > 0;
        }
    }

    public String generateSetter(String tabs)
    {
        if (this.isConstant())
            return "";

        StringBuffer buf = new StringBuffer();
        buf.append(tabs);
        buf.append("public void set");
        String firstLetter = this.getName().substring(0, 1).toUpperCase();
        String rest = this.getName().substring(1, this.getName().length());
        buf.append(firstLetter);
        buf.append(rest);
        buf.append("(");
        buf.append(this.getType().toString());
        if (this.isArray())
        {
            buf.append("[]");
        }
        buf.append(" value)\n");
        buf.append(tabs);
        buf.append("{\n");
        buf.append(tabs);
        buf.append("\tthis.");
        buf.append(this.getName());
        buf.append(" = value;\n");
        buf.append(tabs);
        buf.append("}");
        return buf.toString();
    }

    public String generateGetter(String tabs)
    {
        if (this.isConstant())
            return "";

        StringBuffer buf = new StringBuffer();
        buf.append(tabs);
        buf.append("public ");
        buf.append(this.getType().toString());
        if (this.isArray())
        {
            buf.append("[]");
        }
        buf.append(" get");
        String firstLetter = this.getName().substring(0, 1).toUpperCase();
        String rest = this.getName().substring(1, this.getName().length());
        buf.append(firstLetter);
        buf.append(rest);
        buf.append("()\n");
        buf.append(tabs);
        buf.append("{\n");
        buf.append(tabs);
        buf.append("\treturn this.");
        buf.append(this.getName());
        buf.append(";\n");
        buf.append(tabs);
        buf.append("}");
        return buf.toString();
    }

    public String generateConstantChecker(String tabs)
    {
        if (!this.isConstant())
            return "";

        StringBuffer buf = new StringBuffer();
        buf.append(tabs);
        buf.append("public boolean is");
        String firstLetter = this.getName().substring(0, 1).toUpperCase();
        String rest = this.getName().substring(1, this.getName().length())
                .toLowerCase();
        buf.append(firstLetter);
        buf.append(rest);
        buf.append("(");
        buf.append(this.getType().toString());
        buf.append(" value)\n");
        buf.append(tabs);
        buf.append("{\n");
        buf.append(tabs);
        buf.append("\treturn ");

        String valueHoldersName = "value";

        if (this.isArray())
        {
            buf.append("contains(");
            buf.append(this.getName());
            buf.append(", ");
            buf.append(valueHoldersName);
            buf.append(")");
        }
        else
        {
            buf.append(valueHoldersName);

            if (this.getType().equals(TYPE_NUMBER))
            {
                buf.append(" == ");
                buf.append(this.getName());
            }
            else
            {
                // Objekte und Strings werden mit equals verglichen
                buf.append(".equals(");
                buf.append(this.getName());
                buf.append(")");
            }
        }
        buf.append(";\n");
        buf.append(tabs);
        buf.append("}");
        return buf.toString();
    }
}
