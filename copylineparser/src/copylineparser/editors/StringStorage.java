/*
 * Created on 10.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.editors;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StringStorage
{
    private String name;

    private String source;

    private IPath path;

    public StringStorage(String name, String source)
    {
        this.name = name;
        this.source = source;
    }

    public String toString()
    {
        return this.source;
    }

    public void setPath(IPath path)
    {
        this.path = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IStorage#getContents()
     */
    public InputStream getContents()
    {
        return new StringBufferInputStream(source);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IStorage#getFullPath()
     */
    public IPath getFullPath()
    {
        if (this.path == null)
            return new Path(System.getProperty("user.dir") + "\\"
                    + this.getName());
        else
            return this.path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IStorage#getName()
     */
    public String getName()
    {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IStorage#isReadOnly()
     */
    public boolean isReadOnly()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        return null;
    }
}
