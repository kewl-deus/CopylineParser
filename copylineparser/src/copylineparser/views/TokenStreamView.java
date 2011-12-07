/*
 * Created on 03.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;
import org.eclipse.ui.part.ViewPart;

import copylineparser.logic.TextpositionFormatter;
import copylineparser.logic.TokenStream;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TokenStreamView extends ViewPart
{

    public static final String ID = "copylineparser.views.TokenStreamView";

    private TableViewer tableViewer;
    
    private TextpositionFormatter posFormatter = new TextpositionFormatter();

    private TokenStream tokenStream = TokenStream.getEmptyTokenStream();

    public void createPartControl(Composite parent)
    {
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 3;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;

        tableViewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER);
        tableViewer.setLabelProvider(new TokenStreamViewLabelProvider());
        tableViewer.setContentProvider(new ViewContentProvider());
        tableViewer.getControl().setLayoutData(gridData);

        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn typeColumn = new TableColumn(table, SWT.NONE);
        typeColumn.setText("Token");
        typeColumn.setWidth(250);

        TableColumn valueColumn = new TableColumn(table, SWT.NONE);
        valueColumn.setText("Inhalt");
        valueColumn.setWidth(300);

        TableColumn positionColumn = new TableColumn(table, SWT.NONE);
        positionColumn.setText("Position");
        positionColumn.setWidth(50);

    }

    public void setFocus()
    {
        this.tableViewer.getControl().setFocus();
    }

    public void setInput(String source, TokenStream stream)
    {
        this.posFormatter.inititialize(source);
        tableViewer.getTable().clearAll();
        tableViewer.refresh();
        this.tokenStream = stream;
        tableViewer.add(this.tokenStream.toArray());
    }

    public TokenStream getTokenStream()
    {
        return this.tokenStream;
    }
    
    public TextpositionFormatter getTextpositionFormatter()
    {
        return this.posFormatter;
    }

}
