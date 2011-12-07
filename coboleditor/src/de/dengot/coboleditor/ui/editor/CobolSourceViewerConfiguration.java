package de.dengot.coboleditor.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;

import de.dengot.coboleditor.logic.CobolReconcilingStrategy;
import de.dengot.coboleditor.logic.WordDetector;

public class CobolSourceViewerConfiguration extends SourceViewerConfiguration
{

	private CobolEditor fEditor;

	private ITokenScanner fScanner;

	public CobolSourceViewerConfiguration(CobolEditor editor)
	{
		fEditor = editor;
		fScanner = this.getCobolScanner();
	}

	public IReconciler getReconciler(ISourceViewer sourceViewer)
	{
		CobolReconcilingStrategy strategy = new CobolReconcilingStrategy(
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
		RuleBasedScanner scanner = new RuleBasedScanner();

		final String[] KEYWORDS = { "OCCURS", "TIMES", "PACKED-DECIMAL",
				"VALUE", "VALUES", "THRU", "THROUGH", "REDEFINES", "PIC",
				"PICTURE", "BINARY", "FILLER", "MOVE", "IF", "THEN", "ELSE",
				"END-IF", "PERFORM", "VARYING", "END-PERFORM", "FROM", "BY",
				"UNTIL", "SECTION", "DIVISION", "CALL", "END-CALL",
				"FILE-CONTROL", "EXIT", "STRING", "END-STRING", "WRITE", "END",
				"INVALID", "KEY", "END-WRITE", "READ", "END-READ", "START",
				"WITH", "ENVIRONMENT", "DATA", "CONFIGURATION", "PROGRAM-ID",
				"SELECT", "RECORD", "IS", "IN", "OF", "AT", "TO", "FD",
				"HIGH-VALUE", "LOW-VALUE", "USING", "INSPECT", "CONVERTING",
				"FILE", "INPUT-OUTPUT", "WORKING-STORAGE", "DECIMAL-POINT",
				"COMMA", "TERMINAL", "COPY", "SYMBOLIC", "CHARACTERS",
				"SPECIAL-NAMES", "CONTINUE", "DISPLAY", "UPON", "SET", "AND",
				"OR", "NOT", "COMPUTE", "ADD", "SUBSTRACT", "GIVING",
				"FUNCTION", "REM", "MULTIPLY", "DIVIDE", "WHEN", "EVALUATE",
				"END-EVALUATE", "OTHER", "INITIALIZE", "DELIMITED", "SIZE",
				"INTO", "PROCEDURE" };

		final String[] CONSTANTS = { "TRUE", "FALSE", "ZERO", "ZEROES",
				"SPACE", "SPACES" };

		SyntaxColorProvider colorProvider = new SyntaxColorProvider();

		IToken keyword = new Token(new TextAttribute(colorProvider
				.getColor(colorProvider.KEYWORD), null, SWT.BOLD));
		IToken constant = new Token(new TextAttribute(colorProvider
				.getColor(colorProvider.CONSTANT), null, SWT.BOLD));
		IToken string = new Token(new TextAttribute(colorProvider
				.getColor(colorProvider.STRING)));
		IToken comment = new Token(new TextAttribute(colorProvider
				.getColor(colorProvider.SINGLE_LINE_COMMENT)));
		IToken other = new Token(new TextAttribute(colorProvider
				.getColor(colorProvider.DEFAULT)));

		List<IRule> rules = new ArrayList<IRule>();
		// Add rule for single line comments.
		rules.add(new EndOfLineRule("*", comment));

		// Add rule for strings and character constants.
		rules.add(new SingleLineRule("\"", "\"", string, '\\'));
		rules.add(new SingleLineRule("'", "'", string, '\\'));

		// Add word rule for keywords, types, and constants.
		WordRule wordRule = new WordRule(new WordDetector(), other);
		for (int i = 0; i < KEYWORDS.length; i++)
		{
			wordRule.addWord(KEYWORDS[i], keyword);
		}
		for (int i = 0; i < CONSTANTS.length; i++)
		{
			wordRule.addWord(CONSTANTS[i], constant);
		}
		rules.add(wordRule);

		IRule[] ruleArray = new IRule[rules.size()];
		rules.toArray(ruleArray);
		scanner.setRules(ruleArray);
		return scanner;
	}

}
