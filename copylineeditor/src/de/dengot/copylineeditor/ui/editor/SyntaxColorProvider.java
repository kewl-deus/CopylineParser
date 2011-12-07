/*
 * Created on 07.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.dengot.copylineeditor.ui.editor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SyntaxColorProvider
{
	public final RGB MULTI_LINE_COMMENT = new RGB(0, 128, 0);

	public final RGB SINGLE_LINE_COMMENT = new RGB(0, 128, 0);

	public final RGB KEYWORD = new RGB(0, 0, 128);

	public final RGB CONSTANT = new RGB(128, 0, 0);

	public final RGB STRING = new RGB(0, 0, 128);

	public final RGB DEFAULT = new RGB(0, 0, 0);

	protected Map<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	/**
	 * Release all of the color resources held onto by the receiver.
	 */
	public void dispose()
	{
		Iterator<Color> e = fColorTable.values().iterator();
		while (e.hasNext())
			(e.next()).dispose();
	}

	/**
	 * Return the Color that is stored in the Color table as rgb.
	 */
	public Color getColor(RGB rgb)
	{
		Color color = fColorTable.get(rgb);
		if (color == null)
		{
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}
}
