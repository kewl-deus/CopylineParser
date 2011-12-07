/*
 * Created on 04.10.2004
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
public class EndToken extends Token
{

    public EndToken(int position)
    {
        super(position);
    }

    public int length()
    {
        return 0;
    }

    public boolean isEndToken()
    {
        return true;
    }

    public String getContents()
    {
        return "";
    }
}
