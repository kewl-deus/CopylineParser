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
public class NumericFieldType extends FieldType
{
    protected final String[] NUMERIC_SYMBOLS = { "9", "B", "0", "/", ".", ",",
            "Z", "*", "+", "-", "S", "V" };

    protected final String NUMERIC_SYMBOLS_STRING = "9B0/.,Z*+-SV";

    protected final String[] NUMERIC_NOT_PRINTED_SYMBOLS = { "S", "V" };

    protected final String NUMERIC_NOT_PRINTED_SYMBOLS_STRING = "SV";

    protected final String[] NUMERIC_SIGN_SYMBOLS = { "+", "-", "S" };

    protected final String NUMERIC_SIGN_SYMBOLS_STRING = "+-S";

    protected final String[] NUMERIC_COMMA_SYMBOLS = { ",", ".", "V" };

    protected final String NUMERIC_COMMA_SYMBOLS_STRING = ",.V";

    protected final String[] NUMERIC_SUPPRESS_ZERO_SYMBOLS = { "Z" };

    protected final String NUMERIC_SUPPRESS_ZERO_SYMBOLS_STRING = "Z";

    private boolean packed;

    private boolean suppressLeadingZeroes;

    private int postCommaLength;

    private char signSymbol;

    private char commaSymbol;

    private SignPosition signPosition;

    public NumericFieldType(boolean packed, boolean suppressLeadingZeroes)
    {
        super("9");
        this.packed = packed;
        this.suppressLeadingZeroes = suppressLeadingZeroes;
        this.postCommaLength = 0;
        this.signSymbol = 0;
        this.commaSymbol = 0;
        this.signPosition = SignPosition.NO_SIGN;
    }

    public NumericFieldType()
    {
        this(false, false);
    }

    public SignPosition getSignPosition()
    {
        return signPosition;
    }

    public void setSignPosition(SignPosition signPosition)
    {
        this.signPosition = signPosition;
    }

    public char getCommaSymbol()
    {
        return commaSymbol;
    }

    public void removeComma()
    {
        this.commaSymbol = 0;
        this.postCommaLength = 0;
    }

    public boolean setCommaSymbol(char commaSymbol)
    {
        if (this.isCommaSymbol(commaSymbol))
        {
            this.commaSymbol = commaSymbol;
            return true;
        }
        return false;
    }

    public void removeSign()
    {
        this.signSymbol = 0;
    }

    public char getSignSymbol()
    {
        return signSymbol;
    }

    public boolean setSignSymbol(char signSymbol)
    {
        if (this.isSignSymbol(signSymbol))
        {
            this.signSymbol = signSymbol;
            return true;
        }
        return false;
    }

    public boolean isSigned()
    {
        return this.signSymbol != 0;
    }

    public boolean isPacked()
    {
        return this.packed;
    }

    public boolean isDecimal()
    {
        return this.postCommaLength > 0;
    }

    public boolean isInteger()
    {
        return this.postCommaLength == 0;
    }

    /**
     * @return Returns the postCommaLength.
     */
    public int getPostCommaLength()
    {
        return postCommaLength;
    }

    /**
     * @param postCommaLength
     *            The postCommaLength to set.
     */
    public void setPostCommaLength(int postCommaLength)
    {
        this.postCommaLength = postCommaLength;
    }

    /**
     * @return Returns the suppressLeadingZeroes.
     */
    public boolean isSuppressLeadingZeroes()
    {
        return suppressLeadingZeroes;
    }

    /**
     * @param packed
     *            The packed to set.
     */
    public void setPacked(boolean packed)
    {
        this.packed = packed;
    }

    public void setSuppressLeadingZeroes(boolean suppress)
    {
        this.suppressLeadingZeroes = suppress;
    }

    // Overrides super.symbol;
    public String symbol()
    {
        if (this.isSigned())
        {
            return (this.getSignSymbol() + super.symbol()).toUpperCase();
        }
        else
        {
            return super.symbol();
        }
    }

    public String[] allowedSymbols()
    {
        return NUMERIC_SYMBOLS;
    }

    public boolean isAllowedSymbol(char c)
    {
        return NUMERIC_SYMBOLS_STRING.indexOf(c) > -1;
    }

    public boolean isSignSymbol(char c)
    {
        return NUMERIC_SIGN_SYMBOLS_STRING.indexOf(c) > -1;
    }

    public boolean isCommaSymbol(char c)
    {
        return NUMERIC_COMMA_SYMBOLS_STRING.indexOf(c) > -1;
    }

    public boolean isSuppressZeroSymbol(char c)
    {
        return NUMERIC_SUPPRESS_ZERO_SYMBOLS_STRING.indexOf(c) > -1;
    }

    public boolean isNumericType()
    {
        return true;
    }

    public boolean isPrintedSymbol(char c)
    {
        if (c == 0)
        {
            return false;
        }
        return NUMERIC_NOT_PRINTED_SYMBOLS_STRING.indexOf(c) < 0;
    }

    public int parseLength(String typeDefinition)
            throws TypeDefinitionMissmatchException
    {
        int len = typeDefinition.length();
        char[] chars = typeDefinition.toUpperCase().toCharArray();
        for (char c : chars)
        {
            if (!isAllowedSymbol(c))
            {
                throw new TypeDefinitionMissmatchException(typeDefinition, this);
            }
            // Wenn es sich um ein gedachtes (== nicht gedrucktes) Zeichen
            // handelt
            if (!isPrintedSymbol(c))
            {
                // eine Stelle abziehen, da gedachte Zeichen
                // zwar in der TypeDef stehen, aber nicht in die Feldlänge
                // gerechnet werden
                len--;
            }
        }
        return len;
    }

    public int parsePostCommaLength(String typeDefinition)
            throws TypeDefinitionMissmatchException
    {
        int postCommaLen = 0;
        char[] ca = typeDefinition.toUpperCase().toCharArray();
        for (int i = 0; i < ca.length; i++)
        {
            if (!isAllowedSymbol(ca[i]))
            {
                throw new TypeDefinitionMissmatchException(typeDefinition, this);
            }
            else
            {
                if (isCommaSymbol(ca[i]))
                {
                    if (postCommaLen == 0)
                    {
                        postCommaLen = typeDefinition.length() - i - 1;
                        // -1 für das Kommazeichen selbst
                        // das nicht in den Nachkommabereich mitgezählt wird!
                        // egal ob es gedruckt wird oder nicht
                    }
                    else
                    {
                        // es sind keine zwei Kommadefinitionen erlaubt
                        throw new TypeDefinitionMissmatchException(
                                typeDefinition, this);
                    }
                }
            }
        }
        return postCommaLen;
    }
}
