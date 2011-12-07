package copylineparser;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import copylineparser.views.CopylineContentView;
import copylineparser.views.FieldView;
import copylineparser.views.StructureView;

public class ParserPerspective implements IPerspectiveFactory
{
    public static final String ID = "copylineparser.ParserPerspective";

    public void createInitialLayout(IPageLayout layout)
    {
        layout.setEditorAreaVisible(false);
        layout.addView(CopylineContentView.ID, IPageLayout.BOTTOM, new Float(0.8)
                .floatValue(), IPageLayout.ID_EDITOR_AREA);
        
        layout.addView(StructureView.ID, IPageLayout.LEFT, new Float(0.5)
                .floatValue(), IPageLayout.ID_EDITOR_AREA);
        
        layout.addView(FieldView.ID, IPageLayout.RIGHT, new Float(0.4)
                .floatValue(), IPageLayout.ID_EDITOR_AREA);
        
    }
}
