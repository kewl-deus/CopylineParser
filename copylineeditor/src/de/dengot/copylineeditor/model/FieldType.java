/*
 * Created on 14.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.model;

import de.dengot.copylineeditor.logic.exception.TypeDefinitionMissmatchException;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public abstract class FieldType
{
    private String symbol;

    public abstract String[] allowedSymbols();

    public abstract boolean isAllowedSymbol(char c);

    public abstract int parseLength(String typeDefinition)
            throws TypeDefinitionMissmatchException;

    public FieldType(String symbol)
    {
        this.symbol = symbol;
    }

    public String symbol()
    {
        return this.symbol;
    }

    protected void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public boolean isNumericType()
    {
        return false;
    }

    public boolean isAlphanumericType()
    {
        return false;
    }

}
