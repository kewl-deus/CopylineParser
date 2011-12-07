package de.dengot.coboleditor.model;

public class CobolDivision extends CobolCodeElement
{

	public CobolDivision(String name, int offset, int length)
	{
		super(name, offset, length);
	}
	
	public void addSection(CobolSection sec)
	{
		super.childList.add(sec);
	}

}
