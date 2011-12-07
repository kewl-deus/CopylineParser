/*
 * Created on 14.01.2005
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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CopylineContentLoadLimitWizardPage extends WizardPage
{
    private Text loadLimitText;

    protected CopylineContentLoadLimitWizardPage(String pageName)
    {
        super(pageName);
        setTitle("Lade-Limit");
        setDescription("Geben Sie wieviele Zeilen Copystreckeninhalt geladen werden sollen");
    }

    public void createControl(Composite parent)
    {
        GridLayout pageLayout = new GridLayout();
        pageLayout.numColumns = 2;
        parent.setLayout(pageLayout);
        parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label labelLimit = new Label(parent, SWT.NONE);
        labelLimit.setText("Zeilenlimit:");
        loadLimitText = new Text(parent, SWT.BORDER);
        loadLimitText.setText("100");
        loadLimitText.setSelection(0, loadLimitText.getText().length());
        loadLimitText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        setControl(parent);
    }

    public int getLoadLimit()
    {
        int limit = Integer.parseInt(this.loadLimitText.getText());
        if (limit <= 0)
            limit = 1;
        return limit;
    }

}
