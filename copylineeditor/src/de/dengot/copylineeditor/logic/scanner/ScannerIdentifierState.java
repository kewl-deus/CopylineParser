/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.scanner.token.BinarySymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.Comp3SymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.DependingSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.IdentifierToken;
import de.dengot.copylineeditor.logic.scanner.token.OccursSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.OnSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.PackedDecimalSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.PicSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.PictureSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.RedefinesSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.SpaceToken;
import de.dengot.copylineeditor.logic.scanner.token.SpacesToken;
import de.dengot.copylineeditor.logic.scanner.token.ThroughSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.ThruSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.TimesSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.ValueSymbolToken;
import de.dengot.copylineeditor.logic.scanner.token.ZeroToken;
import de.dengot.copylineeditor.logic.scanner.token.ZeroesToken;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScannerIdentifierState extends ScannerValueState
{

	public ScannerIdentifierState(CopylineScanner scanner)
	{
		super(scanner);
	}

	public void scan()
	{
		if (scanner.startsWithWhiteSpace())
		{
			appendKeyWordTokenOrIdentifier();
			scanner.setState(scanner.theScannerWhiteSpaceState);
			return;
		}
		if (scanner.startsWithSymbol())
		{
			appendKeyWordTokenOrIdentifier();
			scanner.setState(scanner.theScannerStandardState);
			return;
		}
		else
		{
			scanner.skip(1);
			return;
		}
	}

	public void finalToken(int position)
	{
		appendKeyWordTokenOrIdentifier();
	}

	private void appendKeyWordTokenOrIdentifier()
	{
		String value = scanner.getSubString(startPosition);
		if (value.equalsIgnoreCase("pic"))
		{
			scanner.appendToken(new PicSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("picture"))
		{
			scanner.appendToken(new PictureSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("occurs"))
		{
			scanner.appendToken(new OccursSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("value"))
		{
			scanner.appendToken(new ValueSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("on"))
		{
			scanner.appendToken(new OnSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("depending"))
		{
			scanner.appendToken(new DependingSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("times"))
		{
			scanner.appendToken(new TimesSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("through"))
		{
			scanner.appendToken(new ThroughSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("thru"))
		{
			scanner.appendToken(new ThruSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("binary"))
		{
			scanner.appendToken(new BinarySymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("redefines"))
		{
			scanner.appendToken(new RedefinesSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("packed-decimal"))
		{
			scanner.appendToken(new PackedDecimalSymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("comp-3"))
		{
			scanner.appendToken(new Comp3SymbolToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("zero"))
		{
			scanner.appendToken(new ZeroToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("zeroes"))
		{
			scanner.appendToken(new ZeroesToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("space"))
		{
			scanner.appendToken(new SpaceToken(startPosition));
			return;
		}
		if (value.equalsIgnoreCase("spaces"))
		{
			scanner.appendToken(new SpacesToken(startPosition));
			return;
		}
		else
		{
			scanner.appendToken(new IdentifierToken(startPosition, value));
			return;
		}
	}
}
