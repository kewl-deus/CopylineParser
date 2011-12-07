/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import copylineparser.Copyline;
import copylineparser.FileUtils;
import copylineparser.views.CopylineContentView;
import copylineparser.views.StructureView;
import copylineparser.wizards.CopylineContentLoadingWizard;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LoadCopylineContentAction extends Action implements
        IWorkbenchWindowActionDelegate
{

    CopylineContentLoadingWizard loadWizard;

    private IWorkbenchWindow window;

    public LoadCopylineContentAction()
    {
        setEnabled(true);
    }

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
        this.loadWizard = new CopylineContentLoadingWizard();
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

    private File queryFile()
    {
        FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
        dialog.setText("Copystreckeninhalt laden");
        dialog.setFilterExtensions(new String[] { "*.bin", "*.txt", "*.*" });
        String path = dialog.open();
        if (path != null && path.length() > 0)
            return new File(path);
        return null;
    }

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        try
        {
            File file = queryFile();

            if (file == null)
            {
                return;
            }

            IWorkbenchPage page = this.window.getActivePage();

            WizardDialog dialog = new WizardDialog(this.window.getShell(),
                    loadWizard);
            dialog.open();

            StructureView structView = (StructureView) page
                    .findView(StructureView.ID);
            
            List<Copyline> copylines = new ArrayList<Copyline>();
            
            if (file.getPath().endsWith(".bin"))
            {
                copylines = FileUtils.readCopylineContent(file,
                        structView.getRoot(), loadWizard.getLoadLimit());
            }
            else
            {
                copylines = FileUtils.readCopylineContent(file,
                        loadWizard.getLoadLimit());
            }

            CopylineContentView contentView = (CopylineContentView) page
                    .findView(CopylineContentView.ID);

            contentView.setInput(copylines);

        }
        catch (IOException e)
        {
            MessageDialog.openWarning(this.window.getShell(), "IOException", e
                    .getMessage());
        }
    }
}
