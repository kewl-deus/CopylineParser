/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.scanner.token.WhiteSpaceToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerWhiteSpaceState extends ScannerValueState
{

    public ScannerWhiteSpaceState(CopylineScanner scanner)
    {
        super(scanner);
    }

    public void scan()
    {
        if (scanner.startsWithWhiteSpace())
        {
            scanner.skip(1);
            return;
        }
        else
        {
            scanner.appendToken(new WhiteSpaceToken(startPosition, scanner.getSubString(startPosition)));
            scanner.setState(scanner.theScannerStandardState);
            return;
        }
    }

    public void finalToken(int position)
    {
        scanner.appendToken(new WhiteSpaceToken(startPosition, scanner.getSubString(startPosition)));
    }
}