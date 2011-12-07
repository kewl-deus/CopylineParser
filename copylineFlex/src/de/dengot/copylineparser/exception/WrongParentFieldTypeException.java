package de.dengot.copylineparser.exception;

import de.dengot.copylineparser.model.AbstractField;

public class WrongParentFieldTypeException extends Exception
{

	public WrongParentFieldTypeException(AbstractField child,
			AbstractField parent)
	{
		super("Parent-Child-Relation not allowed between: child("
				+ child.getClass().getName() + ") and parent("
				+ parent.getClass().getName() + ")");
	}

}
