package de.dengot.coboleditor.ui.outline;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.dengot.coboleditor.model.CobolCodeElement;

class CobolCodeTreeContentProvider implements ITreeContentProvider
{

	public CobolCodeTreeContentProvider()
	{
	}

	public Object[] getChildren(Object parentElement)
	{
		CobolCodeElement c = (CobolCodeElement) parentElement;
		return c.getChildren();
	}

	public Object getParent(Object element)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren(Object element)
	{
		CobolCodeElement c = (CobolCodeElement) element;
		return c.hasChildren();
	}

	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof CobolCodeElement)
		{
			CobolCodeElement c = (CobolCodeElement) inputElement;
			if (c.hasChildren())
			{
				return c.getChildren();
			}
		}
		return null;
	}

	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		// TODO Auto-generated method stub

	}

}
