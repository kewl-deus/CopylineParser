/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.token;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZeroToken extends ConstantToken
{

    public ZeroToken(int position)
    {
        super(position, "ZERO");
    }
    
    public boolean isZeroToken()
    {
        return true;
    }

}
