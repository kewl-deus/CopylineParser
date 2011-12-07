package de.dengot.copylineparser.model;

public class TypedField extends OccurableField
{
	private String typedefinition;

	public TypedField(int stepNumber, String name, int occurence)
	{
		super(stepNumber, name, occurence);
	}

	public TypedField(int stepNumber, String name)
	{
		this(stepNumber, name, 1);
	}

	@Override
	public int length()
	{
		return this.typedefinition.length() * this.getOccurence();
	}

	public String getTypedefinition()
	{
		return typedefinition;
	}

	public void setTypedefinition(String typedefinition)
	{
		this.typedefinition = typedefinition;
	}

}
