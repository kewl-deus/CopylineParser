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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldViewComposite extends Composite
{

    private FieldPropertiesComposite fieldPropertiesComposite = null;

    private FieldCommentaryComposite fieldCommentaryComposite = null;

    private FieldValuesComposite fieldValuesComposite = null;

    private Button buttonSave = null;

    private Button buttonRestore = null;

    public FieldViewComposite(Composite parent, int style)
    {
        super(parent, style);
        initialize();
    }

    private void initialize()
    {
        GridLayout gridLayout5 = new GridLayout();
        createFieldPropertiesComposite();
        createFieldCommentaryComposite();
        createFieldValuesComposite();
        buttonSave = new Button(this, SWT.NONE);
        buttonSave.setText("Speichern");
        buttonRestore = new Button(this, SWT.NONE);
        buttonRestore.setText("Verwerfen");
        this.setLayout(gridLayout5);
        gridLayout5.numColumns = 4;
        this.setSize(new org.eclipse.swt.graphics.Point(473, 507));
    }

    /**
     * This method initializes fieldPropertiesComposite
     * 
     */
    private void createFieldPropertiesComposite()
    {
        GridData gridData6 = new org.eclipse.swt.layout.GridData();
        fieldPropertiesComposite = new FieldPropertiesComposite(this, SWT.NONE);
        gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData6.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData6.grabExcessHorizontalSpace = true;
        gridData6.grabExcessVerticalSpace = false;
        gridData6.horizontalSpan = 4;
        fieldPropertiesComposite.setLayoutData(gridData6);
    }

    /**
     * This method initializes fieldCommentaryComposite
     * 
     */
    private void createFieldCommentaryComposite()
    {
        GridData gridData7 = new org.eclipse.swt.layout.GridData();
        fieldCommentaryComposite = new FieldCommentaryComposite(this, SWT.NONE);
        gridData7.horizontalSpan = 4;
        gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData7.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData7.grabExcessHorizontalSpace = true;
        gridData7.grabExcessVerticalSpace = true;
        gridData7.verticalSpan = 1;
        fieldCommentaryComposite.setLayoutData(gridData7);
    }

    /**
     * This method initializes fieldValuesComposite
     * 
     */
    private void createFieldValuesComposite()
    {
        GridData gridData8 = new org.eclipse.swt.layout.GridData();
        fieldValuesComposite = new FieldValuesComposite(this, SWT.NONE);
        gridData8.horizontalSpan = 4;
        gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData8.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData8.grabExcessHorizontalSpace = true;
        gridData8.grabExcessVerticalSpace = false;
        fieldValuesComposite.setLayoutData(gridData8);
    }

    public Button getButtonSave()
    {
        return buttonSave;
    }

    public Button getButtonRestore()
    {
        return buttonRestore;
    }

    public FieldCommentaryComposite getFieldCommentaryComposite()
    {
        return fieldCommentaryComposite;
    }

    public FieldPropertiesComposite getFieldPropertiesComposite()
    {
        return fieldPropertiesComposite;
    }

    public FieldValuesComposite getFieldValuesComposite()
    {
        return fieldValuesComposite;
    }
} // @jve:decl-index=0:visual-constraint="10,10"
