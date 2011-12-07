/*
 * Created on 14.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.exception;

import de.dengot.copylineeditor.model.FieldType;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TypeDefinitionMissmatchException extends Exception
{

    /**
     * @param message
     */
    public TypeDefinitionMissmatchException(String typeDefinition,
            FieldType type)
    {
        super("Typdefinition: '" + typeDefinition + "'" + "does not match "
                + type.getClass().getName() + "!");
    }
}
