/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors.cobol;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import copylineparser.editors.SyntaxColorProvider;
import copylineparser.editors.WhitespaceDetector;
import copylineparser.editors.WordDetector;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CopylineRuleScanner extends RuleBasedScanner
{
    private static String[] fgKeywords = { "OCCURS", "TIMES",
            "PACKED-DECIMAL", "VALUE", "VALUES", "ON", "THRU", "THROUGH",
            "REDEFINES", "PIC", "PICTURE" };

    private static String[] fgTypes = { "BINARY", "COMP-3", "FILLER", "S9", "V9"};

    private static String[] fgConstants = { "TRUE", "FALSE", "ZERO", "ZEROES",
            "SPACE", "SPACES" };

    public CopylineRuleScanner(SyntaxColorProvider provider)
    {
        IToken schalter = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.GREEN)));
        IToken number = new Token(new TextAttribute(provider
                .getColor(SyntaxColorProvider.RED)));
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
        rules.add(new EndOfLineRule("*", comment));

        // Add rule for strings and character constants.
        rules.add(new SingleLineRule("\"", "\"", string, '\\'));
        rules.add(new SingleLineRule("'", "'", string, '\\'));
        
        //Schalter
        rules.add(new SingleLineRule("88", ".", schalter));
        
        //Zahlen
        rules.add(new NumberRule(number));
        
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
