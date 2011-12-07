/*
 * Created on 14.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.exception.ScannerException;
import de.dengot.copylineeditor.logic.exception.ScannerUnlimitedException;
import de.dengot.copylineeditor.logic.scanner.token.StringToken;
import de.dengot.copylineeditor.logic.scanner.token.UnlimitedStringToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerStringState extends ScannerValueState
{
    public ScannerStringState(CopylineScanner scanner)
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
