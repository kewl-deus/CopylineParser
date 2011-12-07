package de.dengot.coboleditor.ui.outline;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import de.dengot.coboleditor.model.CobolCodeElement;
import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.ui.editor.CobolEditor;

public class CobolContentOutlinePage extends ContentOutlinePage
{
	private CobolEditor editor;

	public CobolContentOutlinePage(CobolEditor editor)
	{
		super();
		this.editor = editor;
	}

	public void setModel(CobolProgram program)
	{
		this.getTreeViewer().setInput(program);
	}

	@Override
	public void createControl(Composite parent)
	{
		super.createControl(parent);
		TreeViewer treeViewer = this.getTreeViewer();
		treeViewer.setContentProvider(new CobolCodeTreeContentProvider());
		treeViewer.setLabelProvider(new CobolCodeLabelProvider());
		treeViewer.expandAll();

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event)
			{
				if (!(event.getSelection() instanceof IStructuredSelection))
					return;
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() != 1)
					return;
				Object element = selection.getFirstElement();
				if (!(element instanceof CobolCodeElement))
					return;

				CobolCodeElement cce = (CobolCodeElement) element;
				editor.selectAndReveal(cce.getOffset(), cce.getLength());
			}
		});

		setModel(this.editor.getModel());
	}

}
