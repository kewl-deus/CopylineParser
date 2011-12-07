package de.dengot.copylineparser.model;

public abstract class OccurableField extends AbstractField
{
	private int occurence;
	
	public OccurableField(int stepNumber, String name)
	{
		this(stepNumber, name, 1);
	}
	
	public OccurableField(int stepNumber, String name, int occurence)
	{
		super(stepNumber, name);
		this.occurence = occurence;
	}

	public int getOccurence()
	{
		return occurence;
	}

	public boolean setOccurence(int oc)
    {
        if (oc > 0)
        {
            this.occurence = oc;
            return true;
        }
        else
        {
            return false;
        }
    }
	
	public boolean isArray()
	{
		return this.getOccurence() > 1;
	}
	
	public abstract int length();
	
}
