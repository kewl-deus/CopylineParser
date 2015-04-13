package de.sastry.coboleditor.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class COBOLProject extends WizardPage {
	
		  private Text text1;
		  private Text text2;
		  private Text txtLocation;
		  private Composite container;
		  private boolean useDefaultLocation = true;		  

		  public COBOLProject() {
		    super("Project Details");
		    setTitle("Project Details");
		    setDescription("Project Details to be entered here");
		  }

		  @Override
		  public void createControl(Composite parent) {
		    container = new Composite(parent, SWT.NONE);
		    GridLayout layout = new GridLayout();
		    container.setLayout(layout);
		    layout.numColumns = 2;
		    Label label1 = new Label(container, SWT.NONE);
		    label1.setText("Please enter IR no here.");

		    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
		    text1.setText("16413");
		    text1.addKeyListener(new KeyListener() { @Override public void keyPressed(KeyEvent e) { /* TODO Auto-generated method stub */ } @Override public void keyReleased(KeyEvent e) { if (!text1.getText().isEmpty()) { setPageComplete(true); } } });
		    
		    Label label2 = new Label(container, SWT.NONE);
		    label2.setText("Please Enter Description as mentioned in the FS");		    
		    text2 = new Text(container, SWT.MULTI  | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		    text2.setText("Collateral Validations IR");
		    text2.addKeyListener(new KeyListener() { @Override public void keyPressed(KeyEvent e) { /* TODO Auto-generated method stub */ } @Override public void keyReleased(KeyEvent e) { if (!text1.getText().isEmpty()) { setPageComplete(true); } } });
		    
		    Label lblLocation = new Label(container, SWT.NONE);
		    lblLocation.setEnabled(true);
		    lblLocation.setText("&Location:");
		    
		    txtLocation = new Text(container, SWT.BORDER);
		    txtLocation.setText("IR16413");
		    txtLocation.setEnabled(true);
		    
		    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		    text1.setLayoutData(gd);
		    
		    GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		    gridData.heightHint = 5 * text2.getLineHeight();
		    text2.setLayoutData(gridData);

		    // required to avoid an error in the system
		    gridData = new GridData(GridData.FILL_HORIZONTAL);
		    txtLocation.setLayoutData(gridData);
		    
		    setControl(container);
		    setPageComplete(false);

		  }

		  public String getText1() {
		    return text1.getText();
		  }
			  
		  public void setUseDefaultLocation(boolean b) {
			    this.useDefaultLocation = b;
		  }
		  
		  public boolean isUseDefaultLocation() {
			   return this.useDefaultLocation;
		  }
		  
		  public String getLocation() {
				return this.txtLocation.getText().trim();
		  }
}
