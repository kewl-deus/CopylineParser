package de.dengot.copylineeditor.logic.scanner.token;


/**
 * @author 010627
 *
 * Folgendes ausw�hlen, um die Schablone f�r den erstellten Typenkommentar zu �ndern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class OnSymbolToken extends SymbolToken
{

	private final String SYMBOL = "ON";

	public OnSymbolToken(int position)
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
    
	public boolean isOnSymbolToken()
	{
		return true;
	}
}
