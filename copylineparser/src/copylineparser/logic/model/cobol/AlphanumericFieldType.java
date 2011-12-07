/*
 * Created on 17.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.model.cobol;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AlphanumericFieldType extends FieldType
{
    protected final String[] ALPHANUMERIC_SYMBOLS = { "X", "A", "B", "9", "0",
            "/" };

    protected final String ALPHANUMERIC_SYMBOLS_STRING = "XAB90/";

    public AlphanumericFieldType()
    {
        super("X");
    }

    public String[] allowedSymbols()
    {
        return ALPHANUMERIC_SYMBOLS;
    }

    public boolean isAllowedSymbol(char c)
    {
        return ALPHANUMERIC_SYMBOLS_STRING.indexOf(c) > -1;
    }

    public boolean isAlphanumericType()
    {
        return true;
    }

    public int parseLength(String typeDefinition)
            throws TypeDefinitionMissmatchException
    {
        char[] chars = typeDefinition.toUpperCase().toCharArray();
        for (char c : chars)
        {
            if (!isAllowedSymbol(c))
            {
                throw new TypeDefinitionMissmatchException(typeDefinition, this);
            }
        }
        return typeDefinition.length();
    }

}
