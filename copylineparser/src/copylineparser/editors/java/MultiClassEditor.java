/*
 * Created on 10.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors.java;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import copylineparser.editors.MultiStorageEditorInput;
import copylineparser.editors.StringEditorInput;
import copylineparser.editors.StringStorage;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MultiClassEditor extends MultiPageEditorPart
{
    public static String ID = "copylineparser.editors.java.MultiClassEditor";

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    protected void createPages()
    {
        MultiStorageEditorInput input = (MultiStorageEditorInput) getEditorInput();
        for (StringStorage strStorage : input)
        {
            try
            {
                JavaEditor editor = new JavaEditor();
                StringEditorInput pageInput = new StringEditorInput(strStorage);
                int index = addPage(editor, pageInput);
                setPageText(index, editor.getTitle());
                IDocument doc = editor.getDocumentProvider().getDocument(
                        pageInput);
                doc.set(strStorage.toString());
            }
            catch (PartInitException e)
            {
                ErrorDialog.openError(getSite().getShell(),
                        "Error creating nested text editor", null, e
                                .getStatus());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor)
    {
        int pageCount = this.getPageCount();
        for (int i = 0; i < pageCount; i++)
        {
            getEditor(i).doSave(monitor);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs()
    {
        int pageCount = this.getPageCount();
        for (int i = 0; i < pageCount; i++)
        {
            getEditor(i).doSaveAs();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.ISaveablePart#isSaveAsAllowed()
     */
    public boolean isSaveAsAllowed()
    {
        return true;
    }

}
