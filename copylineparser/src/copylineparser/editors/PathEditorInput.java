/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PlatformUI;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PathEditorInput implements IPathEditorInput
{
    private IPath fPath;

    public PathEditorInput(IPath path)
    {
        if (path == null)
        {
            throw new IllegalArgumentException();
        }
        this.fPath = path;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return fPath.hashCode();
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof PathEditorInput))
            return false;
        PathEditorInput other = (PathEditorInput) obj;
        return fPath.equals(other.fPath);
    }

    /*
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists()
    {
        return fPath.toFile().exists();
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor()
    {
        return PlatformUI.getWorkbench().getEditorRegistry()
                .getImageDescriptor(fPath.toString());
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName()
    {
        return fPath.toString();
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText()
    {
        return fPath.makeRelative().toString();
    }

    /*
     * @see org.eclipse.ui.IPathEditorInput#getPath()
     */
    public IPath getPath()
    {
        return fPath;
    }

    /*
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        return null;
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable()
    {
        // no persistence
        return null;
    }
}
