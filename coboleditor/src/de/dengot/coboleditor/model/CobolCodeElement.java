package de.dengot.coboleditor.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class CobolCodeElement implements Iterable<CobolCodeElement>
{
	private String name;

	private int offset;

	private int length;

	private List<String> codeLines;

	protected List<CobolCodeElement> childList;

	public CobolCodeElement(String name, int offset, int length)
	{
		this.name = name;
		this.length = length;
		this.offset = offset;
		this.childList = new LinkedList<CobolCodeElement>();
		this.codeLines = new ArrayList<String>();
	}

	public void addCodeLine(String codeLine)
	{
		this.codeLines.add(codeLine);
	}

	public String[] getCodeLines()
	{
		String[] codeLineArray = new String[this.codeLines.size()];
		this.codeLines.toArray(codeLineArray);
		return codeLineArray;
	}

	public Iterator<CobolCodeElement> iterator()
	{
		return childList.iterator();
	}
	
	public Object[] getChildren()
	{
		return this.childList.toArray();
	}
	
	public boolean hasChildren()
	{
		return ! this.childList.isEmpty();
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}

}
