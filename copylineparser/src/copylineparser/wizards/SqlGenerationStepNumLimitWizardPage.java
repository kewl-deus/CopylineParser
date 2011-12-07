/*
 * Created on 13.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SqlGenerationStepNumLimitWizardPage extends WizardPage
{
    private Text tabGenLimitText;
    private Button buttonGenFK;

    protected SqlGenerationStepNumLimitWizardPage(String pageName)
    {
        super(pageName);
        setTitle("Tabellen-Generierungs-Limit");
        setDescription("Geben Sie die Stufennummer ein, bis zu der Tabellen generiert werden sollen");
    }

    public void createControl(Composite parent)
    {
        GridLayout pageLayout = new GridLayout();
        pageLayout.numColumns = 1;
        parent.setLayout(pageLayout);
        parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        Composite textboxes = new Composite(parent, SWT.NONE);
        GridLayout textboxesLayout = new GridLayout();
        textboxesLayout.numColumns = 2;
        textboxes.setLayout(textboxesLayout);
        textboxes.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        Label labelLimit = new Label(textboxes, SWT.NONE);
        labelLimit.setText("Limit Stufennummer:");
        tabGenLimitText = new Text(textboxes, SWT.BORDER);
        tabGenLimitText.setText("88");
        tabGenLimitText.setSelection(0, tabGenLimitText.getText().length());
        tabGenLimitText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        
        Composite checkboxes = new Composite(parent, SWT.NONE);
        GridLayout checkboxesLayout = new GridLayout();
        checkboxesLayout.numColumns = 2;
        checkboxes.setLayout(checkboxesLayout);
        checkboxes.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        buttonGenFK = new Button(checkboxes, SWT.CHECK);
        buttonGenFK.setText("Generiere IDs und Fremdschlüsselbeziehungen");
        buttonGenFK.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        setControl(parent);
    }

    public int getTableGenLimit()
    {
        int limit = Integer.parseInt(this.tabGenLimitText.getText());
        if (limit <= 0)
            limit = 1;
        return limit;
    }
    
    public boolean isGenerateForeignKeysEnabled()
    {
        return this.buttonGenFK.getEnabled();
    }

}
