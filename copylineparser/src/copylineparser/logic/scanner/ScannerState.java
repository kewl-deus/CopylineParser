/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.scanner;

import copylineparser.logic.exception.ScannerException;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ScannerState
{
    protected Scanner scanner;

    protected ScannerState(Scanner scanner)
    {
        this.scanner = scanner;
    }

    protected abstract void scan() throws ScannerException;

    protected void finalToken(int i) throws ScannerException
    {
    }

    protected void reset(int i)
    {
    }

}