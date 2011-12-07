/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner.token;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StringToken extends ConstantToken
{

    public StringToken(int position, String value)
    {
        super(position, value);
    }

    public String getValue()
    {
        return removeEscapes(value.substring("\"".length(), value.length() - "\"".length()));
    }

    public String removeEscapes(String s)
    {
        StringBuffer b = new StringBuffer(s);
        for(int escapeIndex = b.indexOf("\"\""); escapeIndex >= 0; escapeIndex = b.indexOf("\"\"", escapeIndex + "\"".length()))
            b.replace(escapeIndex, escapeIndex + "\"\"".length(), "\"");

        return b.toString();
    }
    
    public boolean isStringToken()
    {
        return true;
    }

}
