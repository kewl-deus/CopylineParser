package de.sastry.coboleditor.wizard;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

public class COBobjCreator {
	
	static String CODE_FULLQUALIFIED_BUILDER_ID = "CODE_Core.CODE_Builder";
	static String CODE_FULLQUALIFIED_NATURE_ID = "CODE_Core.CODENature";
	static String includes_cpbk="N";
    //$NON-NLS-1$ //$NON-NLS-2$
    
	public COBobjCreator() {
		// TODO Auto-generated constructor stub
	}

	public static IProject createProject(String projectName, URI location,String pgmname,String Extn) {
		// TODO Auto-generated method stub
		System.out.println("came here to print:"+projectName+" "+location);
	    Assert.isNotNull(projectName);
	    Assert.isTrue(projectName.trim().length() > 0);
	    
	    List<String> where = new ArrayList<String>();
	    //String[] paths={""};
	    IProject project = createBaseProject(projectName, location);
	    try {
	    	  String srcfolder="";
	    	  if ( Extn.equals("COB") )
	    	  {
	    		  srcfolder="src/";
	    		  where.add(srcfolder);
	    	  }
	    	  if ( Extn.equals("PCO") )
	    	  {
	    		  srcfolder="dbora/pco/";
	    		  where.add(srcfolder);
	    	  }
	    	  for (int i=1;i < where.size();i++ )
	    	  {
//	              addToProjectStructure(project, paths);
			      IFolder etcFolders = project.getFolder(where.get(i));
			      createFolder(etcFolders);	    		  
	    	  }
	    	  if ( ! Extn.equals("") )
	    	  {	    	  
	             //manna
		         createFiles(project,pgmname,Extn,srcfolder);
	             //manna
	    	  }
		      // add CODE Builder
	          addBuilder(project, CODE_FULLQUALIFIED_BUILDER_ID);
	          // add CODE Nature
	          addNature(project, CODE_FULLQUALIFIED_NATURE_ID);
	    }
	    catch (CoreException e) {
	      e.printStackTrace();
	      project = null;
	    }
	    
	    return project;		
	}
	
	private static IProject createBaseProject(String projectName, URI location) {
		    // it is acceptable to use the ResourcesPlugin class
		    IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		    
		    if (!newProject.exists()) {
		      URI projectLocation = location;
		      IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
		      if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
		        projectLocation = null;
		      }
		      
		      desc.setLocationURI(projectLocation);
		      try {
		        newProject.create(desc, null);
		        if (!newProject.isOpen()) {
		          newProject.open(null);
		        }
		      }
		      catch (CoreException e) {
		        e.printStackTrace();
		      }
		    }
		    
		    return newProject;
		  }
	  /*
	  private static void addToProjectStructure(IProject newProject, String[] string) throws CoreException {
		  for (String path : string) {
		      IFolder etcFolders = newProject.getFolder(path);
		      createFolder(etcFolders);
		    }
		  }
	  */
	    private static void createFolder(IFolder folder) throws CoreException {
		    IContainer parent = folder.getParent();
		    if (parent instanceof IFolder) {
		      createFolder((IFolder) parent);
		    }
		    if (!folder.exists()) {
		      folder.create(false, true, null);
		    }
		}
	  
	    //manna
		static void createFiles(IProject project,String pgmname,String Extn,String srcfolder) throws CoreException{
			IFolder folder = null;
            try
            {
			   folder = project.getFolder(srcfolder);
            }
            catch (Exception e)
            {
            	System.out.println("COBobjCreator:No Valid Project with src directory Selected!!");
            };
            if ( folder == null || !folder.exists() )
            {
                  //addToProjectStructure(project, srcfolder);
            	  System.out.println("source folder is :"+srcfolder);
			      folder = project.getFolder(srcfolder);
			      createFolder(folder);		       
            }
            System.out.println("got folder going to create file");
			IFile file = folder.getFile(pgmname+"."+Extn);
			String templatePath = "/resources/XXXXXX.txt";
			createFile(file, templatePath,pgmname);
		}
		
		public static void createFile(IFile file, String templatePath,String pgmname) throws CoreException {
			InputStream inputStream = null;
			BufferedReader input = null;
			StringBuilder sbuilder = null;
			String str="";

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();		
			inputStream = classloader.getResourceAsStream(templatePath);
			try {
				input = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				sbuilder = new StringBuilder();
				try { str = input.readLine(); } catch (IOException e) {	/* TODO Auto-generated catch block*/	e.printStackTrace(); }
				//System.out.println("str:"+str);
				String Printedline;
				while ( str != null) {
					   String[] lin_dets = str.split(":");
					   //System.out.println(lin_dets[0]);
					   Printedline="N";
					   if ( lin_dets[0].equals("ALL") )
					   {
					      sbuilder.append(lin_dets[1]);
					      Printedline="Y";
					   }
					   try { str = input.readLine(); } catch (IOException e) { e.printStackTrace(); };
					   if (Printedline.equals("Y")) { sbuilder.append("\n"); }
				}
			} catch (UnsupportedEncodingException e) { System.out.println("Problem reading buffer of input stream");e.printStackTrace(); }
			String string = sbuilder.toString();
			string=string.replace("[PROGNAME]", pgmname);
			inputStream = new ByteArrayInputStream(string.getBytes(Charset.forName("UTF-8")));
			if (!file.exists()) {
				try {
					file.create(inputStream, IResource.NONE, null);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		}
	//manna	  
		private static void addBuilder(IProject project, String codeBuilderId) throws CoreException {
			System.out.println(new Exception().getStackTrace()[0].getMethodName());
			AbstractIncrementalProjectBuilder.addBuilder(project,CODE_FULLQUALIFIED_BUILDER_ID, true, null);
		}
		
		private static void addNature(IProject project, final String NATURE_ID) throws CoreException {
			if (!project.hasNature(NATURE_ID)) {
			   IProjectDescription description = project.getDescription();
			   String[] prevNatures = description.getNatureIds();
			   String[] newNatures = new String[prevNatures.length + 1];
			   System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			   newNatures[prevNatures.length] = NATURE_ID;
			   description.setNatureIds(newNatures);
			   IProgressMonitor monitor = null;
			   project.setDescription(description, IProject.AVOID_NATURE_CONFIG, monitor);
			}
	    }		
}
