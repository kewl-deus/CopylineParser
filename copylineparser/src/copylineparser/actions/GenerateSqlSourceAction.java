/*
 * Created on 13.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.actions;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.ITextEditor;

import copylineparser.ParserPerspective;
import copylineparser.ScannerPerspective;
import copylineparser.editors.PathEditorInput;
import copylineparser.editors.sql.SqlEditor;
import copylineparser.logic.generators.SqlSourceGenerator;
import copylineparser.views.StructureView;
import copylineparser.wizards.SqlGenerationWizard;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenerateSqlSourceAction extends Action implements
        IWorkbenchWindowActionDelegate
{

    private SqlGenerationWizard wizard;

    private IWorkbenchWindow window;

    /*
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    public void dispose()
    {
        window = null;
    }

    /*
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    public void init(IWorkbenchWindow window)
    {
        this.window = window;
        this.wizard = new SqlGenerationWizard();
    }

    /*
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action)
    {
        run();
    }

    /*
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection)
    {
    }

    public void run()
    {
        IWorkbenchPage page = this.window.getActivePage();

        IPerspectiveRegistry perspReg = this.window.getWorkbench()
                .getPerspectiveRegistry();
        IPerspectiveDescriptor parserPersp = perspReg
                .findPerspectiveWithId(ParserPerspective.ID);
        page.setPerspective(parserPersp);

        StructureView structView = (StructureView) page
                .findView(StructureView.ID);

//        WizardDialog dialog = new WizardDialog(this.window.getShell(), wizard);
//        dialog.open();
//
//        SqlSourceGenerator sqlGenerator = new SqlSourceGenerator(wizard
//                .getTableGenLimit(), wizard.isGenerateForeignKeysEnabled());
//        String source = sqlGenerator.generateSource(structView.getRoot());
        
        
        SqlSourceGenerator sqlGenerator = new SqlSourceGenerator();
        String source = sqlGenerator.generateSource(structView.getRoot());
        
        // Perspektive wechseln
        IPerspectiveDescriptor scannerPersp = perspReg
                .findPerspectiveWithId(ScannerPerspective.ID);
        page.setPerspective(scannerPersp);

        String editorId = SqlEditor.ID;
        ITextEditor edt = null;
        IEditorInput input = this.createEditorInput();
        try
        {
            edt = (ITextEditor) page.openEditor(input, editorId);
        }
        catch (PartInitException pie)
        {
            pie.printStackTrace();
        }

        IDocument doc = edt.getDocumentProvider().getDocument(input);
        doc.set(source);

    }

    private IEditorInput createEditorInput()
    {
        IPath location = new Path(System.getProperty("user.dir")
                + "\\Copystrecke.sql");
        PathEditorInput input = new PathEditorInput(location);
        return input;
    }
}
