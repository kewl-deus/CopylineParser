package de.sastry.coboleditor.wizard;

import java.net.URI;
import java.util.LinkedList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


public class COBProjWizardPage extends Wizard implements INewWizard {

	  protected COBOLProject one;
	  protected COBOLFile two;
	  public LinkedList<IWizardPage> pages = new LinkedList<IWizardPage>();
	
	  public COBProjWizardPage() {
	    super();
	    setNeedsProgressMonitor(true);
	  }

	  @Override
	  public String getWindowTitle() {
	    return "Export My Data";
	  }

	  @Override
	  public void addPages() {
	    one = new COBOLProject();
	    two = new COBOLFile();
	    addPage(one);
	    addPage(two);
	  }

	@Override
	public boolean performFinish() {
	    // Print the result to the console
	    System.out.println(one.getText1());
	    System.out.println(two.getText1()+two.getText2());
	    // create a new COBOL project...
	    //final ProjectPage newProjectPage = (ProjectPage) this.pages.get(0);
	    final COBOLProject newProjectPage = (COBOLProject) this.one;
	    final String name = one.getText1();
	    final String pgmname = two.getText1()+two.getText2();
	    final String Extn = two.getExtn();
	    URI location = null;
	    if (!newProjectPage.isUseDefaultLocation()) {
	    	try{
	             location = new URI(newProjectPage.getLocation());
	    	}
	    	catch(Exception e){System.out.println("didnt find it");}
	    }
	    COBobjCreator.createProject(name, location,pgmname,Extn);	    
	    return true;

	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}		  
}
