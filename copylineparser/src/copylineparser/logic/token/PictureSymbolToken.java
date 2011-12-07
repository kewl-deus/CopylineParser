package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class PictureSymbolToken extends SymbolToken
{

	private final String SYMBOL = "PICTURE";

	public PictureSymbolToken(int position)
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
    
	public boolean isPictureSymbolToken()
	{
		return true;
	}
}
