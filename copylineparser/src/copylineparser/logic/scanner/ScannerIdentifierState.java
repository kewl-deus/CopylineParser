/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.scanner;

import copylineparser.logic.token.BinarySymbolToken;
import copylineparser.logic.token.Comp3SymbolToken;
import copylineparser.logic.token.DependingSymbolToken;
import copylineparser.logic.token.IdentifierToken;
import copylineparser.logic.token.OccursSymbolToken;
import copylineparser.logic.token.OnSymbolToken;
import copylineparser.logic.token.PackedDecimalSymbolToken;
import copylineparser.logic.token.PicSymbolToken;
import copylineparser.logic.token.PictureSymbolToken;
import copylineparser.logic.token.RedefinesSymbolToken;
import copylineparser.logic.token.SpaceToken;
import copylineparser.logic.token.SpacesToken;
import copylineparser.logic.token.ThroughSymbolToken;
import copylineparser.logic.token.ThruSymbolToken;
import copylineparser.logic.token.TimesSymbolToken;
import copylineparser.logic.token.ValueSymbolToken;
import copylineparser.logic.token.ZeroToken;
import copylineparser.logic.token.ZeroesToken;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScannerIdentifierState extends ScannerValueState
{

	public ScannerIdentifierState(Scanner scanner)
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
