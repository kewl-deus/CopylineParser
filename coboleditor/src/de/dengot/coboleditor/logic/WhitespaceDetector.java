/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.coboleditor.logic;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WhitespaceDetector implements IWhitespaceDetector
{

    /*
     * (non-Javadoc) Method declared on IWhitespaceDetector
     */
    public boolean isWhitespace(char character)
    {
        return Character.isWhitespace(character);
    }
}
