/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package copylineparser.editors.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import copylineparser.editors.SyntaxColorProvider;
import copylineparser.editors.WhitespaceDetector;
import copylineparser.editors.WordDetector;

/**
 * A Java code scanner.
 */
public class JavaCodeScanner extends RuleBasedScanner
{

    private static String[] fgKeywords = {
            "abstract", "break", "case", "catch", "class", "continue", "default", "do", "else", "extends", "final", "finally", "for", "if", "implements", "import", "instanceof", "interface", "native", "new", "package", "private", "protected", "public", "return", "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "volatile", "while" };

    private static String[] fgTypes = {
            "void", "boolean", "char", "byte", "short", "int", "long", "float", "double" };

    private static String[] fgConstants = { "false", "null", "true" };

    /**
     * Creates a Java code scanner
     */
    public JavaCodeScanner(SyntaxColorProvider provider)
    {

        IToken keyword = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.KEYWORD)));
        IToken type = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.TYPE)));
        IToken string = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.STRING)));
        IToken comment = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.SINGLE_LINE_COMMENT)));
        IToken other = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.DEFAULT)));

        List rules = new ArrayList();

        // Add rule for single line comments.
        rules.add(new EndOfLineRule("//", comment)); //$NON-NLS-1$

        // Add rule for strings and character constants.
        rules.add(new SingleLineRule("\"", "\"", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
        rules.add(new SingleLineRule("'", "'", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

        // Add generic whitespace rule.
        rules.add(new WhitespaceRule(new WhitespaceDetector()));

        // Add word rule for keywords, types, and constants.
        WordRule wordRule = new WordRule(new WordDetector(), other);
        for (int i = 0; i < fgKeywords.length; i++)
            wordRule.addWord(fgKeywords[i], keyword);
        for (int i = 0; i < fgTypes.length; i++)
            wordRule.addWord(fgTypes[i], type);
        for (int i = 0; i < fgConstants.length; i++)
            wordRule.addWord(fgConstants[i], type);
        rules.add(wordRule);

        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }
}
