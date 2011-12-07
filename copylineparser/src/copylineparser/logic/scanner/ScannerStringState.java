/*
 * Created on 14.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.scanner;

import copylineparser.logic.exception.ScannerException;
import copylineparser.logic.exception.ScannerUnlimitedException;
import copylineparser.logic.token.StringToken;
import copylineparser.logic.token.UnlimitedStringToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerStringState extends ScannerValueState
{
    public ScannerStringState(Scanner scanner)
    {
        super(scanner);
    }

    public void scan()
    {
        if (scanner.startsWith("\"\""))
        {
            scanner.advancePosition("\"\"");
            return;
        }
        if (scanner.startsWith("\""))
        {
            scanner.advancePosition("\"");
            StringToken strToken = new StringToken(startPosition, scanner
                    .getSubString(startPosition));
            scanner.appendToken(strToken);
            scanner.setState(scanner.theScannerStandardState);
            return;
        }
        else
        {
            scanner.skip(1);
            return;
        }
    }

    public void finalToken(int position) throws ScannerException
    {
        scanner.appendToken(new UnlimitedStringToken(startPosition, scanner
                .getSubString(startPosition)));
        throw new ScannerUnlimitedException("Unlimited String", startPosition);
    }

}
