/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditor;

import copylineparser.Copyline;
import copylineparser.ScannerPerspective;
import copylineparser.actions.LoadCopylineContentAction;
import copylineparser.editors.PathEditorInput;
import copylineparser.editors.sql.SqlEditor;
import copylineparser.logic.generators.SqlSourceGenerator;
import copylineparser.logic.model.cobol.Field;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CopylineContentView extends ViewPart
{
    public static final String ID = "copylineparser.views.CopylineContentView";

    private TableViewer tableViewer;

    private List<Copyline> copylines;

    public void createPartControl(Composite parent)
    {

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginHeight = 5;
        gridLayout.marginWidth = 5;

        parent.setLayout(gridLayout);

        // Button: Content laden
        Button loadContentButton = new Button(parent, SWT.PUSH);
        loadContentButton.setToolTipText("Inhalt für Copystrecke laden");

        loadContentButton.setText(" " + loadContentButton.getToolTipText()
                + " ");
        ImageDescriptor loadContentImage = ImageDescriptor
                .createFromURL(GuiUtils.makeImageURL("packagefolder_obj.gif"));

        if (loadContentImage != null)
        {
            loadContentButton.setImage(loadContentImage.createImage());
        }

        loadContentButton.addSelectionListener(new SelectionListener()
        {

            public void widgetSelected(SelectionEvent e)
            {
                LoadCopylineContentAction loadContentAction = new LoadCopylineContentAction();
                loadContentAction.init(getViewSite().getWorkbenchWindow());
                loadContentAction.run();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        Button genSqlInsertButton = new Button(parent, SWT.PUSH);
        genSqlInsertButton.setToolTipText("Generiere SQL Insert-Statements");
        genSqlInsertButton.setText(" " + genSqlInsertButton.getToolTipText()
                + " ");
        ImageDescriptor genSqlInsertImage = ImageDescriptor
                .createFromURL(GuiUtils.makeImageURL("history_list.gif"));
        if (genSqlInsertImage != null)
        {
            genSqlInsertButton.setImage(genSqlInsertImage.createImage());
        }

        genSqlInsertButton.addSelectionListener(new SelectionListener()
        {

            public void widgetSelected(SelectionEvent e)
            {
                generateSqlInsertStatements();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        });

        // Table
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 3;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;

        tableViewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER);
        tableViewer.setLabelProvider(new CopylineContentViewLabelProvider());
        tableViewer.setContentProvider(new ViewContentProvider());
        tableViewer.getControl().setLayoutData(gridData);

        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setFont(new org.eclipse.swt.graphics.Font(
                org.eclipse.swt.widgets.Display.getDefault(), "Courier New", 8,
                org.eclipse.swt.SWT.NORMAL));

        TableColumn lineNumberColumn = new TableColumn(table, SWT.NONE);
        lineNumberColumn.setText("Zeile");
        lineNumberColumn.setWidth(50);

        TableColumn valueColumn = new TableColumn(table, SWT.NONE);
        valueColumn.setText("Inhalt");
        valueColumn.setWidth(750);

        this.tableViewer
                .addSelectionChangedListener(new ISelectionChangedListener()
                {
                    public void selectionChanged(SelectionChangedEvent event)
                    {
                        StructuredSelection selection = (StructuredSelection) event
                                .getSelection();
                        Copyline copyline = (Copyline) selection
                                .getFirstElement();
                        showCopyline(copyline);
                    }
                });
    }

    void generateSqlInsertStatements()
    {
        IWorkbenchPage page = this.getViewSite().getPage();
        StructureView structView = (StructureView) page
                .findView(StructureView.ID);
        Field root = structView.getRoot();
        SqlSourceGenerator sqlGenerator = new SqlSourceGenerator();
        String sql = sqlGenerator
                .generateInsertStatements(this.copylines, root);

        IPerspectiveRegistry perspReg = page.getWorkbenchWindow()
                .getWorkbench().getPerspectiveRegistry();
        IPerspectiveDescriptor scannerPersp = perspReg
                .findPerspectiveWithId(ScannerPerspective.ID);
        page.setPerspective(scannerPersp);

        IPath location = new Path(System.getProperty("user.dir")
                + "\\CopystreckeInsert.sql");
        PathEditorInput input = new PathEditorInput(location);
        String editorId = SqlEditor.ID;
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
        doc.set(sql);
    }

    void showCopyline(Copyline copyline)
    {
        StructureView structView = (StructureView) this.getViewSite().getPage()
                .findView(StructureView.ID);
        structView.getRoot().setContent(copyline.getContent());

        FieldView fieldView = (FieldView) this.getViewSite().getPage()
                .findView(FieldView.ID);
        fieldView.refresh();
    }

    public void setFocus()
    {
        this.tableViewer.getControl().setFocus();
    }

    public void setInput(List<Copyline> copylines)
    {
        this.copylines = copylines;
        Copyline[] ca = new Copyline[copylines.size()];
        copylines.toArray(ca);

        // colindex = 1 -> Inhalt
        double newContentColWidth = copylines.iterator().next().getContent()
                .length() * 5.4;
        this.tableViewer.getTable().getColumn(1).setWidth(
                (int) newContentColWidth);

        this.tableViewer.getTable().clearAll();
        this.tableViewer.refresh();
        this.tableViewer.add(ca);
        this.tableViewer.setSelection(new StructuredSelection(ca[0]));
        this.tableViewer.notifyAll();
    }
}
