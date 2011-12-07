package de.dengot.coboleditor.model;


public class CobolProgram extends CobolCodeElement
{
	
	public CobolProgram(String name, int offset, int length)
	{
		super(name, offset, length);
	}
	
	public void add(CobolDivision div)
	{
		super.childList.add(div);
	}
	
	
	
}
