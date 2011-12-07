/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.scanner;

import copylineparser.logic.exception.ScannerException;
import copylineparser.logic.token.CardinalToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerCardinalState extends ScannerValueState
{

    protected ScannerCardinalState(Scanner scanner)
    {
        super(scanner);
    }

    public void scan()
    {
        if (scanner.startsWithDigit())
        {
            scanner.skip(1);
            return;
        }
        if (scanner.startsWithSymbol())
        {
            scanner.appendToken(new CardinalToken(startPosition, scanner
                    .getSubString(startPosition)));
            scanner.setState(scanner.theScannerStandardState);
            return;
        }
        if (scanner.startsWithWhiteSpace())
        {
            scanner.appendToken(new CardinalToken(startPosition, scanner
                    .getSubString(startPosition)));
            scanner.setState(scanner.theScannerWhiteSpaceState);
            return;
        } else
        {
            ScannerIdentifierState theNextState = scanner.theScannerIdentifierState;
            scanner.setState(theNextState);
            theNextState.setPosition(startPosition);
            return;
        }
    }

    public void finalToken(int position) throws ScannerException
    {
        scanner.appendToken(new CardinalToken(startPosition, scanner
                .getSubString(startPosition)));
    }
}