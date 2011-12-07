/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import copylineparser.views.TokenStreamView;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ScannerPerspective implements IPerspectiveFactory
{

    public static final String ID = "copylineparser.ScannerPerspective";

    public void createInitialLayout(IPageLayout layout)
    {
        layout.setEditorAreaVisible(true);
        layout.addView(TokenStreamView.ID, IPageLayout.BOTTOM, new Float(0.7)
                .floatValue(), IPageLayout.ID_EDITOR_AREA);
    }

}
