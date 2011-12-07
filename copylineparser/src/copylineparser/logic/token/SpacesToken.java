package copylineparser.logic.token;

/**
 * @author 010627
 *
 * Folgendes ausw�hlen, um die Schablone f�r den erstellten Typenkommentar zu �ndern:
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
