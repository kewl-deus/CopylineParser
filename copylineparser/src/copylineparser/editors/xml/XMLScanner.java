package copylineparser.editors.xml;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;

import copylineparser.editors.SyntaxColorProvider;
import copylineparser.editors.WhitespaceDetector;

public class XMLScanner extends RuleBasedScanner
{

    public XMLScanner(SyntaxColorProvider colorProvider)
    {
        IToken procInstr = new Token(new TextAttribute(colorProvider
                .getColor(IXMLColorConstants.PROC_INSTR)));

        IRule[] rules = new IRule[2];
        // Add rule for processing instructions
        rules[0] = new SingleLineRule("<?", "?>", procInstr); //$NON-NLS-1$ //$NON-NLS-2$
        // Add generic whitespace rule.
        rules[1] = new WhitespaceRule(new WhitespaceDetector());

        setRules(rules);
    }
}
