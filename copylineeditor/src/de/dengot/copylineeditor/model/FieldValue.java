/*
 * Created on 13.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.model;


/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class FieldValue
{
    public static final String SPACE = " ";
    public static final String SPACES = " ";
    public static final long ZERO = 0;
    public static final long ZEROES = 0;
    
    public boolean isSimple()
    {
        return false;
    }
    
    public boolean isRange()
    {
        return false;
    }
    
    public abstract boolean equals(String content);
}
