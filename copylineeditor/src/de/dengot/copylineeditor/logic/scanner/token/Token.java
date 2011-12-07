/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.logic.scanner.token;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class Token
{
    private int position;
    
    public Token(int position)
    {
        this.position = position;
    }
    
    public int getPosition()
    {
        return position;
    }
    
    public String getTypeName()
    {
        Class clazz = this.getClass();
        String packageName = clazz.getPackage().getName();
        int packNameLength = (packageName + ".").length();
        return clazz.getName().substring(packNameLength);
    }

    public abstract String getContents();

    public abstract int length();
    
    public String toString()
    {
        return this.getTypeName() + ".content = '" + this.getContents() + "'";
    }


    public boolean isBinarySymbolToken()
    {
        return false;
    }

	public boolean isBlindToken()
	{
		return false;
	}

	public boolean isCardinalToken()
	{
		return false;
	}

	public boolean isCloseBracketSymbolToken()
	{
		return false;
	}
	
	public boolean isCommaSymbolToken()
	{
		return false;
	}

	public boolean isCommentToken()
	{
		return false;
	}
	
	public boolean isComp3SymbolToken()
	{
		return false;
	}

	public boolean isConstantToken()
	{
		return false;
	}
	
	public boolean isDependingSymbolToken()
	{
		return false;
	}

	public boolean isDotSymbolToken()
	{
		return false;
	}

	public boolean isEndToken()
	{
		return false;
	}

	public boolean isIdentifierToken()
	{
		return false;
	}

	public boolean isOccursSymbolToken()
	{
		return false;
	}
	
	public boolean isOnSymbolToken()
	{
		return false;
	}

	public boolean isOpenBracketSymbolToken()
	{
		return false;
	}

	public boolean isPackedDecimalSymbolToken()
	{
		return false;
	}

	public boolean isPictureSymbolToken()
	{
		return false;
	}

	public boolean isRedefinesSymbolToken()
    {
        return false;
    }

	public boolean isSpaceToken()
	{
		return false;
	}

	public boolean isStartToken()
	{
		return false;
	}
	
	public boolean isStringToken()
	{
		return false;
	}
	
	public boolean isSymbolToken()
	{
		return false;
	}

	
	public boolean isThroughSymbolToken()
	{
		return false;
	}
	
	public boolean isTimesSymbolToken()
	{
		return false;
	}

	public boolean isValueSymbolToken()
	{
		return false;
	}

	public boolean isValueToken()
	{
		return false;
	}

	public boolean isWhiteSpaceToken()
	{
		return false;
	}
	
	public boolean isZeroToken()
	{
		return false;
	}

}