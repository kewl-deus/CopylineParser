/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WordDetector implements IWordDetector
{

    /*
     * (non-Javadoc) Method declared on IWordDetector.
     */
    public boolean isWordPart(char character)
    {
        return Character.isJavaIdentifierPart(character);
    }

    /*
     * (non-Javadoc) Method declared on IWordDetector.
     */
    public boolean isWordStart(char character)
    {
        return Character.isJavaIdentifierStart(character);
    }
}
