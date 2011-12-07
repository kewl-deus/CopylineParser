/*
 * Created on 22.12.2004
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
public class PreProcessorConfigWizardPage extends WizardPage
{   
    private Text stringToReplaceText;
    private Text replacementValueText;

    protected PreProcessorConfigWizardPage(String pageName)
    {
        super(pageName);
        setTitle("Präprozessor Konfiguration");
        setDescription("Geben Sie eine vor dem Parsevorgang zu ersetzende Zeichenfolgen ein.");
    }

    public void createControl(Composite parent)
    {
        GridLayout pageLayout = new GridLayout();
        pageLayout.numColumns = 2;
        parent.setLayout(pageLayout);
        parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label labelRepTarget = new Label(parent, SWT.NONE);
        labelRepTarget.setText("Gesuchte Zeichenfolge:");
        stringToReplaceText = new Text(parent, SWT.BORDER);
        stringToReplaceText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Label labelRepString = new Label(parent, SWT.NONE);
        labelRepString.setText("Ersetzen mit:");
        replacementValueText = new Text(parent, SWT.BORDER);
        replacementValueText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        setControl(parent);
    }
    
    public String getStringToReplace()
    {
        return stringToReplaceText.getText();
    }

    
    public String getReplacementValue()
    {
        return replacementValueText.getText();
    }

}
