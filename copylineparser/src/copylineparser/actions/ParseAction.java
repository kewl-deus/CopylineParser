/*
 * Created on 05.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import copylineparser.ParserPerspective;
import copylineparser.logic.LineColumn;
import copylineparser.logic.Parser;
import copylineparser.logic.TextpositionFormatter;
import copylineparser.logic.ParserTokenStream;
import copylineparser.logic.TokenStream;
import copylineparser.logic.exception.ParserException;
import copylineparser.logic.model.cobol.Field;
import copylineparser.views.StructureView;
import copylineparser.views.TokenStreamView;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParseAction extends Action implements
        IWorkbenchWindowActionDelegate
{
    private IWorkbenchWindow window;
    
    public ParseAction()
    {
        super();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose()
    {
    }

    public void init(IWorkbenchWindow window)
    {
        this.window = window;

    }

    public void run(IAction action)
    {
        this.run();
    }
    
    public void selectionChanged(IAction action, ISelection selection)
    {
    }
    
    public void run()
    {
        IWorkbenchPage page = window.getActivePage();
        TokenStreamView tokenView = (TokenStreamView) page.findView(TokenStreamView.ID);
        TokenStream tokenStream = tokenView.getTokenStream();
        TextpositionFormatter posFormatter = tokenView.getTextpositionFormatter(); 
        
        Field root = this.parse(tokenStream, posFormatter);
        
        IPerspectiveDescriptor parserPerspective = this.window.getWorkbench()
                .getPerspectiveRegistry().findPerspectiveWithId(
                        ParserPerspective.ID);
        
        page.setPerspective(parserPerspective);
        
        StructureView structView = (StructureView) page
                .findView(StructureView.ID);
        structView.setInput(root);
        page.activate(structView);
    }
    
    private Field parse(TokenStream streamToParse, TextpositionFormatter errorposFormatter)
    {
        Field root = null;
        try
        {
            ParserTokenStream parserSource = new ParserTokenStream(
                    streamToParse);
            Parser parser = new Parser(parserSource);
            root = parser.parse();
        }
        catch (ParserException pe)
        {
            LineColumn errorPos = errorposFormatter.getLineAndColunm(pe.getPosition());
            MessageDialog.openWarning(this.window.getShell(),
                    "ParserException", pe.getMessage() + " Zeile: "
                            + errorPos.getLine() + " Spalte: "
                            + errorPos.getColumn());
        }
        catch (Exception e)
        {
            MessageDialog.openWarning(this.window.getShell(), "Exception", e
                    .getMessage());
            e.printStackTrace();
        }
        finally
        {
            return root;
        }
    }
    
}
