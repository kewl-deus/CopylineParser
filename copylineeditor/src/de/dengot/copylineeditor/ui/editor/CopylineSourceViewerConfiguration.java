package de.dengot.copylineeditor.ui.editor;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import de.dengot.copylineeditor.logic.CopylineReconcilingStrategy;
import de.dengot.copylineeditor.logic.CopylineRuleBasedScanner;

public class CopylineSourceViewerConfiguration extends SourceViewerConfiguration
{

	private CopylineEditor fEditor;

	private ITokenScanner fScanner;

	public CopylineSourceViewerConfiguration(CopylineEditor editor)
	{
		fEditor = editor;
		fScanner = this.getCobolScanner();
	}

	public IReconciler getReconciler(ISourceViewer sourceViewer)
	{
		CopylineReconcilingStrategy strategy = new CopylineReconcilingStrategy(
				fEditor);
		MonoReconciler reconciler = new MonoReconciler(strategy, false);
		reconciler.setProgressMonitor(new NullProgressMonitor());
		//reconciler.setDelay(10000);

		return reconciler;
	}

	/*
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer)
	{
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(fScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

	private ITokenScanner getCobolScanner()
	{
		return new CopylineRuleBasedScanner(new SyntaxColorProvider());
	}

}
