/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner.token;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommentToken extends ValueToken //BlindToken
{

    public CommentToken(int position, String value)
    {
        super(position, value);
    }

    public boolean isCommentToken()
    {
        return true;
    }
}