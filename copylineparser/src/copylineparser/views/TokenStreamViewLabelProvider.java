/*
 * Created on 03.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import copylineparser.logic.token.Token;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TokenStreamViewLabelProvider extends LabelProvider implements
        ITableLabelProvider
{

    public Image getColumnImage(Object element, int columnIndex)
    {
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        switch (columnIndex)
        {
        case 0:
            return ((Token) element).getTypeName();
        case 1:
            return ((Token) element).getContents();
        case 2:
            return String.valueOf(((Token) element).getPosition());

        }
        return "";

    }

}
