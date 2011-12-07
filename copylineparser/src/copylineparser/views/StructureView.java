package copylineparser.views;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import copylineparser.ScannerPerspective;
import copylineparser.editors.PathEditorInput;
import copylineparser.editors.java.JavaEditor;
import copylineparser.logic.generators.JavaSourceGenerator;
import copylineparser.logic.model.cobol.Field;
import copylineparser.wizards.FindFieldByPositionWizard;
import copylineparser.wizards.FindFieldWizard;

public class StructureView extends ViewPart
{
    public static final String ID = "copylineparser.views.StructureView";

    private TreeViewer viewer;

    private Field root;

    private DrillDownAdapter drillDownAdapter;

    private Action findFieldAction;

    private Action gotoFieldByPositionAction;

    private Action generateJavaClassAction;

    private Action deleteFieldAction;

    private Action doubleClickAction;

    /*
     * The content provider class is responsible for providing objects to the
     * view. It can wrap existing objects in adapters or simply return objects
     * as-is. These objects may be sensitive to the current input of the view,
     * or ignore it and always show the same content (like Task List, for
     * example).
     */

    /**
     * The constructor.
     */
    public StructureView()
    {
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent)
    {
        viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        drillDownAdapter = new DrillDownAdapter(viewer);
        viewer.setLabelProvider(new FieldTreeLabelProvider());
        // viewer.setSorter(new ViewerSorter());
        viewer.setContentProvider(new FieldTreeContentProvider());
        makeActions();
        hookContextMenu();
        hookDoubleClickAction();
        contributeToActionBars();
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener)
    {
        this.viewer.addSelectionChangedListener(listener);
    }

    private void hookContextMenu()
    {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                StructureView.this.fillContextMenu(manager);
            }
        });
        Menu menu = menuMgr.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, viewer);
    }

    private void contributeToActionBars()
    {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager manager)
    {
        manager.add(findFieldAction);
        manager.add(gotoFieldByPositionAction);
        manager.add(deleteFieldAction);
        manager.add(generateJavaClassAction);
    }

    private void fillContextMenu(IMenuManager manager)
    {
        manager.add(findFieldAction);
        manager.add(gotoFieldByPositionAction);
        manager.add(deleteFieldAction);
        manager.add(generateJavaClassAction);
        manager.add(new Separator());
        drillDownAdapter.addNavigationActions(manager);
        // Other plug-ins can contribute there actions here
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private void fillLocalToolBar(IToolBarManager manager)
    {
        manager.add(findFieldAction);
        manager.add(gotoFieldByPositionAction);
        manager.add(deleteFieldAction);
        manager.add(generateJavaClassAction);
        manager.add(new Separator());
        drillDownAdapter.addNavigationActions(manager);
    }

    private void findFieldByName()
    {
        FindFieldWizard wizard = new FindFieldWizard();
        WizardDialog dialog = new WizardDialog(viewer.getControl().getShell(),
                wizard);
        dialog.open();

        if (wizard.hasFieldnameToFind())
        {
            Field gotoField = Field.findByName(getRoot(), wizard
                    .getFieldnameToFind());
            if (gotoField == null)
            {
                showMessage("Das Feld mit der Bezeichnung '"
                        + wizard.getFieldnameToFind()
                        + "' konnte nicht gefunden werden");
            }
            else
            {
                StructuredSelection newSelection = new StructuredSelection(
                        gotoField);
                viewer.setSelection(newSelection);
            }
        }
    }

    private void deleteField()
    {
        IStructuredSelection selection = (IStructuredSelection) viewer
                .getSelection();
        List selectedFields = selection.toList();
        // Field selectedField = (Field) selection.getFirstElement();

        boolean confirmation = MessageDialog.openConfirm(viewer.getControl()
                .getShell(), "Feld löschen",
                "Möchten Sie die Feldauswahl wirklich löschen?");

        if (confirmation)
        {
            Iterator it = selectedFields.iterator();
            while (it.hasNext())
            {
                Field selectedField = (Field) it.next();
                Field parent = selectedField.getParent();
                if (parent != null)
                {
                    parent.remove(selectedField);
                }
            }
            this.viewer.refresh();
        }
    }

    private void generateJavaClass()
    {
        ISelection selection = viewer.getSelection();
        Field selectedField = (Field) ((IStructuredSelection) selection)
                .getFirstElement();

        if (selectedField.isGroup())
        {
            JavaSourceGenerator javaGenerator = new JavaSourceGenerator();
            String source = javaGenerator.generateSource(selectedField);

            IPath location = new Path(System.getProperty("user.dir")
                    + "\\"
                    + JavaSourceGenerator.convertToJavaClassName(selectedField
                            .getName()) + ".java");
            PathEditorInput input = new PathEditorInput(location);

            // Perspektive wechseln
            IWorkbenchPage page = this.getViewSite().getPage();

            IPerspectiveRegistry perspReg = page.getWorkbenchWindow()
                    .getWorkbench().getPerspectiveRegistry();
            IPerspectiveDescriptor scannerPersp = perspReg
                    .findPerspectiveWithId(ScannerPerspective.ID);
            page.setPerspective(scannerPersp);

            String editorId = JavaEditor.ID;
            ITextEditor edt = null;
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
        else
        {
            showMessage(selectedField.getName()
                    + " ist keine Gruppe! Für Klassen-Generierung werden nur Gruppen unterstützt.");
        }
    }

    private void gotoFieldByPosition()
    {
        FindFieldByPositionWizard wizard = new FindFieldByPositionWizard();
        WizardDialog dialog = new WizardDialog(viewer.getControl().getShell(),
                wizard);
        dialog.open();

        if (wizard.hasPositionToFind())
        {
            int searchPos = wizard.getPositionToFind();
            if (searchPos > getRoot().length())
            {
                showMessage("Die Position " + searchPos
                        + " liegt außerhalb des Suchbereichs");
            }
            Field gotoField = Field.findByPosition(getRoot(), searchPos);
            if (gotoField == null)
            {
                showMessage("Das Feld an Position " + searchPos
                        + " konnte nicht gefunden werden");
            }
            else
            {
                StructuredSelection newSelection = new StructuredSelection(
                        gotoField);
                viewer.setSelection(newSelection);
            }
        }
    }

    private void makeActions()
    {
        ImageDescriptor defaultImage = PlatformUI.getWorkbench()
                .getSharedImages().getImageDescriptor(
                        ISharedImages.IMG_OBJS_INFO_TSK);

        findFieldAction = new Action()
        {
            public void run()
            {
                findFieldByName();
            }
        };
        findFieldAction.setText("Finde Feld");
        findFieldAction
                .setToolTipText("Sucht das von der Wurzel an nächste Feld mit bestimmtem Namen");
        ImageDescriptor findFieldImage = ImageDescriptor.createFromURL(GuiUtils
                .makeImageURL("taschenlampe_fragezeichen.gif"));
        findFieldAction
                .setImageDescriptor(findFieldImage == null ? defaultImage
                        : findFieldImage);

        gotoFieldByPositionAction = new Action()
        {
            public void run()
            {
                gotoFieldByPosition();
            }
        };
        gotoFieldByPositionAction.setText("Gehe zu Position");
        gotoFieldByPositionAction
                .setToolTipText("Sucht das Feld zu der angegebenen Position");
        ImageDescriptor gotoFieldByPositionImage = ImageDescriptor
                .createFromURL(GuiUtils.makeImageURL("search_results_view.gif"));
        gotoFieldByPositionAction
                .setImageDescriptor(gotoFieldByPositionImage == null ? defaultImage
                        : gotoFieldByPositionImage);

        deleteFieldAction = new Action()
        {
            public void run()
            {
                deleteField();
            }
        };
        deleteFieldAction.setText("Feld löschen");
        deleteFieldAction.setToolTipText("Löscht das ausgewählte Feld");
        ImageDescriptor deleteFieldImage = ImageDescriptor
                .createFromURL(GuiUtils.makeImageURL("delete_edit.gif"));
        deleteFieldAction
                .setImageDescriptor(deleteFieldImage == null ? defaultImage
                        : deleteFieldImage);

        generateJavaClassAction = new Action()
        {
            public void run()
            {
                generateJavaClass();
            }
        };
        generateJavaClassAction.setText("Generiere Java-Klasse");
        generateJavaClassAction
                .setToolTipText("Generiert aus gewähltem Feld eine Java-Klasse");

        ImageDescriptor generateJavaClassImage = ImageDescriptor
                .createFromURL(GuiUtils.makeImageURL("newsbook_wiz.gif"));
        generateJavaClassAction
                .setImageDescriptor(generateJavaClassImage == null ? defaultImage
                        : generateJavaClassImage);

        doubleClickAction = new Action()
        {
            public void run()
            {
                // ISelection selection = viewer.getSelection();
                // Object obj = ((IStructuredSelection) selection)
                // .getFirstElement();
                // showMessage("Double-click detected on " + obj.toString());
            }
        };
    }

    private void hookDoubleClickAction()
    {
        viewer.addDoubleClickListener(new IDoubleClickListener()
        {
            public void doubleClick(DoubleClickEvent event)
            {
                doubleClickAction.run();
            }
        });
    }

    private void showMessage(String message)
    {
        MessageDialog.openInformation(viewer.getControl().getShell(),
                "Strukturansicht", message);
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus()
    {
        viewer.getControl().setFocus();
    }

    public void setInput(Field root)
    {
        this.root = root;
        Field invisibleRoot = new Field(root.getStepNumber() - 1,
                "INVISIBLE_ROOT");
        invisibleRoot.add(root);
        this.viewer.setInput(invisibleRoot);
    }

    public Field getRoot()
    {
        return this.root;
    }

    public void refresh(Field field)
    {
        this.viewer.refresh(field);
    }

}
