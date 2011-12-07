package copylineparser.actions;

import java.io.File;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import copylineparser.editors.PathEditorInput;
import copylineparser.editors.cobol.CopylineEditor;

public class OpenSourceFileAction extends Action implements
        IWorkbenchWindowActionDelegate
{

    private IWorkbenchWindow window;

    public OpenSourceFileAction()
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
        dialog.setText("Copystrecke öffnen");
        dialog.setFilterExtensions(new String[]{"*.inl", "*.cob", "*.*"});
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
        File file = queryFile();
        if (file != null)
        {
            IWorkbenchPage page = this.window.getActivePage();
            IEditorInput input = createEditorInput(file);
            String editorId = getEditorId(file);
            page.closeAllEditors(false);
            try
            {
                page.openEditor(input, editorId);
            }
            catch (PartInitException pie)
            {
                pie.printStackTrace();
            }
        }
        else
        {
            String msg = MessageFormat.format("File is null: {0}",
                    new String[] { file.getName() });
            MessageDialog.openWarning(this.window.getShell(), "Problem", msg);
        }
    }

    private String getEditorId(File file)
    {
        IWorkbench workbench = this.window.getWorkbench();
        IEditorRegistry editorRegistry = workbench.getEditorRegistry();
        IEditorDescriptor descriptor = editorRegistry.getDefaultEditor(file
                .getName());
        if (descriptor != null)
            return descriptor.getId();
        return CopylineEditor.ID;
    }

    private IEditorInput createEditorInput(File file)
    {
        IPath location = new Path(file.getAbsolutePath());
        PathEditorInput input = new PathEditorInput(location);
        return input;
    }
}
