/*
 * Created on 14.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.views;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

import copylineparser.CopylineparserPlugin;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GuiUtils
{
    public static URL makeImageURL(String name)
    {
        Location location = Platform.getInstallLocation();
        String path = "plugins/" + CopylineparserPlugin.ID + "_"
                + CopylineparserPlugin.VERSION + "/icons/" + name;
        URL url = null;
        try
        {
            url = new URL(location.getURL(), path);
        }
        catch (MalformedURLException e)
        {
            return null;
        }
        return url;
    }
}
