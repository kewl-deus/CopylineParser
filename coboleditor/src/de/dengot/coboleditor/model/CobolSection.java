package de.dengot.coboleditor.model;

import java.util.LinkedList;
import java.util.List;

public class CobolSection extends CobolCodeElement
{
	private List<CobolSection> calls;

	public CobolSection(String name, int offset, int length)
	{
		super(name, offset, length);
		this.calls = new LinkedList<CobolSection>();
	}

	public void addSectionElement(CobolSectionElement secElem)
	{
		super.childList.add(secElem);
	}

	public void addCall(CobolSection called)
	{
		this.calls.add(called);
	}

}
