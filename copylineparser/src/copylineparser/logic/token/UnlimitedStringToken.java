/*
 * Created on 14.12.2004
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
public class UnlimitedStringToken extends StringToken
{

    public UnlimitedStringToken(int position, String value)
    {
        super(position, value);
    }

    public String getValue()
    {
        return removeEscapes(value.substring("\"".length(), value.length()));
    }
}
