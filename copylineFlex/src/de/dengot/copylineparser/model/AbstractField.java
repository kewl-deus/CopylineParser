package de.dengot.copylineparser.model;

import de.dengot.copylineparser.exception.WrongFieldRedefinitionException;
import de.dengot.copylineparser.exception.WrongParentFieldTypeException;

public abstract class AbstractField
{
	private int stepNumber;

	private String name;

	private String comment;

	private OccurableField parent;

	private OccurableField redefinedField;

	public AbstractField(int stepNumber, String name)
	{
		this.name = name;
		this.stepNumber = stepNumber;
		this.parent = null;
		this.redefinedField = null;
		this.comment = "";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getStepNumber()
	{
		return stepNumber;
	}

	public void setStepNumber(int stepNumber)
	{
		this.stepNumber = stepNumber;
	}

	public boolean equals(AbstractField other)
	{
		return this.stepNumber == other.stepNumber
				&& this.name.equalsIgnoreCase(other.name);
	}

	public boolean isFiller()
	{
		return this.name.equalsIgnoreCase("FILLER");
	}

	public boolean isRedefinition()
	{
		return this.redefinedField != null;
	}

	public boolean hasParent()
	{
		return this.parent != null;
	}

	public boolean isContainer()
	{
		return false;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public OccurableField getParent()
	{
		return parent;
	}

	public void setParent(OccurableField parent)
			throws WrongParentFieldTypeException
	{
		if (this instanceof SwitchField)
		{
			if (!(parent instanceof SwitchGroup))
				throw new WrongParentFieldTypeException(this, parent);
		}
		else
		{
			if (!(parent instanceof GroupField))
				throw new WrongParentFieldTypeException(this, parent);
		}

		this.parent = parent;
	}

	public OccurableField getRedefinedField()
	{
		return redefinedField;
	}

	public void setRedefinedField(OccurableField redefinedField)
			throws WrongFieldRedefinitionException
	{
		// a field cannot redefine itself neither another switch
		if (this == redefinedField || this instanceof SwitchField)
		{
			throw new WrongFieldRedefinitionException();
		}
		this.redefinedField = redefinedField;
	}
	
	public String toString()
	{
		return this.getStepNumber() + this.getName();
	}

}
