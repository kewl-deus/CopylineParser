package copylineparser.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import copylineparser.logic.model.cobol.Field;
import copylineparser.logic.model.cobol.FieldType;
import copylineparser.logic.model.cobol.NumericFieldType;

public class FieldView extends ViewPart implements ISelectionChangedListener
{

    public static final String ID = "copylineparser.views.FieldView";

    private FieldViewComposite viewComposite;

    private Field currentField = null;

    public void createPartControl(Composite parent)
    {
        // register for Fieldselection-Changes in StructureView
        StructureView structureView = (StructureView) this.getViewSite()
                .getPage().findView(StructureView.ID);
        structureView.addSelectionChangedListener(this);

        parent.setLayout(new FillLayout());

        this.viewComposite = new FieldViewComposite(parent, SWT.NONE);

        this.viewComposite.getButtonSave().addSelectionListener(
                new SelectionListener()
                {
                    public void widgetSelected(SelectionEvent e)
                    {
                        saveField();
                    }

                    public void widgetDefaultSelected(SelectionEvent e)
                    {
                    }
                });

        this.viewComposite.getButtonRestore().addSelectionListener(
                new SelectionListener()
                {
                    public void widgetSelected(SelectionEvent e)
                    {
                        restoreField();
                    }

                    public void widgetDefaultSelected(SelectionEvent e)
                    {
                    }
                });
    }

    public void setFocus()
    {
        this.viewComposite.setFocus();
    }

    public void dispose()
    {
        super.dispose();
    }

    public void refresh()
    {
        this.setInput(this.currentField);
    }

    public void saveField()
    {
        //TODO: es werden noch nicht alle Felder der Oberfläche ausgewertet!
        try
        {
            FieldPropertiesComposite fprop = this.viewComposite
                    .getFieldPropertiesComposite();

            this.currentField.setName(fprop.getTextName().getText());

            int newStepNumber = Integer
                    .parseInt(fprop.getTextStufe().getText());
            int parentStepNumber = this.currentField.hasParent() ? this.currentField
                    .getParent().getStepNumber()
                    : 0;
            if (newStepNumber <= parentStepNumber)
            {
                newStepNumber = parentStepNumber + 1;
                MessageDialog
                        .openError(
                                this.getViewSite().getShell(),
                                "Fehler beim Ändern der Stufennummer",
                                "Die eingegebene Stufennummer ist kleiner oder gleich der Stufennummer des übergeordneten Feldes.");
            }
            else
            {
                this.currentField.setStepNumber(newStepNumber);
            }

            int newLength = Integer.parseInt(fprop.getTextLaenge().getText());
            this.currentField.setLength(newLength);

            this.currentField.setCommentary(this.viewComposite
                    .getFieldCommentaryComposite().getTextAreaCommentary()
                    .getText());

            // refresh GUI
            this.refresh();
            StructureView structureView = (StructureView) this.getViewSite()
                    .getPage().findView(StructureView.ID);
            structureView.refresh(this.currentField);
        }
        catch (Exception e)
        {
            MessageDialog.openError(this.getViewSite().getShell(),
                    "Fehler beim Speichern", e.getMessage());
        }

    }

    public void restoreField()
    {
        this.setInput(this.currentField);
    }

    public void setInput(Field field)
    {
        this.currentField = field;

        FieldPropertiesComposite fprop = this.viewComposite
                .getFieldPropertiesComposite();
        if (field == null)
        {
            fprop.getTextStufe().setText("");
            fprop.getTextName().setText("");
            fprop.getTextStartposition().setText("");
            fprop.getTextLaenge().setText("");
            fprop.getTextSpeicherbedarf().setText("");
            fprop.getTextRedefinition().setText("");
            fprop.getTextLoadedValue().setText("");
            
            fprop.getRadioButtonGruppe().setSelection(false);
            fprop.getRadioButtonGruppe().setSelection(false);
            
            fprop.getCheckBoxGepackt().setEnabled(false);
            fprop.getCheckBoxVorzeichen().setEnabled(false);
            fprop.getCheckBoxNullUnterdrueckung().setEnabled(false);
            fprop.getTextNachkommastellen().setEnabled(false);
            fprop.getCheckBoxGepackt().setSelection(false);
            fprop.getCheckBoxVorzeichen().setSelection(false);
            fprop.getCheckBoxNullUnterdrueckung().setSelection(false);
            fprop.getTextNachkommastellen().setText("");
            
            
            this.viewComposite.getFieldCommentaryComposite()
                    .getTextAreaCommentary().setText("");
            this.viewComposite.getFieldValuesComposite().getValueList()
                    .removeAll();

            return;
        }
        // else
        fprop.getTextStufe().setText(String.valueOf(field.getStepNumber()));
        fprop.getTextName().setText(field.getName());
        fprop.getTextStartposition().setText(
                String.valueOf(field.getStartPosition()));
        fprop.getTextLaenge().setText(String.valueOf(field.length()));
        fprop.getTextSpeicherbedarf().setText(String.valueOf(field.sizeInBytes()));
        fprop.getTextLoadedValue().setText(field.getContent(false));

        // Schalter od. Gruppe (beides gleichzeitig gibt es nicht)
        fprop.getRadioButtonGruppe().setSelection(field.isGroup());
        fprop.getRadioButtonSchalter().setSelection(field.isSwitch());

        // Feldtyp
        FieldType type = field.getType();
        boolean isAlphanumericType = type == null || type.isAlphanumericType();
        boolean isNumericType = type != null && type.isNumericType();
        fprop.getRadioButtonAlphanumerisch().setSelection(isAlphanumericType);
        fprop.getRadioButtonNumerisch().setSelection(isNumericType);

        fprop.getCheckBoxGepackt().setEnabled(isNumericType);
        fprop.getCheckBoxVorzeichen().setEnabled(isNumericType);
        fprop.getCheckBoxNullUnterdrueckung().setEnabled(isNumericType);
        fprop.getTextNachkommastellen().setEnabled(isNumericType);
        if (isNumericType)
        {
            NumericFieldType numType = (NumericFieldType) type;
            fprop.getCheckBoxGepackt().setSelection(numType.isPacked());
            fprop.getCheckBoxVorzeichen().setSelection(numType.isSigned());
            fprop.getCheckBoxNullUnterdrueckung().setSelection(
                    numType.isSuppressLeadingZeroes());
            fprop.getTextNachkommastellen().setText(
                    String.valueOf(numType.getPostCommaLength()));
        }
        else
        {
            // clean
            fprop.getCheckBoxGepackt().setSelection(false);
            fprop.getCheckBoxVorzeichen().setSelection(false);
            fprop.getCheckBoxNullUnterdrueckung().setSelection(false);
            fprop.getTextNachkommastellen().setText("");
        }

        //Array
        fprop.getCheckBoxArray().setSelection(field.isArray());
        fprop.getTextArrayElemAnzahl().setText(
                String.valueOf(field.getOccurence()));
        
        //Redefinition
        if (field.isRedefinition())
        {
            fprop.getTextRedefinition().setText(
                    field.getRedefinedField().getName());
        }
        else
        {
            fprop.getTextRedefinition().setText("");
        }

        fprop.getTextLaenge().setEditable(!field.isGroup());

        this.viewComposite.getFieldCommentaryComposite()
                .getTextAreaCommentary().setText(field.getCommentary());
        this.viewComposite.getFieldValuesComposite().getValueListViewer()
                .setInput(field);
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
        ISelection selection = event.getSelection();
        Object obj = ((IStructuredSelection) selection).getFirstElement();
        Field field = (Field) obj;
        this.setInput(field);
    }
}

// private FormToolkit toolkit;
//
// private ScrolledForm form;
//
// private Text textFeldname;
//
// private Text textFeldstufe;
//
// private Text textFeldtyp;
//
// private Text textFeldStartpos;
//
// private Text textFeldlaenge;
//
// private Text textRedefinition;
//
// private Text textLoadedValue;
//
// private Label commentaryClient;
//
// private ExpandableComposite commentaryComposite;
//
// private TableViewer valueTabViewer;
//
// public void createPartControl(Composite parent)
// {
// // register for Fieldselection-Changes in StructureView
// StructureView structureView = (StructureView) this.getViewSite()
// .getPage().findView(StructureView.ID);
// structureView.addSelectionChangedListener(this);
//
// toolkit = new FormToolkit(parent.getDisplay());
// // form = toolkit.createForm(parent);
// form = new ScrolledForm(parent);
// FormColors colors = new FormColors(parent.getDisplay());
// form.setBackground(colors.getBackground());
// form.setForeground(colors.getColor(FormColors.TITLE));
// form.setFont(JFaceResources.getHeaderFont());
//
// form.setText("Feldeigenschaften");
//
// TableWrapData td;
// TableWrapLayout formLayout = new TableWrapLayout();
// form.getBody().setLayout(formLayout);
// formLayout.numColumns = 1;
//
// Composite detailComposite = toolkit.createComposite(form.getBody(),
// SWT.WRAP);
// td = new TableWrapData(TableWrapData.FILL);
// td.colspan = 1;
// detailComposite.setLayoutData(td);
// detailComposite.setLayout(new GridLayout(2, false));
// Label labelFeldstufe = toolkit.createLabel(detailComposite, "Stufe");
// textFeldstufe = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelFeldname = toolkit.createLabel(detailComposite, "Name");
// textFeldname = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelFeldStartpos = toolkit.createLabel(detailComposite,
// "Startposition");
// textFeldStartpos = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelFeldlaenge = toolkit.createLabel(detailComposite, "Länge");
// textFeldlaenge = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelFeldtyp = toolkit.createLabel(detailComposite, "Typ");
// textFeldtyp = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelRedefinition = toolkit.createLabel(detailComposite,
// "Redefinition von");
// textRedefinition = toolkit.createText(detailComposite,
// "______________________________________________");
// Label labelLoadedValue = toolkit.createLabel(detailComposite,
// "Geladener Wert");
// textLoadedValue = toolkit.createText(detailComposite,
// "______________________________________________");
//
// textFeldstufe.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textFeldname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textFeldStartpos.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textFeldlaenge.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textFeldtyp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textRedefinition.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
// textLoadedValue.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//
// // Commentary
// commentaryComposite = toolkit.createExpandableComposite(form.getBody(),
// ExpandableComposite.TREE_NODE);
// commentaryComposite.setText("Kommentar");
// commentaryClient = toolkit.createLabel(commentaryComposite, "",
// SWT.WRAP);
// commentaryComposite.setClient(commentaryClient);
// td = new TableWrapData(TableWrapData.FILL);
// td.colspan = 1;
// commentaryComposite.setLayoutData(td);
// commentaryComposite.addExpansionListener(new ExpansionAdapter()
// {
// public void expansionStateChanged(ExpansionEvent e)
// {
// form.reflow(true);
// }
// });
//
// // FieldValues
// Section section = toolkit.createSection(form.getBody(),
// Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED);
// td = new TableWrapData(TableWrapData.FILL);
// td.colspan = 1;
// section.setLayoutData(td);
// section.addExpansionListener(new ExpansionAdapter()
// {
// public void expansionStateChanged(ExpansionEvent e)
// {
// form.reflow(true);
// }
// });
// section.setText("Vordefinierte Werte");
// toolkit.createCompositeSeparator(section);
// // section.setDescription("Fest codierte Feldwerte");
// section.marginWidth = 10;
// section.marginHeight = 5;
// toolkit.createCompositeSeparator(section);
// Composite sectionClient = toolkit.createComposite(section, SWT.WRAP);
// GridLayout clientLayout = new GridLayout();
// clientLayout.numColumns = 1;
// clientLayout.marginWidth = 0;
// clientLayout.marginHeight = 0;
// sectionClient.setLayout(clientLayout);
// Table valueTab = toolkit.createTable(sectionClient, SWT.NULL);
// GridData gd = new GridData(GridData.FILL_BOTH);
// gd.heightHint = 20;
// gd.widthHint = 100;
// valueTab.setLayoutData(gd);
// toolkit.paintBordersFor(sectionClient);
// section.setClient(sectionClient);
//
// valueTabViewer = new TableViewer(valueTab);
// valueTabViewer.setContentProvider(new ValueTabContentProvider());
// valueTabViewer.setLabelProvider(new ValueTabLabelProvider());
//
// }
//
// public void setFocus()
// {
// form.setFocus();
// }
//
// public void dispose()
// {
// toolkit.dispose();
// super.dispose();
// }
//
// public void setInput(Field field)
// {
// if (field == null)
// {
// this.textFeldstufe.setText("");
// this.textFeldname.setText("");
// this.textFeldStartpos.setText("");
// this.textFeldlaenge.setText("");
// this.textFeldtyp.setText("");
// this.textRedefinition.setText("");
// this.textLoadedValue.setText("");
// this.commentaryClient.setText("");
// commentaryComposite.setEnabled(false);
// this.valueTabViewer.getTable().clearAll();
// }
// else
// {
// this.textFeldstufe.setText(String.valueOf(field.getStepNumber()));
// this.textFeldname.setText(field.getName());
// this.textFeldStartpos.setText(String.valueOf(field
// .getStartPosition()));
// this.textFeldlaenge.setText(String.valueOf(field.length()));
// this.textLoadedValue.setText(field.getContent(false));
//
// // Feldtyp
// String typeDesciption = "";
// try
// {
// if (field.getType().isAlphanumericType())
// typeDesciption = "Alphanumerisch";
// else
// {
// try
// {
// NumericFieldType numType = (NumericFieldType) field
// .getType();
// if (numType.isDecimal())
// {
// typeDesciption = "Dezimal ("
// + numType.getPostCommaLength()
// + " Nachkommastellen)";
// }
// else
// {
// typeDesciption = "Numerisch (Natürliche Zahl)";
// }
// }
// catch (ClassCastException cce)
// {
// typeDesciption = "Numerisch";
// }
// }
// }
// catch (NullPointerException npe)
// {
// if (field.isSwitch())
// typeDesciption = "Schalter";
// else if (field.isGroup())
// typeDesciption = "Gruppe";
// }
// if (field.isArray())
// {
// typeDesciption += " [" + field.getOccurence() + "]";
// }
// this.textFeldtyp.setText(typeDesciption);
// // Ende: Feldtyp
//
// if (field.isRedefinition())
// {
// this.textRedefinition.setText(field.getRedefinedField()
// .getName());
// }
// else
// {
// this.textRedefinition.setText("");
// }
//
// this.commentaryClient.setText(field.getCommentary());
// commentaryComposite.setEnabled(field.hasCommentary());
//
// this.valueTabViewer.setInput(field);
// }
// }
//
// public void selectionChanged(SelectionChangedEvent event)
// {
// ISelection selection = event.getSelection();
// Object obj = ((IStructuredSelection) selection).getFirstElement();
// Field field = (Field) obj;
// this.setInput(field);
// }

