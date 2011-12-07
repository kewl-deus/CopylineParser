/*
 * Created on 31.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldValuesComposite extends Composite
{

    private Label labelValueList = null;

    private List valueList = null;
    
    private ListViewer valueListViewer = null;

    public FieldValuesComposite(Composite parent, int style)
    {
        super(parent, style);
        initialize();
        initListViewer();
    }

    private void initialize()
    {
        labelValueList = new Label(this, SWT.NONE);
        valueList = new List(this, SWT.V_SCROLL | SWT.BORDER);
        GridData gridData4 = new org.eclipse.swt.layout.GridData();
        GridData gridData3 = new org.eclipse.swt.layout.GridData();
        GridLayout gridLayout2 = new GridLayout();
        this.setLayout(gridLayout2);
        gridLayout2.horizontalSpacing = 10;
        gridLayout2.verticalSpacing = 10;
        gridLayout2.marginWidth = 10;
        gridLayout2.marginHeight = 10;
        gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelValueList.setLayoutData(gridData3);
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.grabExcessHorizontalSpace = true;
        gridData4.grabExcessVerticalSpace = true;
        valueList.setLayoutData(gridData4);
        labelValueList.setText("Vorgabewerte");
        this.setSize(new org.eclipse.swt.graphics.Point(402, 225));
    }

    private void initListViewer()
    {
        valueListViewer = new ListViewer(valueList);
        valueListViewer.setContentProvider(new ValueTabContentProvider());
        valueListViewer.setLabelProvider(new ValueTabLabelProvider());
    }
    
    
    public List getValueList()
    {
        return valueList;
    }
    
    public ListViewer getValueListViewer()
    {
        return valueListViewer;
    }
}
