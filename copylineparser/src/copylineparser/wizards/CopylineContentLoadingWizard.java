/*
 * Created on 14.01.2005
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
public class CopylineContentLoadingWizard extends Wizard
{
    private CopylineContentLoadLimitWizardPage page;

    private int loadLimit = 100;

    public CopylineContentLoadingWizard()
    {
        super();
        this.setWindowTitle("Inhalt für Copystrecke laden");
    }

    public void addPages()
    {
        if (this.page == null)
        {
            //TODO: RecSize abfragen!
            page = new CopylineContentLoadLimitWizardPage("loadLimit");
            addPage(page);
        }
    }

    public boolean performFinish()
    {
        this.loadLimit = page.getLoadLimit();
        return true;
    }


    public int getLoadLimit()
    {
        return this.loadLimit;
    }

}