/*
 * Created on 05.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.actions;

import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
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
import copylineparser.editors.MultiStorageEditorInput;
import copylineparser.editors.PathEditorInput;
import copylineparser.editors.StringStorage;
import copylineparser.editors.java.JavaEditor;
import copylineparser.editors.java.MultiClassEditor;
import copylineparser.editors.sql.SqlEditor;
import copylineparser.logic.generators.JavaSourceGenerator;
import copylineparser.logic.model.java.JavaClassDefinition;
import copylineparser.views.StructureView;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenerateJavaSourceAction extends Action implements
        IWorkbenchWindowActionDelegate
{

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

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
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

        JavaSourceGenerator javaGenerator = new JavaSourceGenerator();
        String allInOne = javaGenerator.generateSource(structView.getRoot());

        // Perspektive wechseln
        IPerspectiveDescriptor scannerPersp = perspReg
                .findPerspectiveWithId(ScannerPerspective.ID);
        page.setPerspective(scannerPersp);

        String editorId = JavaEditor.ID;
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
        doc.set(allInOne);

        // MultiStorageEditorInput input = createMultiEditorInput(javaGenerator
        // .getClassDefinitions());
        //
        // // Perspektive wechseln
        // IPerspectiveDescriptor scannerPersp = perspReg
        // .findPerspectiveWithId(ScannerPerspective.ID);
        // page.setPerspective(scannerPersp);
        //
        // String editorId = MultiClassEditor.ID;
        // MultiClassEditor edt = null;
        // try
        // {
        // edt = (MultiClassEditor) page.openEditor(input, editorId);
        // }
        // catch (PartInitException pie)
        // {
        // pie.printStackTrace();
        // }
    }

    private IEditorInput createEditorInput()
    {
        IPath location = new Path(System.getProperty("user.dir")
                + "\\Copystrecke.java");
        PathEditorInput input = new PathEditorInput(location);
        return input;
    }

    private MultiStorageEditorInput createMultiEditorInput(
            Collection<JavaClassDefinition> classDefs)
    {
        MultiStorageEditorInput multiInput = new MultiStorageEditorInput();

        String path = System.getProperty("user.dir") + "\\";
        for (JavaClassDefinition classDef : classDefs)
        {
            IPath location = new Path(path + classDef.getName() + ".java");
            StringStorage storage = new StringStorage(classDef.getName(),
                    classDef.toString());
            storage.setPath(location);
            multiInput.add(storage);
        }
        return multiInput;
    }

}
