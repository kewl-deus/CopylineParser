/*
 * Created on 13.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SqlGenerationWizard extends Wizard
{
    private SqlGenerationStepNumLimitWizardPage page;

    private int tableGenLimit = 88;
    
    private boolean genForeignKeys;

    public SqlGenerationWizard()
    {
        super();
        this.setWindowTitle("SQL Generation");
    }

    public void addPages()
    {
        if (this.page == null)
        {
            page = new SqlGenerationStepNumLimitWizardPage("tableGenLimit");
            addPage(page);
        }
    }

    public boolean performFinish()
    {
        this.tableGenLimit = page.getTableGenLimit();
        this.genForeignKeys = page.isGenerateForeignKeysEnabled();
        return true;
    }


    public int getTableGenLimit()
    {
        return this.tableGenLimit;
    }
    
    public boolean isGenerateForeignKeysEnabled()
    {
        return this.genForeignKeys;
    }

}