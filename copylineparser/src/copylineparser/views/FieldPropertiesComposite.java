package copylineparser.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

/*
 * Created on 31.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldPropertiesComposite extends Composite
{

    private Label labelStufe = null;

    private Text textStufe = null;

    private Label labelName = null;

    private Text textName = null;

    private Label labelStartposition = null;

    private Text textStartposition = null;

    private Text textLaenge = null;

    private Text textSpeicherbedarf = null;

    private Label labelDatentyp = null;

    private Label labelRedefinition = null;

    private Label labelLoadedValue = null;

    private Label labelLaenge = null;

    private Text textRedefinition = null;

    private Text textLoadedValue = null;

    private Button radioButtonGruppe = null;

    private Button radioButtonSchalter = null;

    private Button radioButtonAlphanumerisch = null;

    private Button radioButtonNumerisch = null;

    private Label labelNachkomma = null;

    private Text textNachkommastellen = null;

    private Button checkBoxGepackt = null;

    private Button checkBoxVorzeichen = null;

    private Button checkBoxNullUnterdrueckung = null;

    private Label labelDummy1 = null;

    private Label labelDummy2 = null;

    private Button checkBoxArray = null;

    private Label labelArrayElemAnzahl = null;

    private Text textArrayElemAnzahl = null;

    private Label labelDummy3 = null;

    private Label labelDummy4 = null;

    private Label labelDummy5 = null;

    private Label labelDummy6 = null;

    private Label labelDummy7 = null;
    
    private Label labelDummy8 = null;

    private Label labelSpeicherbedarf = null;

    public FieldPropertiesComposite(Composite parent, int style)
    {
        super(parent, style);
        initialize();
    }

    private void initialize()
    {
        GridData gridData1 = new org.eclipse.swt.layout.GridData();
        GridData gridData151 = new org.eclipse.swt.layout.GridData();
        GridData gridData141 = new org.eclipse.swt.layout.GridData();
        GridData gridData34 = new org.eclipse.swt.layout.GridData();
        GridData gridData32 = new org.eclipse.swt.layout.GridData();
        GridData gridData31 = new org.eclipse.swt.layout.GridData();
        GridData gridData30 = new org.eclipse.swt.layout.GridData();
        GridData gridData29 = new org.eclipse.swt.layout.GridData();
        GridData gridData28 = new org.eclipse.swt.layout.GridData();
        GridData gridData27 = new org.eclipse.swt.layout.GridData();
        GridData gridData26 = new org.eclipse.swt.layout.GridData();
        GridData gridData25 = new org.eclipse.swt.layout.GridData();
        GridData gridData24 = new org.eclipse.swt.layout.GridData();
        GridData gridData23 = new org.eclipse.swt.layout.GridData();
        GridData gridData22 = new org.eclipse.swt.layout.GridData();
        GridData gridData21 = new org.eclipse.swt.layout.GridData();
        GridData gridData20 = new org.eclipse.swt.layout.GridData();
        GridData gridData19 = new org.eclipse.swt.layout.GridData();
        GridData gridData18 = new org.eclipse.swt.layout.GridData();
        GridData gridData17 = new org.eclipse.swt.layout.GridData();
        GridData gridData16 = new org.eclipse.swt.layout.GridData();
        GridData gridData15 = new org.eclipse.swt.layout.GridData();
        GridData gridData14 = new org.eclipse.swt.layout.GridData();
        GridData gridData13 = new org.eclipse.swt.layout.GridData();
        GridData gridData12 = new org.eclipse.swt.layout.GridData();
        GridData gridData11 = new org.eclipse.swt.layout.GridData();
        GridData gridData10 = new org.eclipse.swt.layout.GridData();
        GridData gridData9 = new org.eclipse.swt.layout.GridData();
        GridData gridData8 = new org.eclipse.swt.layout.GridData();
        GridData gridData7 = new org.eclipse.swt.layout.GridData();
        GridData gridData6 = new org.eclipse.swt.layout.GridData();
        GridData gridData5 = new org.eclipse.swt.layout.GridData();
        GridData gridData4 = new org.eclipse.swt.layout.GridData();
        GridData gridData3 = new org.eclipse.swt.layout.GridData();
        GridData gridData2 = new org.eclipse.swt.layout.GridData();
        GridLayout gridLayout1 = new GridLayout();
        labelStufe = new Label(this, SWT.NONE);
        textStufe = new Text(this, SWT.BORDER);
        radioButtonGruppe = new Button(this, SWT.RADIO);
        radioButtonSchalter = new Button(this, SWT.RADIO);
        labelDummy1 = new Label(this, SWT.NONE);
        labelName = new Label(this, SWT.NONE);
        textName = new Text(this, SWT.BORDER);
        labelStartposition = new Label(this, SWT.NONE);
        textStartposition = new Text(this, SWT.BORDER);
        labelDummy3 = new Label(this, SWT.NONE);
        labelDummy5 = new Label(this, SWT.NONE);
        labelDummy4 = new Label(this, SWT.NONE);
        labelLaenge = new Label(this, SWT.NONE);
        textLaenge = new Text(this, SWT.BORDER);
        labelSpeicherbedarf = new Label(this, SWT.NONE);
        textSpeicherbedarf = new Text(this, SWT.BORDER | SWT.READ_ONLY);
        labelDummy8 = new Label(this, SWT.NONE);
        labelDummy6 = new Label(this, SWT.NONE);
        checkBoxArray = new Button(this, SWT.CHECK);
        labelArrayElemAnzahl = new Label(this, SWT.NONE);
        textArrayElemAnzahl = new Text(this, SWT.BORDER);
        labelDummy7 = new Label(this, SWT.NONE);
        labelDatentyp = new Label(this, SWT.NONE);
        radioButtonAlphanumerisch = new Button(this, SWT.RADIO);
        radioButtonNumerisch = new Button(this, SWT.RADIO);
        labelNachkomma = new Label(this, SWT.NONE);
        textNachkommastellen = new Text(this, SWT.BORDER);
        labelDummy2 = new Label(this, SWT.NONE);
        checkBoxGepackt = new Button(this, SWT.CHECK);
        checkBoxVorzeichen = new Button(this, SWT.CHECK);
        checkBoxNullUnterdrueckung = new Button(this, SWT.CHECK);
        labelRedefinition = new Label(this, SWT.NONE);
        textRedefinition = new Text(this, SWT.BORDER);
        labelLoadedValue = new Label(this, SWT.NONE);
        textLoadedValue = new Text(this, SWT.BORDER);
        labelSpeicherbedarf.setText("Speicherbedarf");
        labelArrayElemAnzahl.setText("Elementanzahl");
        labelArrayElemAnzahl.setLayoutData(gridData27);
        checkBoxArray.setText("Array");
        checkBoxArray.setLayoutData(gridData26);
        gridData26.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData26.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData26.grabExcessHorizontalSpace = false;
        gridData27.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData27.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData27.grabExcessHorizontalSpace = false;
        gridData28.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData28.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData28.grabExcessHorizontalSpace = false;
        textArrayElemAnzahl.setLayoutData(gridData28);
        radioButtonGruppe.setText("Gruppe");
        radioButtonSchalter.setText("Schalter");
        radioButtonSchalter.setLayoutData(gridData16);
        this.setLayout(gridLayout1);
        gridLayout1.numColumns = 5;
        gridData2.horizontalSpan = 1;
        gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData2.grabExcessHorizontalSpace = true;
        textStufe.setLayoutData(gridData2);
        gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelStufe.setLayoutData(gridData3);
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelName.setLayoutData(gridData4);
        gridData5.horizontalSpan = 4;
        gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = false;
        textName.setLayoutData(gridData5);
        gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData6.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelStartposition.setLayoutData(gridData6);
        gridData7.horizontalSpan = 1;
        gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData7.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData7.grabExcessHorizontalSpace = false;
        textStartposition.setLayoutData(gridData7);
        gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData8.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelLaenge.setLayoutData(gridData8);
        gridData9.horizontalSpan = 1;
        gridData9.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData9.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData9.grabExcessHorizontalSpace = false;
        textLaenge.setLayoutData(gridData9);
        textLaenge.setToolTipText("Länge bei Ausgabe (Druck od. Anzeige)");
        gridData32.horizontalSpan = 1;
        gridData32.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData32.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData32.grabExcessHorizontalSpace = false;
        textSpeicherbedarf.setLayoutData(gridData32);
        gridData34.horizontalSpan = 1;
        gridData34.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData34.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData34.grabExcessHorizontalSpace = false;
        labelSpeicherbedarf.setLayoutData(gridData34);
        labelSpeicherbedarf
                .setToolTipText("Tatsächlich benötigter Speicherplatz");
        gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData10.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDatentyp.setLayoutData(gridData10);
        gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData11.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        radioButtonGruppe.setLayoutData(gridData11);
        gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData12.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData13.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData14.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData14.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData14.grabExcessHorizontalSpace = false;
        gridData15.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData15.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData15.grabExcessHorizontalSpace = true;
        textNachkommastellen.setLayoutData(gridData15);
        gridData16.horizontalSpan = 1;
        gridData16.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData16.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDummy1.setText("");
        labelDummy1.setLayoutData(gridData25);
        labelDummy2.setText("");
        labelDummy2.setLayoutData(gridData17);
        gridData17.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData17.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData18.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData18.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData19.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData19.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData20.horizontalSpan = 2;
        gridData20.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData20.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData21.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData22.horizontalSpan = 4;
        gridData22.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData22.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData23.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData23.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData24.horizontalSpan = 4;
        gridData24.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData24.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData25.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData25.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData25.grabExcessHorizontalSpace = false;
        radioButtonAlphanumerisch.setText("Text");
        radioButtonAlphanumerisch.setLayoutData(gridData12);
        radioButtonNumerisch.setText("Numerisch");
        radioButtonNumerisch.setLayoutData(gridData13);
        labelStufe.setText("Stufe");
        labelLaenge.setText("Länge");
        labelLaenge
                .setToolTipText("Gesamte Feldlänge inkl. Vorzeichen, Kommazeichen etc.");
        labelDatentyp.setText("Datentyp");
        labelRedefinition.setText("Redefinition von");
        labelRedefinition.setLayoutData(gridData21);
        labelLoadedValue.setText("Geladener Wert");
        labelLoadedValue.setLayoutData(gridData23);
        labelName.setText("Name");
        labelStartposition.setText("Startposition");

        textStartposition.setEditable(false);
        textLoadedValue.setEditable(false);
        textLoadedValue.setFont(new org.eclipse.swt.graphics.Font(
                org.eclipse.swt.widgets.Display.getDefault(), "Courier New", 8,
                org.eclipse.swt.SWT.NORMAL));
        textLoadedValue.setLayoutData(gridData24);
        textRedefinition.setEditable(false);
        textSpeicherbedarf.setEditable(false);
        textSpeicherbedarf.setToolTipText("Angabe in Bytes");
        
        textRedefinition.setLayoutData(gridData22);
        labelNachkomma.setText("Nachkomma");
        labelNachkomma.setLayoutData(gridData14);
        checkBoxGepackt.setText("Gepackt");
        checkBoxGepackt.setLayoutData(gridData18);
        checkBoxVorzeichen.setText("Vorzeichen");
        checkBoxVorzeichen.setLayoutData(gridData19);
        checkBoxNullUnterdrueckung.setText("Führ. 0 Unterdrückung");
        checkBoxNullUnterdrueckung.setLayoutData(gridData20);
        labelDummy3.setText("");
        labelDummy3.setLayoutData(gridData29);
        labelDummy5.setText("");
        labelDummy5.setLayoutData(gridData30);
        gridData29.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData29.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData30.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData30.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData31.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData31.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDummy4.setText("");
        labelDummy4.setLayoutData(gridData31);
        gridData141.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData141.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDummy7.setLayoutData(gridData141);
        gridData151.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData151.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDummy6.setLayoutData(gridData151);
        gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        labelDummy8.setLayoutData(gridData1);
        this.setSize(new org.eclipse.swt.graphics.Point(447, 260));
    }

    public Text getTextLaenge()
    {
        return textLaenge;
    }

    public Text getTextSpeicherbedarf()
    {
        return textSpeicherbedarf;
    }

    public Text getTextLoadedValue()
    {
        return textLoadedValue;
    }

    public Text getTextName()
    {
        return textName;
    }

    public Text getTextRedefinition()
    {
        return textRedefinition;
    }

    public Text getTextStartposition()
    {
        return textStartposition;
    }

    public Text getTextStufe()
    {
        return textStufe;
    }

    public Button getCheckBoxGepackt()
    {
        return checkBoxGepackt;
    }

    public Button getCheckBoxNullUnterdrueckung()
    {
        return checkBoxNullUnterdrueckung;
    }

    public Button getCheckBoxVorzeichen()
    {
        return checkBoxVorzeichen;
    }

    public Button getRadioButtonAlphanumerisch()
    {
        return radioButtonAlphanumerisch;
    }

    public Button getRadioButtonGruppe()
    {
        return radioButtonGruppe;
    }

    public Button getRadioButtonNumerisch()
    {
        return radioButtonNumerisch;
    }

    public Button getRadioButtonSchalter()
    {
        return radioButtonSchalter;
    }

    public Text getTextNachkommastellen()
    {
        return textNachkommastellen;
    }

    public Button getCheckBoxArray()
    {
        return checkBoxArray;
    }

    public Text getTextArrayElemAnzahl()
    {
        return textArrayElemAnzahl;
    }
} // @jve:decl-index=0:visual-constraint="10,10"
