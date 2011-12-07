/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ValueTabLabelProvider extends LabelProvider
{
    public String getText(Object obj)
    {
        String str = obj.toString();
        return str;
    }

    public Image getImage(Object obj)
    {
//        String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//
//        if (obj instanceof FieldValueRange)
//        {
//            imageKey = ISharedImages.IMG_OBJ_FOLDER;
//        }
//
//        return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
        return null;
    }
}
