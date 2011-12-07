package de.dengot.copylineeditor.logic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordPatternRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

import de.dengot.copylineeditor.ui.editor.SyntaxColorProvider;

public class CopylineRuleBasedScanner extends RuleBasedScanner
{

	private static final String[] KEYWORDS = { "OCCURS", "TIMES",
			"PACKED-DECIMAL", "COMP-3", "VALUE", "VALUES", "THRU", "THROUGH",
			"REDEFINES", "PIC", "PICTURE", "BINARY", "FILLER" };

	private static final String[] CONSTANTS = { "TRUE", "FALSE", "ZERO",
			"ZEROES", "SPACE", "SPACES" };

	private SyntaxColorProvider colorProvider;

	public CopylineRuleBasedScanner(SyntaxColorProvider colorProvider)
	{
		this.colorProvider = colorProvider;

		setRules(createRules());
	}

	private IRule[] createRules()
	{
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

		/*
		 * ACHTUNG WICHTIG: Die Reihenfolge der Rules im Array ist
		 * gleichbedeutend mit Ihrer Priorität beim Scannen. Das Token der
		 * Regel, welche zuerst greift wird genommen und sofort abgebrochen.
		 */

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("*", comment));

		// Add rule for strings and character constants.
		rules.add(new SingleLineRule("\"", "\"", string, '\\'));
		rules.add(new SingleLineRule("'", "'", string, '\\'));

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new WhitespaceDetector()));

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

		rules.add(createFieldSizeRule());
		rules.add(createStepNoRule());
		rules.add(createFieldTypeRule());
		rules.add(createSwitchRule());

		IRule[] result = new IRule[rules.size()];
		rules.toArray(result);
		return result;
	}

	private IRule createFieldSizeRule()
	{
		IToken sizeToken = new Token(new TextAttribute(this.colorProvider
				.getColor(new RGB(200, 100, 100)), null, SWT.BOLD));
		SingleLineRule sizeRule = new SingleLineRule("(", ")", sizeToken);
		return sizeRule;
	}

	private IRule createStepNoRule()
	{
		IToken stepNoToken = new Token(new TextAttribute(this.colorProvider
				.getColor(new RGB(200, 0, 0)), null, SWT.BOLD));
		 NumberRule stepNoRule = new NumberRule(stepNoToken);
		 stepNoRule.setColumnConstraint(0);
		return stepNoRule;
	}

	private IRule createFieldTypeRule()
	{
		IToken typeToken = new Token(new TextAttribute(this.colorProvider
				.getColor(new RGB(255, 255, 255)), null, SWT.BOLD));
		WordPatternRule typeRule = new WordPatternRule(new FieldTypeDetector(),
				"PIC ", "(", typeToken);

		return typeRule;
	}

	private IRule createSwitchRule()
	{
		IToken typeToken = new Token(new TextAttribute(this.colorProvider
				.getColor(new RGB(0, 0, 200)), null, SWT.ITALIC));
		SingleLineRule switchRule = new SingleLineRule("88 ", " ", typeToken,
				(char) 0, true);
		return switchRule;
	}

}
