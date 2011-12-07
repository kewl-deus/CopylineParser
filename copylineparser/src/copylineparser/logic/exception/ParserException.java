package copylineparser.logic.exception;

import copylineparser.logic.token.Token;

/**
 * @author 010627
 * 
 * Folgendes ausw�hlen, um die Schablone f�r den erstellten Typenkommentar zu
 * �ndern: Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und
 * Kommentare
 */
public class ParserException extends SyntaxException
{
    public ParserException(String message, int position, Token currentToken)
    {
        super("Parser exception: " + message + " " + currentToken.getTypeName()
                + " '" + currentToken.getContents() + "'", position);

    }

}
