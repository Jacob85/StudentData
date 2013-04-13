package il.ac.shenkar.studentdata;


import java.io.File;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class FileSystemHandler
{
	private static FileSystemHandler instance;
	private String pathBarier = "/";	
	private String currentWorkingPath = "/var/lib/openshift/514b02865973ca7a4b000087/app-root/data";
	private final String homeDirectory = "Files";
	
	
	private FileSystemHandler()
	{
	/*	try {
			currentWorkingPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	//Singleton File System Handler
	public static FileSystemHandler getInstande()
	{
		if (FileSystemHandler.instance == null)
			instance = new FileSystemHandler();
		return instance;
	}
	
	public int saveFile(FileItem file, String path)
	{
		String[] FolderHierarchy = path.split(pathBarier);
		String absuotePath = hierarchyBuilder(FolderHierarchy);
		
		try {
			file.write(new File(absuotePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	private String hierarchyBuilder(String [] hierarchy)
	{
		// Initialize the path builder with the current directory + /
		StringBuilder path = new StringBuilder();
		path.append(currentWorkingPath);
		path.append(pathBarier);
		path.append(homeDirectory);
		
		//opening the homeDirectory
		File currFile = new File(path.toString());
		
		// if the home directory does not exists create it
		if (!currFile.exists())
			currFile.mkdir();
		
		// go over the hierarchy and create the path
		for (int index =0; index< hierarchy.length -1; index++)
		{
			path.append(pathBarier);
			path.append(hierarchy[index]);
			currFile = new File(path.toString());
			
			if (!currFile.exists())
				currFile.mkdir();
			
		}
		
		//add the file name to the path
		path.append(pathBarier);
		path.append(hierarchy[hierarchy.length-1]);
		
		//now my path look like this: currentWorkingPath/homeDirectory/hierarchy[index]/hierarchy[index]/.../filename.txt
		return path.toString();
	}
	
	
	// the path received in this method look like this "uni/trend/year/course/filename.pdf"
	public File getFile(String path) throws NullPointerException 
	{
		if (path == null)
			throw new NullPointerException("path recived is null");
		
		// create the absolute path to the file
		StringBuilder absuotePath = new StringBuilder();
		absuotePath.append(currentWorkingPath + pathBarier + homeDirectory);
		absuotePath.append(pathBarier + path);
		
		//try to open the file
		File toReturn = new File(absuotePath.toString());
		if (toReturn.exists() && toReturn.isFile())
			return toReturn;
		
		return null;
	}
	
	public boolean deleteFile (String path) throws NullPointerException
	{
		if (path == null)
			throw new NullPointerException("path is null");
		
		// create the absolute path to the file
		StringBuilder absuotePath = new StringBuilder();
		absuotePath.append(currentWorkingPath + pathBarier + homeDirectory);
		absuotePath.append(pathBarier + path);
		
		File toDelete = new File(absuotePath.toString());
		if (toDelete.exists() && toDelete.isFile())
		{
			toDelete.delete();
			return true;
		}
		
		return false;
	}
	
}
