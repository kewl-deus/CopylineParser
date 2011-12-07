/*
 * Created on 13.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.sql;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SqlTableDefinition implements Comparable<SqlTableDefinition>,
        Iterable<SqlColumnDefinition>
{
    private String name;

    private SortedSet<SqlColumnDefinition> columns = new TreeSet<SqlColumnDefinition>();

    public SqlTableDefinition(String name)
    {
        this.name = name;
    }

    public boolean addColumn(SqlColumnDefinition column)
    {
        return this.columns.add(column);
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo(SqlTableDefinition other)
    {
        return this.getName().compareTo(other.getName());
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("CREATE TABLE ");
        buf.append(this.getName());
        buf.append("\n");
        buf.append("(");
        buf.append("\n");

        Iterator<SqlColumnDefinition> colIt = this.columns.iterator();
        while (colIt.hasNext())
        {
            buf.append(colIt.next().toString());
            if (colIt.hasNext())
                buf.append(",\n");
        }

        buf.append("\n");
        buf.append(");");
        return buf.toString();
    }

    /**
     * @param separator
     *            das zu verwendende Trennzeichen
     * @return alphabetisch sortierte Liste der Spaltennamen als Zeichenkette
     */
    public String generateSeparatedColumnNames(String separator)
    {
        /*
         * die Sortierung wird durch die Collection vom Typ SortedSet als
         * Membervariable automatisch gewährleistet (intern wird dort über die
         * compareTo-Methode von SqlColumnDefinition sortiert)
         */
        StringBuffer buf = new StringBuffer();
        Iterator<SqlColumnDefinition> colIt = this.columns.iterator();
        while (colIt.hasNext())
        {
            buf.append(colIt.next().getName());
            if (colIt.hasNext())
                buf.append(separator);
        }
        return buf.toString();
    }

    public Iterator<SqlColumnDefinition> iterator()
    {
        return this.columns.iterator();
    }

    public int getColumnCount()
    {
        return this.columns.size();
    }

    public SqlColumnDefinition[] columnsToArray(SqlColumnDefinition[] target)
    {
        return this.columns.toArray(target);
    }

    public SqlColumnDefinition getColumnByName(String columnName)
    {
        for (SqlColumnDefinition col : this)
        {
            if (col.getName().equalsIgnoreCase(columnName))
                return col;
        }
        return null;
        // TODO: Besser throw new NoSuchColumnInTableDefException
    }

    public String toStringXML(Set<SqlForeignKeyConstraint> foreignKeys)
    {
        StringBuffer buf = new StringBuffer();
        buf.append("<table name='");
        buf.append(this.getName());
        buf.append("'>\n");
        for (SqlColumnDefinition col : this)
        {
            buf.append(col.toStringXML());
            buf.append("\n");
        }
        for (SqlForeignKeyConstraint fkey : foreignKeys)
        {
            buf.append(fkey.toStringXML());
            buf.append("\n");
        }
        buf.append("</table>");
        return buf.toString();
    }
    
    public String toStringXML()
    {
        return this.toStringXML(new HashSet<SqlForeignKeyConstraint>());
    }
}
