package de.dengot.copylineeditor;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class CopylineEditorPlugin extends AbstractUIPlugin
{
	public static final String ID = "de.dengot.copylineeditor";

	private static final String RESOURCE_BUNDLE_NAME = "CopylineEditorMessages";

	private ResourceBundle resourceBundle;

	private ContributionTemplateStore templateStore;

	private ContributionContextTypeRegistry contextTypeRegistry;

	// The shared instance.
	private static CopylineEditorPlugin plugin;

	/**
	 * The constructor.
	 */
	public CopylineEditorPlugin()
	{
		plugin = this;
		try
		{
			this.resourceBundle = ResourceBundle.getBundle(ID + "."
					+ RESOURCE_BUNDLE_NAME);
		}
		catch (MissingResourceException mre)
		{
			this.resourceBundle = null;
		}
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
	}

	/**
	 * Returns the shared instance.
	 */
	public static CopylineEditorPlugin getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
	}

	public static String getResourceString(String key)
	{
		ResourceBundle bundle = CopylineEditorPlugin.getDefault()
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

	public ResourceBundle getResourceBundle()
	{
		return resourceBundle;
	}

	public TemplateStore getTemplateStore()
	{
		if (templateStore == null)
		{
			templateStore = new ContributionTemplateStore(
					getContextTypeRegistry(),
					getDefault().getPreferenceStore(), "templates");
			try
			{
				templateStore.load();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return templateStore;
	}

	public ContextTypeRegistry getContextTypeRegistry()
	{
		if (contextTypeRegistry == null)
		{
			contextTypeRegistry = new ContributionContextTypeRegistry();
		}
		return contextTypeRegistry;
	}
}
