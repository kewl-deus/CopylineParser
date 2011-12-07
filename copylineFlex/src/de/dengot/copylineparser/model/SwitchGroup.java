package de.dengot.copylineparser.model;

import java.util.Iterator;
import java.util.LinkedList;

public class SwitchGroup extends TypedField implements Iterable<SwitchField>
{
	private LinkedList<SwitchField> switches;

	public SwitchGroup(int stepNumber, String name, int occurence)
	{
		super(stepNumber, name, occurence);
		this.switches = new LinkedList<SwitchField>();
	}

	public SwitchGroup(int stepNumber, String name)
	{
		this(stepNumber, name, 1);
	}

	public boolean add(SwitchField field)
	{
		if (this.switches.contains(field))
		{
			return false;
		}
		this.switches.addLast(field);
		return true;
	}

	public Iterator<SwitchField> iterator()
	{
		return this.switches.iterator();
	}

	public boolean isContainer()
	{
		return true;
	}

}
