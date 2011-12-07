package de.dengot.copylineeditor.ui.outline;

/*
 * Created on 17.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.dengot.copylineeditor.model.Field;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

class FieldTreeContentProvider implements IStructuredContentProvider,
		ITreeContentProvider
{

	public void inputChanged(Viewer v, Object oldInput, Object newInput)
	{
	}

	public void dispose()
	{
	}

	public Object[] getElements(Object parent)
	{
		return this.getChildren(parent);
	}

	public Object getParent(Object child)
	{
		try
		{
			Field field = (Field) child;
			return field.getParent();
		}
		catch (ClassCastException e)
		{
			return null;
		}
	}

	public Object[] getChildren(Object parent)
	{
		Field f = (Field) parent;
		if (f.isGroup())
		{
			return f.getSubFields();
		}
		return new Object[0];
	}

	public boolean hasChildren(Object parent)
	{
		try
		{
			Field field = (Field) parent;
			return field.isGroup();
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}

}
