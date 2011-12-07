/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner;

import de.dengot.copylineeditor.logic.exception.ScannerException;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ScannerValueState extends ScannerState
{
    protected int startPosition;

    public ScannerValueState(CopylineScanner scanner)
    {
        super(scanner);
    }

    public void setPosition(int position)
    {
        startPosition = position;
    }

    public void reset(int position)
    {
        startPosition = position;
    }

    public abstract void finalToken(int i) throws ScannerException;

    public abstract void scan() throws ScannerException;
}