package de.dengot.coboleditor.logic;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;

import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.ui.editor.CobolEditor;

public class CobolReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension
{

	private CobolEditor fEditor;

	private IDocument fDocument;

	private IProgressMonitor fProgressMonitor;

	private CobolParser fParser;

	public CobolReconcilingStrategy(CobolEditor editor)
	{
		fEditor = editor;
		fParser = new CobolParser();
	}

	public void setDocument(IDocument document)
	{
		fDocument = document;
	}

	public void setProgressMonitor(IProgressMonitor monitor)
	{
		fProgressMonitor = monitor;
	}

	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion)
	{
		reconcile();
	}

	public void reconcile(IRegion partition)
	{
		reconcile();
	}

	public void initialReconcile()
	{
		reconcile();
	}

	private void reconcile()
	{
		IEditorInput input = fEditor.getEditorInput();
		final CobolProgram program = fParser.parse(input.getName(), fDocument);
		if (program == null)
			return;

		Shell shell = fEditor.getSite().getShell();
		if (shell == null || shell.isDisposed())
			return;

		shell.getDisplay().asyncExec(new Runnable() {
			public void run()
			{
				fEditor.setModel(program);
			}
		});
	}

}
