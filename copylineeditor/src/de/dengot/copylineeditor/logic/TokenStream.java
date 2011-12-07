/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic;

import java.util.Iterator;
import java.util.Vector;

import de.dengot.copylineeditor.logic.scanner.token.EndToken;
import de.dengot.copylineeditor.logic.scanner.token.StartToken;
import de.dengot.copylineeditor.logic.scanner.token.Token;


/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TokenStream implements Iterable<Token>
{
    private Vector<Token> tokenStream = new Vector<Token>();

    public static TokenStream getEmptyTokenStream()
    {
        TokenStream stream = new TokenStream();
        stream.append(new EndToken(0));
        stream.append(new StartToken(0));
        return stream;
    }
    
    public Token[] toArray()
    {
        Token[] ta = new Token[this.tokenStream.size()];
        this.tokenStream.toArray(ta);
        return ta;
    }

    public TokenStream()
    {
    }

    public void append(Token t)
    {
        tokenStream.add(t);
    }

    public void appendAll(TokenStream stream)
    {
        tokenStream.addAll(stream.tokenStream);
    }

    public Iterator<Token> iterator()
    {
        return tokenStream.iterator();
    }

    public TokenStream reverse()
    {
        TokenStream result = new TokenStream();
        for (int i = tokenStream.size() - 1; i >= 0; i--)
            result.append((Token) tokenStream.get(i));

        return result;
    }

    public Token getTokenAt(int position)
    {
        Iterator tokenIterator = tokenStream.iterator();
        Token current = new StartToken(0);
        while (tokenIterator.hasNext())
        {
            current = (Token) tokenIterator.next();
            int currentStartPosition = current.getPosition();
            int currentEndPosition = (currentStartPosition + current.length()) - 1;
            if (currentStartPosition <= position
                    && currentEndPosition >= position)
                return current;
        }
        return current;
    }

    protected Token removeFirst()
    {
        Token result = (Token) tokenStream.get(0);
        tokenStream.remove(0);
        return result;
    }

    protected Token removeLast()
    {
        int last = tokenStream.size() - 1;
        Token result = (Token) tokenStream.get(last);
        tokenStream.remove(last);
        return result;
    }

    protected int length()
    {
        return tokenStream.size();
    }

    public Token getLastSignificantToken()
    {
        if (tokenStream.size() > 1)
            return (Token) tokenStream.get(tokenStream.size() - 2);
        else
            return (Token) tokenStream.get(0);
    }

    public void removeLastSignificantToken()
    {
        if (tokenStream.size() > 1)
            tokenStream.remove(tokenStream.size() - 2);
    }

    public StringBuffer getString()
    {
        StringBuffer buffer = new StringBuffer();
        Token current;
        for (Iterator tokens = iterator(); tokens.hasNext(); buffer
                .append(current.getContents()))
            current = (Token) tokens.next();

        return buffer;
    }

}