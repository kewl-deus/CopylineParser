/*
 * Created on 14.01.2005
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
public class FieldTypeFactory
{

    public FieldType createFieldType(String typeDefinition)
            throws TypeDefinitionMissmatchException
    {
        try
        {
            return this.createAlphanumericFieldType(typeDefinition);
        }
        catch (TypeDefinitionMissmatchException e)
        {
            // 1. Versuch kann schiefgehen
        }

        // 2. Auf NumericType testen
        // wenns diesmal nicht klappt ist typeDef ungültig

        return this.createNumericFieldType(typeDefinition);
    }

    private AlphanumericFieldType createAlphanumericFieldType(
            String typeDefinition) throws TypeDefinitionMissmatchException
    {
        AlphanumericFieldType template = new AlphanumericFieldType();

        if ((typeDefinition == null) || typeDefinition.length() == 0)
            throw new TypeDefinitionMissmatchException(typeDefinition, template);

        char[] symbols = typeDefinition.toUpperCase().toCharArray();

        // Kriterium für Alphanumerisches Feld: mind. 1 mal "X" oder "A"
        int alphaOccurence = 0;
        for (char c : symbols)
        {
            if (!template.isAllowedSymbol(c))
            {
                throw new TypeDefinitionMissmatchException(typeDefinition,
                        template);
            }

            if (c == 'X' || c == 'A')
                alphaOccurence++;
        }

        if (alphaOccurence <= 0)
            throw new TypeDefinitionMissmatchException(typeDefinition, template);

        return template;
    }

    private NumericFieldType createNumericFieldType(String typeDefinition)
            throws TypeDefinitionMissmatchException
    {
        NumericFieldType template = new NumericFieldType();

        if ((typeDefinition == null) || typeDefinition.length() == 0)
            throw new TypeDefinitionMissmatchException(typeDefinition, template);

        char[] symbols = typeDefinition.toUpperCase().toCharArray();
        for (char c : symbols)
        {
            if (!template.isAllowedSymbol(c))
            {
                throw new TypeDefinitionMissmatchException(typeDefinition,
                        template);
            }
        }

        for (int i=0; i<symbols.length; i++)
        {
            char c = symbols[i];
            if (template.isSignSymbol(c))
            {
                template.setSignSymbol(c);
                if (i == symbols.length-1)
                {
                    template.setSignPosition(SignPosition.TAILING);
                }
                else
                {
                    template.setSignPosition(SignPosition.LEADING);
                }
                break;
            }
        }

        for (char c : symbols)
        {
            if (template.isSuppressZeroSymbol(c))
            {
                template.setSuppressLeadingZeroes(true);
                break;
            }
        }

        template.setPostCommaLength(template
                .parsePostCommaLength(typeDefinition));

        return template;
    }

}
