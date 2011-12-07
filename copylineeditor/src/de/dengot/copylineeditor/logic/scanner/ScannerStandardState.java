/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.exception.ScannerException;
import de.dengot.copylineeditor.logic.scanner.token.CloseBracketSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.CommaSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.DotSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.OpenBracketSymbolToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerStandardState extends ScannerState
{

	public ScannerStandardState(CopylineScanner scanner)
	{
		super(scanner);
	}

	public void scan() throws ScannerException
	{
		if (scanner.startsWith("*"))
		{
			scanner.setState(scanner.theScannerCommentState);
			scanner.advancePosition("*");
			return;
		}
		if (scanner.startsWith("."))
		{
			scanner.appendToken(new DotSymbolToken(scanner.position));
			scanner.advancePosition(".");
			return;
		}
		if (scanner.startsWith(","))
		{
			scanner.appendToken(new CommaSymbolToken(scanner.position));
			scanner.advancePosition(",");
			return;
		}
		if (scanner.startsWith("("))
		{
			scanner.appendToken(new OpenBracketSymbolToken(scanner.position));
			scanner.advancePosition("(");
			return;
		}
		if (scanner.startsWith(")"))
		{
			scanner.appendToken(new CloseBracketSymbolToken(scanner.position));
			scanner.advancePosition(")");
			return;
		}
		if(scanner.startsWith("\""))
        {
            scanner.setState(scanner.theScannerStringState);
            scanner.advancePosition("\"");
            return;
        }
		if (scanner.startsWithDigit())
		{
			scanner.setState(scanner.theScannerCardinalState);
			return;
		}
		if (scanner.startsWithWhiteSpace())
		{
			scanner.setState(scanner.theScannerWhiteSpaceState);
			return;
		}
		else
		{
			scanner.setState(scanner.theScannerIdentifierState);
			return;
		}
	}
}