package de.dengot.coboleditor.ui.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.dengot.coboleditor.logic.CobolParser;
import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.ui.outline.CobolContentOutlinePage;

public class CobolEditor extends AbstractDecoratedTextEditor
{
	private CobolContentOutlinePage outline;

	private CobolProgram model;

	public CobolEditor()
	{
		super();
		setSourceViewerConfiguration(new CobolSourceViewerConfiguration(this));

	}

	public Object getAdapter(Class required)
	{
		if (IContentOutlinePage.class.equals(required))
		{
			return this.getOutline();
		}
		return super.getAdapter(required);
	}

	private CobolContentOutlinePage getOutline()
	{
		if (this.outline == null)
		{
			this.outline = new CobolContentOutlinePage(this);
		}
		return this.outline;
	}

	public void setModel(CobolProgram program)
	{
		this.model = program;
		this.getOutline().setModel(program);
	}

	public CobolProgram getModel()
	{
//		if (this.model == null)
//		{
//			IEditorInput input = this.getEditorInput();
//			IDocumentProvider prov = this.getDocumentProvider();
//			IDocument doc = prov.getDocument(input);
//			CobolParser parser = new CobolParser();
//			CobolProgram program = parser.parse(input.getName(), doc);
//			setModel(program);
//		}
		return this.model;
	}

}
