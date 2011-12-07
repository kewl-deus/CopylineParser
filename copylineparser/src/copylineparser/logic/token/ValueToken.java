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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ValueToken extends Token
{
    protected String value;
    
    public ValueToken(int position, String value)
    {
        super(position);
        this.value = value;
    }

    public int length()
    {
        return value.length();
    }

    public String getValue()
    {
        return value;
    }

    public String getContents()
    {
        return value;
    }
    
    public boolean isValueToken()
    {
        return true;
    }
}