/*
 * Created on 10.01.2005
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
public class Comp3SymbolToken extends SymbolToken
{

    private final String SYMBOL = "COMP-3";

	public Comp3SymbolToken(int position)
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
    
	public boolean isComp3SymbolToken()
	{
		return true;
	}

}
