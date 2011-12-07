package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class ZeroesToken extends ConstantToken
{

	public ZeroesToken(int position)
	{
		super(position, "ZEROES");
	}
    
	public boolean isZeroToken()
	{
		return true;
	}

}
