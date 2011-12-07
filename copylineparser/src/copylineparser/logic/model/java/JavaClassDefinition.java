/*
 * Created on 11.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.java;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JavaClassDefinition implements Comparable<JavaClassDefinition>
{
    protected String name;

    protected SortedSet<JavaAttributeDefinition> attributes = new TreeSet<JavaAttributeDefinition>();

    protected SortedSet<JavaClassDefinition> innerClasses = new TreeSet<JavaClassDefinition>();

    /**
     * @param name
     */
    public JavaClassDefinition(String name)
    {
        super();
        this.name = name;
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

    public boolean addAttribute(JavaAttributeDefinition attr)
    {
        return this.attributes.add(attr);
    }

    public boolean addInnerClass(JavaClassDefinition innerClass)
    {
        return this.innerClasses.add(innerClass);
    }

    public boolean hasAttributes()
    {
        return !this.attributes.isEmpty();
    }

    public int getAttributeCount()
    {
        return this.attributes.size();
    }

    public boolean hasInnerClasses()
    {
        return !this.innerClasses.isEmpty();
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("class ");
        buf.append(this.getName());
        buf.append("\n{\n\t");

        // 1. Durchlauf: Auflistung der Member
        for (JavaAttributeDefinition attr : this.attributes)
        {
            if (attr.isConstant())
            {
                buf.append("public static ");
            }
            else
            {
                buf.append("protected ");
            }
            buf.append(attr.toString());
            buf.append("\n\t");
        }

        buf.append("\n\n");
        buf.append(JavaAttributeDefinition.ARRAY_CONTAINS_NUMBER_METHOD);
        buf.append("\n\n");
        buf.append(JavaAttributeDefinition.ARRAY_CONTAINS_OBJECT_METHOD);
        buf.append("\n\n");

        String tabs = "\t";
        // 2. Durchlauf: Methoden
        for (JavaAttributeDefinition attr : this.attributes)
        {
            if (attr.isConstant())
            {
                buf.append(attr.generateConstantChecker(tabs));
                buf.append("\n\n");
            }
            else
            {
                buf.append(attr.generateSetter(tabs));
                buf.append("\n\n");
                buf.append(attr.generateGetter(tabs));
                buf.append("\n\n");
            }
        }

        // innere Klassen!
        if (this.hasInnerClasses())
        {
            buf.append("\n\n");
            buf.append("********************* innere Klassen **************************");
            buf.append("\n\n");
            for (JavaClassDefinition innerClass : this.innerClasses)
            {
                buf.append(innerClass.toString());
            }
            buf.append("\n\n");
        }

        // ClassDef schliessen
        buf.append("\n}");

        return buf.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(JavaClassDefinition other)
    {
        return this.getName().compareTo(other.getName());
    }
}
