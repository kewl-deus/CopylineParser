/*
 * Created on 22.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;

import copylineparser.logic.TokenStream;
import copylineparser.logic.exception.ScannerException;
import copylineparser.logic.scanner.Scanner;
import copylineparser.views.TokenStreamView;
import copylineparser.wizards.PreProcessorConfigWizard;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScanAction extends Action implements
        IWorkbenchWindowActionDelegate
{
    private IWorkbenchWindow window;

    private PreProcessorConfigWizard preProWizard;

    public ScanAction()
    {
        super();
    }

    public void dispose()
    {
    }

    /**
     * Initializes this action delegate with the workbench window it will work
     * in.
     * 
     * @param window
     *            the window that provides the context for this delegate
     */
    public void init(IWorkbenchWindow window)
    {
        this.window = window;
        this.preProWizard = new PreProcessorConfigWizard();
    }

    public void run(IAction action)
    {
        this.run();
    }

    public void run()
    {

        WizardDialog dialog = new WizardDialog(this.window.getShell(),
                preProWizard);
        //dialog.open();
        
        String source = getSource();
        TokenStream scanResult = this.scan(source);
        TokenStreamView tokenView = (TokenStreamView) this.window
                .getActivePage().findView(TokenStreamView.ID);
        tokenView.setInput(source, scanResult);
    }

    public void selectionChanged(IAction action, ISelection selection)
    {
    }

    private String getSource()
    {
        ITextEditor edt = (ITextEditor) this.window.getActivePage()
                .getActiveEditor();
        IDocument doc = edt.getDocumentProvider().getDocument(
                edt.getEditorInput());
        String source = doc.get();

        if (preProWizard.hasReplacementJob())
        {
            source = source.replaceAll(preProWizard.getStringToReplace(),
                    preProWizard.getReplacementValue());
            doc.set(source);
        }
        return source;
    }

    private TokenStream scan(String source)
    {
        TokenStream scanResult = null;
        try
        {
            Scanner scanner = new Scanner(source);
            scanResult = scanner.scan();
        }
        catch (ScannerException se)
        {
            MessageDialog.openWarning(this.window.getShell(),
                    "ScannerException", se.getMessage());
            se.printStackTrace();
        }
        finally
        {
            return scanResult;
        }
    }
}
