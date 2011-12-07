/*
 * Created on 22.12.2004
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
public class PreProcessorConfigWizard extends Wizard
{
    private PreProcessorConfigWizardPage page;

    private String stringToReplace = "";

    private String replacementValue = "";

    public PreProcessorConfigWizard()
    {
        super();
        this.setWindowTitle("Präprozessor Konfiguration");
    }

    public void addPages()
    {
        if (this.page == null)

        {
            page = new PreProcessorConfigWizardPage("preProcessorConfig");
            addPage(page);
        }
    }

    public boolean performFinish()
    {
        this.stringToReplace = getStringValue(page.getStringToReplace());
        this.replacementValue = getStringValue(page.getReplacementValue());
        return true;
    }

    public boolean hasReplacementJob()
    {
        return this.getStringToReplace().length() > 0;
    }

    public String getReplacementValue()
    {
        return this.replacementValue;
    }

    public String getStringToReplace()
    {
        return this.stringToReplace;
    }

    private String getStringValue(String s)
    {
        if (s == null)
        {
            return "";
        }
        else
        {
            return s;
        }
    }
}
