package de.dengot.copylineparser.model;

import java.util.Iterator;
import java.util.LinkedList;

public class GroupField extends OccurableField implements
		Iterable<OccurableField>
{
	private LinkedList<OccurableField> subFields;

	public GroupField(int stepNumber, String name, int occurence)
	{
		super(stepNumber, name, occurence);
		this.subFields = new LinkedList<OccurableField>();
	}

	public GroupField(int stepNumber, String name)
	{
		this(stepNumber, name, 1);
	}

	@Override
	public int length()
	{
		int len = 0;
		for (OccurableField f : this)
		{
			len += f.length();
		}
		return len;
	}

	public boolean add(OccurableField field)
	{
		if (this.subFields.contains(field))
		{
			return false;
		}
		this.subFields.addLast(field);
		return true;
	}

	public Iterator<OccurableField> iterator()
	{
		return this.subFields.iterator();
	}

	public boolean isContainer()
	{
		return true;
	}

}
