/*
 * Created on 31.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldCommentaryComposite extends Composite
{

    private Label labelCommentary = null;

    private Text textAreaCommentary = null;

    public FieldCommentaryComposite(Composite parent, int style)
    {
        super(parent, style);
        initialize();
    }

    private void initialize()
    {
        labelCommentary = new Label(this, SWT.NONE);
        textAreaCommentary = new Text(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
                | SWT.BORDER);
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
        labelCommentary.setLayoutData(gridData3);
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.grabExcessHorizontalSpace = true;
        gridData4.grabExcessVerticalSpace = true;
        textAreaCommentary.setLayoutData(gridData4);
        labelCommentary.setText("Kommentare");
        this.setSize(new org.eclipse.swt.graphics.Point(402, 225));
    }

    public Text getTextAreaCommentary()
    {
        return textAreaCommentary;
    }
}
