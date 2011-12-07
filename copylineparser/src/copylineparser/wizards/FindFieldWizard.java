/*
 * Created on 06.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FindFieldWizard extends Wizard
{
    private FindFieldWizardPage page;

    private String fieldnameToFiend = "";

    public FindFieldWizard()
    {
        super();
        this.setWindowTitle("Feldsuche Assistent");
    }

    public void addPages()
    {
        if (this.page == null)
        {
            page = new FindFieldWizardPage("findField");
            addPage(page);
        }
    }

    public boolean performFinish()
    {
        this.fieldnameToFiend = page.getFieldnameToFind();
        return true;
    }

    public boolean hasFieldnameToFind()
    {
        try
        {
            return this.getFieldnameToFind().length() > 0;
        }
        catch (NullPointerException npe)
        {
            return false;
        }
    }

    public String getFieldnameToFind()
    {
        return this.fieldnameToFiend;
    }

}
