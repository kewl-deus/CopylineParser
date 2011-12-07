/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic;

import java.util.Iterator;

import de.dengot.copylineeditor.logic.scanner.token.Token;


/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ParserTokenStream
{
    private Iterator tokenStream;

    private Token current;

    private Token next;

    private Token behindNext;

    private Token behindBehindNext;
    
    public ParserTokenStream(TokenStream tokenStream)
    {
        this.tokenStream = tokenStream.iterator();
    }

    public void start()
    {
        skip();
        skip();
        skip();
        skip();
    }

    public void skip()
    {
        current = next;
        next = behindNext;
        behindNext = behindBehindNext;
        getNextToken();
    }
    
    public Token getCurrent()
    {
        return current;
    }

    public Token getNext()
    {
        return next;
    }

    public Token getBehindNext()
    {
        return behindNext;
    }

    public Token getBehindBehindNext()
    {
        return behindBehindNext;
    }

    private void getNextToken()
    {
        if (tokenStream.hasNext())
            behindBehindNext = (Token) tokenStream.next();
        if (behindBehindNext.isBlindToken() && tokenStream.hasNext())
            getNextToken();
    }

}