package copylineparser.editors.xml;

import org.eclipse.ui.texteditor.AbstractTextEditor;


public class XMLEditor  extends AbstractTextEditor
{
    public static final String ID = "copylineparser.editors.xml.XMLEditor";
    
    public XMLEditor()
    {
        super();
        internal_init();
    }

    /**
     * Initializes the document provider and source viewer configuration. Called
     * by the constructor. Subclasses may replace this method.
     */
    protected void internal_init()
    {
        configureInsertMode(SMART_INSERT, false);
		setSourceViewerConfiguration(new XMLConfiguration());
		setDocumentProvider(new XMLDocumentProvider());
    }
}
