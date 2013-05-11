package il.ac.shenkar.studentdata;


import java.io.File;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;

public class FileSystemHandler
{
	private static FileSystemHandler instance;
	private String pathBarier = "/";	
	private String currentWorkingPath = /* "C:\\Users\\Jacob\\output";*/"/var/lib/openshift/514b02865973ca7a4b000087/app-root/data";
	private final String homeDirectory = "Files";
	private static Logger logger;
	
	private FileSystemHandler()
	{
		logger = Logger.getLogger(FileSystemHandler.class.getName());
		logger.info("FileSystemHandler was created");
	}
	
	
	//Singleton File System Handler
	public static FileSystemHandler getInstande()
	{
		if (FileSystemHandler.instance == null)
			instance = new FileSystemHandler();
		return instance;
	}
	
	
	// should get path that look like this /<university name>/<Trend name>/<year name>/<course name>
	public int saveFile(FileItem file, String path)
	{
		logger.info("saveFile was called");
		String[] FolderHierarchy = path.split(pathBarier);
		// build the absolute file path hierarchy in the file system 
		String absuotePath = hierarchyBuilder(FolderHierarchy);
		
		try {
			//save the file to his absolute path
			file.write(new File(absuotePath));
			logger.info("File Saved to Fily system: "+absuotePath);
		} catch (Exception e) 
		{
			logger.error("File does not saved, return 0");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**
	 * The Hierarchy Builder function received String [] (of directories names) as a parameter check if all of the elements are exists
	 * if the directory is exists -> check the next directory
	 * else create it and goes to the net directory
	 * @param hierarchy
	 * @return the absolute path to the file: /dir1/dir2/dir3/..../filename.png
	 */
	private String hierarchyBuilder(String [] hierarchy)
	{
		// Initialize the path builder with the current directory + /
		StringBuilder path = new StringBuilder();
		path.append(currentWorkingPath);
		path.append(pathBarier);
		path.append(homeDirectory);
		logger.info("Initialise the path: "+ path.toString());
		
		//opening the homeDirectory
		File currFile = new File(path.toString());
		
		// if the home directory does not exists create it
		if (!currFile.exists())
		{
			logger.info(currFile.getAbsolutePath() + "was created");
			currFile.mkdir();
		}
		// go over the hierarchy and create the path
		// leave the last String (the file name).
		for (int index =0; index< hierarchy.length -1; index++)
		{
			// add the String to the path
			path.append(pathBarier);
			path.append(hierarchy[index]);
			currFile = new File(path.toString());
			
			if (!currFile.exists())
			{
				logger.info(currFile.getAbsolutePath() + "was created");
				currFile.mkdir();
			}
		}
		
		//add the file name to the path
		path.append(pathBarier);
		path.append(hierarchy[hierarchy.length-1]);
		
		//now my path look like this: currentWorkingPath/homeDirectory/hierarchy[index]/hierarchy[index]/.../filename.txt
		logger.info("returning path: "+ path.toString());
		return path.toString();
	}
	
	
	
	/**
	 * access the file system and get the file in order to return to the user
	 *the path received in this method look like this "uni/trend/year/course/filename.pdf"
	 * @param path
	 * @return the File if found, else null
	 * @throws NullPointerException
	 */
	public File getFile(String path) throws NullPointerException 
	{
		logger.info("getFile was called with path: "+path);
		if (path == null)
		{
			logger.error("throw NullPointerException, path recieved was null");
			throw new NullPointerException("path recived is null");
		}
		// create the absolute path to the file
		StringBuilder absuotePath = new StringBuilder();
		absuotePath.append(currentWorkingPath + pathBarier + homeDirectory);
		absuotePath.append(pathBarier + path);
		logger.info("Trying to open file in path: "+absuotePath.toString());
		
		//try to open the file
		File toReturn = new File(absuotePath.toString());
		if (toReturn.exists() && toReturn.isFile())
		{
			logger.info("File was found, return the file");
			return toReturn;
		}
		logger.error("File not found, return null");
		return null;
	}
	
	/**
	 * received path of file to delete and remove it from the File system
	 * the path received in this method look like this "uni/trend/year/course/filename.pdf"
	 * @param path
	 * @return true if deleted, false if failed
	 * @throws NullPointerException
	 */
	public boolean deleteFile (String path) throws NullPointerException
	{
		logger.info("deleteFile was called");
		if (path == null)
		{
			logger.error("Throw NullPointerException, Path recieved was null");
			throw new NullPointerException("path is null");
		}
		// create the absolute path to the file
		StringBuilder absuotePath = new StringBuilder();
		absuotePath.append(currentWorkingPath + pathBarier + homeDirectory);
		absuotePath.append(pathBarier + path);
		logger.info("Tring to open file: "+ absuotePath.toString());
		
		File toDelete = new File(absuotePath.toString());
		if (toDelete.exists() && toDelete.isFile())
		{
			toDelete.delete();
			logger.info("File deleted, return true");
			return true;
		}
		logger.error("File didn't delete, return flse");
		return false;
	}
	
	
	
}
