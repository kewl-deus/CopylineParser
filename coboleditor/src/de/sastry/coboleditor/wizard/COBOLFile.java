package de.sastry.coboleditor.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class COBOLFile extends WizardPage {
  private Text text1;
  private Text text2;
  private String FileExtn;  
  private Composite container;

  public COBOLFile() {
    super("COBOL File details");
    setTitle("COBOL File details");
    setDescription("Details of COBOL program to be added...");
    setControl(text1);
  }

  @Override
  public void createControl(Composite parent) {
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("Program System");

    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
    text1.setText("UT");
    text1.addKeyListener(new KeyListener() { @Override public void keyPressed(KeyEvent e) { /* TODO Auto-generated method stub */ } @Override public void keyReleased(KeyEvent e) { if (!text1.getText().isEmpty()) { setPageComplete(true); } } });
    
    Label label2 = new Label(container, SWT.NONE);
    label2.setText("Program Sequence");

    text2 = new Text(container, SWT.BORDER | SWT.SINGLE);
    text2.setText("0000");
    
    SelectionListener selectionListener = new SelectionAdapter () {
        public void widgetSelected(SelectionEvent event) {
           Button button = ((Button) event.widget);
           FileExtn=button.getText();
           System.out.print(button.getText());
           System.out.println(" selected = " + button.getSelection());
        };
     };    
    
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    text1.setLayoutData(gd);
    Label labelCopy = new Label(container, SWT.NONE);
    labelCopy.setText("Modify Existing Component ?");
    Button BtnCpy = new Button(container, SWT.CHECK);
    BtnCpy.setSelection(true);
    
    Composite composite = new Composite(container, SWT.NULL);
    composite.setLayout(new RowLayout());    
    Button[] radios = new Button[3];
    radios[0] = new Button(composite, SWT.RADIO);
    radios[0].setSelection(true);
    FileExtn="COB";
    radios[0].setText("COB");
    radios[0].setBounds(10, 5, 75, 30);
    radios[0].addSelectionListener(selectionListener);

    radios[1] = new Button(composite, SWT.RADIO);
    radios[1].setText("PCO");
    radios[1].setBounds(10, 30, 75, 30);
    radios[1].addSelectionListener(selectionListener);
    
    radios[2] = new Button(composite, SWT.RADIO);
    radios[2].setText("cbl");
    radios[2].setBounds(10, 30, 75, 30);  
    radios[2].addSelectionListener(selectionListener);

    //check.addSelectionListener(new SelectionAdapter() { @Override public void widgetSelected(SelectionEvent event) { Button btn = (Button) event.getSource(); System.out.println(btn.getSelection()); } });    

    // required to avoid an error in the system
    setControl(container);
    //setPageComplete(false);
  }

  public String getText1() {
    return text1.getText();
  }
  
  public String getText2() {
	return text2.getText();
  }
   
  public String getExtn() {
    return FileExtn;	  
  }
  
  public void checkentry() {
  }    
}