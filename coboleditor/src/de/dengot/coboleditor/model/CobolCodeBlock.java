package de.dengot.coboleditor.model;

import java.util.LinkedList;
import java.util.List;

public abstract class CobolCodeBlock extends CobolSectionElement
{
	private List<CobolSectionElement> innerBlocks;

	public CobolCodeBlock(String name, int offset, int length)
	{
		super(name, length, offset);
		this.innerBlocks = new LinkedList<CobolSectionElement>();
	}
	
	public void addInnerBlock(CobolSectionElement innerBlock)
	{
		this.innerBlocks.add(innerBlock);
	}

}
