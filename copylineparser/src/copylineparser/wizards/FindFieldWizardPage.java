/*
 * Created on 06.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FindFieldWizardPage extends WizardPage
{
    private Text fieldnameToFindText;

    protected FindFieldWizardPage(String pageName)
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
