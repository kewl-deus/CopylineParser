/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.exception;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StructureException extends Exception
{
    private int position;
    
    public StructureException(String message, int position)
    {
        super(message + " @ " + position);
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }
}