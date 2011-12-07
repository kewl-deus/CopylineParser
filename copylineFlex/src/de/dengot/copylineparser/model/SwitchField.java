package de.dengot.copylineparser.model;

public class SwitchField extends AbstractField
{
	static final int SWITCH_STEP_NUMBER = 88;
	 
	private String value;

	public SwitchField(String name)
	{
		super(SWITCH_STEP_NUMBER, name);
	}
	
	

	@Override
	public void setStepNumber(int stepNumber)
	{
		throw new UnsupportedOperationException();
	}


	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	
}
