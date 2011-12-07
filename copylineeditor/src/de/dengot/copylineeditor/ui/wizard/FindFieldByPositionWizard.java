/*
 * Created on 06.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.ui.wizard;

import org.eclipse.jface.wizard.Wizard;

public class FindFieldByPositionWizard extends Wizard
{
	private FindFieldByPositionWizardPage page;

	private int positionToFind = 0;

	public FindFieldByPositionWizard()
	{
		super();
		this.setWindowTitle("Feldsuche Assistent");
	}

	public void addPages()
	{
		if (this.page == null)
		{
			page = new FindFieldByPositionWizardPage("findFieldByPosition");
			addPage(page);
		}
	}

	public boolean performFinish()
	{
		this.positionToFind = page.getPositionToFind();
		return true;
	}

	public boolean hasPositionToFind()
	{
		return this.positionToFind > 0;
	}

	public int getPositionToFind()
	{
		return this.positionToFind;
	}

}
