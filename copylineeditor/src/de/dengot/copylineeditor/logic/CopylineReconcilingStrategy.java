package de.dengot.copylineeditor.logic;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;

import de.dengot.copylineeditor.CopylineEditorPlugin;
import de.dengot.copylineeditor.logic.exception.ScannerException;
import de.dengot.copylineeditor.logic.exception.StructureException;
import de.dengot.copylineeditor.logic.scanner.CopylineScanner;
import de.dengot.copylineeditor.model.Field;
import de.dengot.copylineeditor.ui.editor.CopylineEditor;

public class CopylineReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension
{

	private CopylineEditor fEditor;

	private IDocument fDocument;

	private IProgressMonitor fProgressMonitor;

	private CopylineParser fParser;

	private CopylineScanner fScanner;

	public CopylineReconcilingStrategy(CopylineEditor editor)
	{
		fEditor = editor;
		fParser = new CopylineParser();
		fScanner = new CopylineScanner();
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

		final Field root;
		IStatus status;
		try
		{
			TokenStream scanResult = fScanner.scan(fDocument.get());
			ParserTokenStream parserInput = new ParserTokenStream(scanResult);

			root = fParser.parse(0, input.getName(), parserInput);
		}
		catch (ScannerException e)
		{
			status = new Status(Status.WARNING, CopylineEditorPlugin.ID, Status.OK, e.getMessage(), e);
			CopylineEditorPlugin.getDefault().getLog().log(status);
			return;
		}
		catch (StructureException e)
		{
			status = new Status(Status.WARNING, CopylineEditorPlugin.ID, Status.OK, e.getMessage(), e);
			CopylineEditorPlugin.getDefault().getLog().log(status);
			return;
		}
		
		Shell shell = fEditor.getSite().getShell();
		if (shell == null || shell.isDisposed())
			return;

		shell.getDisplay().asyncExec(new Runnable() {
			public void run()
			{
				fEditor.setModel(root);
			}
		});
	}

}
