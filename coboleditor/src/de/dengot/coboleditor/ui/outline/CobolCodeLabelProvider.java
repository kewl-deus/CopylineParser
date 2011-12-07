package de.dengot.coboleditor.ui.outline;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.dengot.coboleditor.CobolEditorPlugin;
import de.dengot.coboleditor.model.CobolCodeElement;
import de.dengot.coboleditor.model.CobolDivision;
import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.model.CobolSection;
import de.dengot.coboleditor.model.CobolSectionElement;

class CobolCodeLabelProvider extends LabelProvider
{
	private final Image fProgramIcon = createImage("class_obj.gif");

	private final Image fDivisionIcon = createImage("parameter_folder.gif");

	private final Image fSectionIcon = createImage("toc_open.gif");

	public Image createImage(String icon)
	{
		return CobolEditorPlugin.getImageDescriptor("icons/" + icon)
				.createImage();
	}

	public String getText(Object element)
	{
		return ((CobolCodeElement) element).getName();
	}

	public Image getImage(Object element)
	{

		if (element instanceof CobolProgram)
			return fProgramIcon;
		if (element instanceof CobolDivision)
			return fDivisionIcon;
		if (element instanceof CobolSection)
			return fSectionIcon;
		else if (element instanceof CobolSectionElement)
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		return super.getImage(element);
	}

	public void dispose()
	{
		if (fProgramIcon != null)
			fProgramIcon.dispose();
		if (fDivisionIcon != null)
			fDivisionIcon.dispose();
		if (fSectionIcon != null)
			fSectionIcon.dispose();
		super.dispose();
	}
}