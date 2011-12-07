/*
 * Created on 06.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package de.dengot.copylineeditor.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FindFieldByNameWizardPage extends WizardPage
{
    private Text fieldnameToFindText;

    protected FindFieldByNameWizardPage(String pageName)
    {
        super(pageName);
        setTitle("Feldsuche per Name");
        setDescription("Geben Sie den Namen des zu suchenden Feldes ein");
    }

    public void createControl(Composite parent)
    {
        GridLayout pageLayout = new GridLayout();
        pageLayout.numColumns = 2;
        parent.setLayout(pageLayout);
        parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label labelName = new Label(parent, SWT.NONE);
        labelName.setText("Gesuchter Feldname:");
        fieldnameToFindText = new Text(parent, SWT.BORDER);
        fieldnameToFindText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        setControl(parent);
    }
    
    public String getFieldnameToFind()
    {
        return this.fieldnameToFindText.getText();
    }

}
