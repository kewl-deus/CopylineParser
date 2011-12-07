package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes ausw�hlen, um die Schablone f�r den erstellten Typenkommentar zu �ndern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class ThruSymbolToken extends SymbolToken
{

	private final String SYMBOL = "THRU";

    public ThruSymbolToken(int position)
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
    
    public boolean isThroughSymbolToken()
    {
        return true;
    }
}
