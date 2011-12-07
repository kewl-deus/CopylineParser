/*
 * Created on 20.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import copylineparser.logic.model.cobol.Field;

/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class FieldTreeLabelProvider extends LabelProvider
{
    
    public String getText(Object obj)
    {
        String str = obj.toString();
        
        try
        {
            Field field = (Field) obj;
            
            if (field.isRedefinition())
            {
                str += " REDEFINES " + field.getRedefinedField().getName();
            }
            
            if (field.isArray())
            {
                str += " [" + field.getOccurence() + "]";
            }
        }
        catch (ClassCastException e)
        {
        }
        
        return str;
    }

    public Image getImage(Object obj)
    {
        String imageKey = ISharedImages.IMG_OBJ_FILE;
        
        try
        {
            Field field = (Field) obj;
            
            if (field.isGroup())
            {
                imageKey = ISharedImages.IMG_OBJ_FOLDER;
            }
            
            if (field.hasDefaultValue())
            {
            }
            
            if (field.isArray())
            {
            }
            
            if (field.isSwitchGroup())
            {
                imageKey = ISharedImages.IMG_OBJ_ELEMENT;
            }
            
            if (field.isSwitch())
            {
                imageKey = ISharedImages.IMG_TOOL_UP;
            }
        }
        catch (ClassCastException e)
        {
        }
        
        
        return PlatformUI.getWorkbench().getSharedImages().getImage(
                imageKey);
    }
}
