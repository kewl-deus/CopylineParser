package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class PackedDecimalSymbolToken extends SymbolToken
{

	private final String SYMBOL = "PACKED-DECIMAL";

	public PackedDecimalSymbolToken(int position)
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
    
	public boolean isPackedDecimalSymbolToken()
	{
		return true;
	}

}
