/*
 * Created on 06.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.ui.wizard;
import org.eclipse.jface.wizard.Wizard;

public class FindFieldByNameWizard extends Wizard
{
    private FindFieldByNameWizardPage page;

    private String fieldnameToFiend = "";

    public FindFieldByNameWizard()
    {
        super();
        this.setWindowTitle("Feldsuche Assistent");
    }

    public void addPages()
    {
        if (this.page == null)
        {
            page = new FindFieldByNameWizardPage("findFieldByName");
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
