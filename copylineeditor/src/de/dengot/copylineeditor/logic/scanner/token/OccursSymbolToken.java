/*
 * Created on 04.10.2004
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
public class OccursSymbolToken extends SymbolToken
{
    private final String SYMBOL = "OCCURS";

    public OccursSymbolToken(int position)
    {
        super(position);
    }

    public String getContents()
    {
        return new String(SYMBOL);
    }

    public int length()
    {
        return SYMBOL.length();
    }
    
    public boolean isOccursSymbolToken()
    {
        return true;
    }

}
