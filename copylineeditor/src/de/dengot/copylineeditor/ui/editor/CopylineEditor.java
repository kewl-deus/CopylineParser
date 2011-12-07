package de.dengot.copylineeditor.ui.editor;

import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.dengot.copylineeditor.model.Field;
import de.dengot.copylineeditor.ui.outline.CopylineOutlinePage;

public class CopylineEditor extends AbstractDecoratedTextEditor
{
	private Field fModel;

	private CopylineOutlinePage fOutlinePage;

	public CopylineEditor()
	{
		super();
		setSourceViewerConfiguration(new CopylineSourceViewerConfiguration(this));
	}

	public Field getModel()
	{
		return fModel;
	}

	public void setModel(Field model)
	{
		this.fModel = model;
		if (fOutlinePage != null)
			fOutlinePage.setModel(model);
	}

	public void outlinePageClosed()
	{
		fOutlinePage = null;
	}

	/*
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class required)
	{
		if (IContentOutlinePage.class.equals(required))
		{
			if (fOutlinePage == null)
				fOutlinePage = new CopylineOutlinePage(this);
			return fOutlinePage;
		}
		return super.getAdapter(required);
	}

}
