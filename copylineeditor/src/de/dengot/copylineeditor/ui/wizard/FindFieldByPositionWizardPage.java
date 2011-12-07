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

public class FindFieldByPositionWizardPage extends WizardPage
{
    private Text positionToFindText;

    protected FindFieldByPositionWizardPage(String pageName)
    {
        super(pageName);
        setTitle("Feldsuche per Position");
        setDescription("Geben Sie die gesuchte Position ein");
    }

    public void createControl(Composite parent)
    {
        GridLayout pageLayout = new GridLayout();
        pageLayout.numColumns = 2;
        parent.setLayout(pageLayout);
        parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label labelName = new Label(parent, SWT.NONE);
        labelName.setText("Position:");
        positionToFindText = new Text(parent, SWT.BORDER);
        positionToFindText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        setControl(parent);
    }
    
    public int getPositionToFind()
    {
        return Integer.parseInt(this.positionToFindText.getText());
    }

}
