package de.dengot.copylineeditor.logic;

import org.eclipse.jface.text.rules.IWordDetector;

public class NumberDetector implements IWordDetector
{

	public NumberDetector()
	{
	}

	public boolean isWordStart(char c)
	{
		return Character.isDigit(c);
	}

	public boolean isWordPart(char c)
	{
		return Character.isDigit(c);
	}

}
