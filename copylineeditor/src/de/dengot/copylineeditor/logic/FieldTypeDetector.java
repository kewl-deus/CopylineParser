package de.dengot.copylineeditor.logic;

import org.eclipse.jface.text.rules.IWordDetector;

import de.dengot.copylineeditor.model.AlphanumericFieldType;
import de.dengot.copylineeditor.model.NumericFieldType;

public class FieldTypeDetector implements IWordDetector
{
	private AlphanumericFieldType aft;

	private NumericFieldType nft;

	public FieldTypeDetector()
	{
		aft = new AlphanumericFieldType();
		nft = new NumericFieldType();
	}

	public boolean isWordStart(char c)
	{
		return this.isWordPart(c);
	}

	public boolean isWordPart(char c)
	{
		return aft.isAllowedSymbol(c) || nft.isAllowedSymbol(c);
	}

}
