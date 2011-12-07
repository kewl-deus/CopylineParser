/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.exception;

import copylineparser.logic.SyntaxObject;


/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SyntaxException extends StructureException
{
    private SyntaxObject thrower;
    
    public SyntaxException(String message, int position)
    {
        super(message, position);
    }
}
