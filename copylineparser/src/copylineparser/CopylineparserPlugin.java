package copylineparser;

import org.eclipse.ui.plugin.*;
import org.osgi.framework.BundleContext;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class CopylineparserPlugin extends AbstractUIPlugin
{
    
    public static final String ID = "CopylineParserPlugin";
    public static final String VERSION = "1.2.9";
    
    // The shared instance.
    private static CopylineparserPlugin plugin;

    // Resource bundle.
    private ResourceBundle resourceBundle;

    /**
     * The constructor.
     */
    public CopylineparserPlugin()
    {
        super();
        plugin = this;
    }

    /**
     * This method is called upon plug-in activation
     */
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    public void stop(BundleContext context) throws Exception
    {
        super.stop(context);
        plugin = null;
        resourceBundle = null;
    }

    /**
     * Returns the shared instance.
     */
    public static CopylineparserPlugin getDefault()
    {
        return plugin;
    }

    /**
     * Returns the string from the plugin's resource bundle, or 'key' if not
     * found.
     */
    public static String getResourceString(String key)
    {
        ResourceBundle bundle = CopylineparserPlugin.getDefault()
                .getResourceBundle();
        try
        {
            return (bundle != null) ? bundle.getString(key) : key;
        }
        catch (MissingResourceException e)
        {
            return key;
        }
    }

    /**
     * Returns the plugin's resource bundle,
     */
    public ResourceBundle getResourceBundle()
    {
        try
        {
            if (resourceBundle == null)
                resourceBundle = ResourceBundle
                        .getBundle("copylineparser.CopylineparserPluginResources");
        }
        catch (MissingResourceException x)
        {
            resourceBundle = null;
        }
        return resourceBundle;
    }
}
