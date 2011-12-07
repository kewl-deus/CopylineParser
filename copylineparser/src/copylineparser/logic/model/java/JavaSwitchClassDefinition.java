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
public class JavaSwitchClassDefinition extends JavaClassDefinition
{
    public static final String ARRAY_CONTAINS_METHOD = "public boolean contains(long[] array, long value)\n{\n\tfor(int i=0; i<array.length; i++)\n\t{\n\t\tif (array[i] == value)\n\t\t{\n\t\t\treturn true;\n\t\t}\n\t}\n\treturn false;\n}";

    /*
     * the memberAttr that holds the SwitchValue
     */
    private JavaAttributeDefinition valueHolder;

    /**
     * @param name
     */
    public JavaSwitchClassDefinition(String name,
            JavaAttributeDefinition valueHolder)
    {
        super(name);
        this.valueHolder = valueHolder;
    }

    public boolean addAttribute(JavaAttributeDefinition attr)
    {
        if (attr.isConstant())
        {
            return super.addAttribute(attr);
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("class ");
        buf.append(this.getName());
        buf.append("\n{\n\t");

        buf.append("private ");
        buf.append(this.valueHolder.toString());
        buf.append("\n\t");

        // 1. Durchlauf: Auflistung der Konstanten
        for (JavaAttributeDefinition attr : this.attributes)
        {
            if (attr.isConstant())
            {
                buf.append("public static ");
            }
            buf.append(attr.toString());
            buf.append("\n\t");
        }

        buf.append(generateConstructor(this.valueHolder));
        buf.append("\n\t");

        buf.append(ARRAY_CONTAINS_METHOD);
        buf.append("\n\t");

        // 2. Durchlauf: Methoden
        for (JavaAttributeDefinition attr : this.attributes)
        {
            buf.append(generateConstantChecker(attr));
            buf.append("\n\t");
        }

        buf.append("\n}");

        return buf.toString();
    }

    private String generateConstructor(JavaAttributeDefinition attr)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("public ");
        buf.append(this.getName());
        buf.append("(");
        buf.append(attr.getType().toString());
        buf.append(" value)\n{\n\tthis.");
        buf.append(attr.getName());
        buf.append(" = value;\n}");
        return buf.toString();
    }

    private String generateConstantChecker(JavaAttributeDefinition attr)
    {
        //TODO: equals-Methode bei String! Dafür ist Enum von JavaTypes notwendig!
        //oder generell equals (1.5 feature: Autoboxing)
        StringBuffer buf = new StringBuffer();
        buf.append("public boolean is");
        String firstLetter = attr.getName().substring(0, 1).toUpperCase();
        String rest = attr.getName().substring(1, attr.getName().length())
                .toLowerCase();
        buf.append(firstLetter);
        buf.append(rest);
        buf.append("(");
        buf.append(attr.getType().toString());
        buf.append(" value)\n{\n\treturn this.");

        if (attr.isArray())
        {
            buf.append("contains(");
            buf.append(attr.getName());
            buf.append(", value)");
        }
        else
        {
            buf.append("value == ");
            buf.append(attr.getName());
        }
        buf.append(";\n}");
        return buf.toString();
    }
}
