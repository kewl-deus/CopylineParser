package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public class SpacesToken extends ConstantToken
{

	public SpacesToken(int position)
	{
		super(position, "SPACES");
	}
    
	public boolean isSpaceToken()
	{
		return true;
	}

}
