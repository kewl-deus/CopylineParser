/*
 * Created on 22.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LineColumn
{
    private int line;
    private int column;
    
    public LineColumn(int line, int column)
    {
        this.line = line;
        this.column = column;
    }
    /**
     * @return Returns the column.
     */
    public int getColumn()
    {
        return column;
    }
    /**
     * @return Returns the line.
     */
    public int getLine()
    {
        return line;
    }
}
