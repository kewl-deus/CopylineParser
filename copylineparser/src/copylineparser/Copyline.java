/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Copyline
{
    private int line;
    private String content;
    
    public Copyline(int line, String content)
    {
        this.line = line;
        this.content = content;
    }
    /**
     * @return Returns the content.
     */
    public String getContent()
    {
        return content;
    }
    /**
     * @param content The content to set.
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    /**
     * @return Returns the line.
     */
    public int getLine()
    {
        return line;
    }
    /**
     * @param line The line to set.
     */
    public void setLine(int line)
    {
        this.line = line;
    }
}
