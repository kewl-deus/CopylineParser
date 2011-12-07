package copylineparser;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;

import copylineparser.actions.WorkbenchActionBuilder;
import copylineparser.editors.cobol.CopylineEditor;
import copylineparser.editors.java.JavaEditor;
import copylineparser.editors.sql.SqlEditor;
import copylineparser.editors.xml.XMLEditor;

public class CopylineparserWorkbenchAdvisor extends WorkbenchAdvisor
{
    private WorkbenchActionBuilder actionBuilder;

    public String getInitialWindowPerspectiveId()
    {
        return ScannerPerspective.ID;
    }

    public void preWindowOpen(IWorkbenchWindowConfigurer configurer)
    {
        super.preWindowOpen(configurer);
        configurer.setTitle("Copystrecken-Parser");
        configurer.setInitialSize(new Point(900, 800));
        configurer.setShowStatusLine(true);
        configurer.setShowCoolBar(true);
        configurer.setShowMenuBar(true);
        configurer.setShowPerspectiveBar(true);

        IEditorRegistry edtRegistry = configurer.getWindow().getWorkbench().getEditorRegistry();
        edtRegistry.setDefaultEditor("inl", CopylineEditor.ID);
        edtRegistry.setDefaultEditor("java", JavaEditor.ID);
        edtRegistry.setDefaultEditor("sql", SqlEditor.ID);
        edtRegistry.setDefaultEditor("xml", XMLEditor.ID);
    }

    public void fillActionBars(IWorkbenchWindow window,
            IActionBarConfigurer configurer, int flags)
    {
        super.fillActionBars(window, configurer, flags);

        if (this.actionBuilder == null)
            this.actionBuilder = new WorkbenchActionBuilder(window);

        this.actionBuilder.makeAndPopulateActions(getWorkbenchConfigurer(),
                configurer);
    }

    public void postShutdown()
    {
        if (this.actionBuilder != null)
            this.actionBuilder.dispose();
    }

    // public void fillActionBars(IWorkbenchWindow window,
    // IActionBarConfigurer configurer, int flags)
    // {
    // IMenuManager menuBar = configurer.getMenuManager();
    // menuBar.add(createFileMenu(window));
    // menuBar.add(createHelpMenu(window));
    // }
    //
    // private MenuManager createFileMenu(IWorkbenchWindow window)
    // {
    // MenuManager menu = new MenuManager("Datei",
    // IWorkbenchActionConstants.M_FILE);
    // menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
    // menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    // menu.add(ActionFactory.QUIT.create(window));
    // menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
    // return menu;
    // }
    //
    // private MenuManager createHelpMenu(IWorkbenchWindow window)
    // {
    // MenuManager menu = new MenuManager("Hilfe",
    // IWorkbenchActionConstants.M_HELP);
    // menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
    // menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    // menu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
    // return menu;
    // }
}
