/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.TokenStream;
import de.dengot.copylineeditor.logic.exception.ScannerException;
import de.dengot.copylineeditor.logic.scanner.token.EndToken;
import de.dengot.copylineeditor.logic.scanner.token.StartToken;
import de.dengot.copylineeditor.logic.scanner.token.Token;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CopylineScanner
{
	private StringBuffer toBeScanned;

	private TokenStream result;

	private ScannerState state;

	protected int length;

	protected int position;

	// States
	protected ScannerStandardState theScannerStandardState;

	protected ScannerWhiteSpaceState theScannerWhiteSpaceState;

	protected ScannerIdentifierState theScannerIdentifierState;

	protected ScannerCommentState theScannerCommentState;

	protected ScannerCardinalState theScannerCardinalState;

	protected ScannerStringState theScannerStringState;

	public CopylineScanner()
	{
		// construct states
		theScannerStandardState = new ScannerStandardState(this);
		theScannerWhiteSpaceState = new ScannerWhiteSpaceState(this);
		theScannerIdentifierState = new ScannerIdentifierState(this);
		theScannerCommentState = new ScannerCommentState(this);
		theScannerCardinalState = new ScannerCardinalState(this);
		theScannerStringState = new ScannerStringState(this);

		result = TokenStream.getEmptyTokenStream();
	}

	protected void setState(ScannerState state)
	{
		this.state = state;
		state.reset(position);
	}

	protected void advancePosition(String symbol)
	{
		int delta = symbol.length();
		skip(delta);
	}

	protected void skip(int length)
	{
		position += length;
	}

	protected String getCurrentCharacter()
	{
		return toBeScanned.substring(position, position + 1);
	}

	protected String getSubString(int startPosition)
	{
		return toBeScanned.substring(startPosition, position);
	}

	protected void appendToken(Token t)
	{
		result.append(t);
	}
	
	private void init(String toBeScanned)
	{
		this.toBeScanned = new StringBuffer(toBeScanned);
		length = toBeScanned.length();
		position = 0;
		state = theScannerStandardState;
		result = new TokenStream();
		result.append(new StartToken(position));
	}

	public TokenStream scan(String toBeScanned) throws ScannerException
	{
		this.init(toBeScanned);
		int length;
		for (length = toBeScanned.length(); position < length;)
			state.scan();

		state.finalToken(length);
		result.append(new EndToken(position));
		return result;
	}

	protected boolean startsWith(String symbol)
	{
		if (symbol.length() > length - position)
			return false;
		else
			return toBeScanned.substring(position, position + symbol.length())
					.toString().equals(symbol);
	}

	protected boolean startsWithWhiteSpace()
	{
		return isWhiteSpace(toBeScanned.charAt(position));
	}

	protected static boolean isWhiteSpace(char character)
	{
		return Character.isWhitespace(character);
	}

	protected boolean startsWithDigit()
	{
		char next = toBeScanned.charAt(position);
		return Character.isDigit(next);
	}

	protected boolean startsWithSymbol()
	{
		int restLength = toBeScanned.length() - position;
		int maximalLength;
		if (restLength <= 2)
			maximalLength = restLength;
		else
			maximalLength = 2;
		String inspect = toBeScanned.substring(position,
				position + maximalLength).toString();
		return startsWithSymbol(inspect);
	}

	protected boolean startsWithSymbol(String inspect)
	{
		return inspect.startsWith("*") || inspect.startsWith(".")
				|| inspect.startsWith("(") || inspect.startsWith(")")
				|| inspect.startsWith(",");
	}
}