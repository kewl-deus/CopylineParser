/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors.java;

import org.eclipse.ui.texteditor.AbstractTextEditor;

import copylineparser.editors.TextDocumentProvider;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaEditor extends AbstractTextEditor
{
    public static final String ID = "copylineparser.editors.java.JavaEditor";
    
    public JavaEditor()
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
        setDocumentProvider(new TextDocumentProvider());
        setSourceViewerConfiguration(new JavaSourceViewerConfiguration());
    }
}