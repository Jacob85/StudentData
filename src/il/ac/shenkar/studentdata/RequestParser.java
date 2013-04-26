package il.ac.shenkar.studentdata;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.mapping.List;

public class RequestParser 
{
	private final String relativePath = "/StudentData/";
	private String separetor = "/";
	private static String fileListSeparetor = "%";
	private static Logger logger;
	
	public RequestParser()
	{
		logger = Logger.getLogger(RequestParser.class.getName());
		logger.info("RequestParser was created");
	}
	
	/**
	 * Received the uri and parse it, return the action that the user asked for
	 * @param uri
	 * @return String with the user request
	 */
	public String getRequestedAction(String uri)
	{
		logger.info("getRequestedAction was recieved with: "+uri);
		String tmp = new String(uri);
		
		if (relativePath.equals(uri))
		{
			// mean this is the first requset and we need to go to login.jsp
			return "Login.jsp";
		}
		
		//remove the relative path section
		String [] tokens = tmp.split(separetor);
		if (tokens.length == 1)
			return tokens[0];
		else 
		{
			return tokens[tokens.length-1];
		}
		
	}
	
	/**
	 * Retrieve the file path from the Uri received
	 * the uri received look look this /StudentData/StudentData/uni/trend/year/course/filename.png/upload
	 * @param uri
	 * @return String of the file path
	 */
	public String getFilePath(String uri)
	{
		logger.info("getFilePath was called with: " +uri);
		// remove all of the request headers;
		String path = uri.replaceAll("/StudentData", "");
		StringBuilder builder = new StringBuilder();
		String[] tokens = path.split(separetor);
		
		for (int i=0; i<tokens.length -1; i++)
		{
			builder.append(tokens[i]);
			if (i < tokens.length -2 )
				builder.append(separetor);
		}
		
		logger.info("Return the path: " + builder.toString());
		return builder.toString();
	}

	/**
	 * get String with files paths separate with '%', break the String to diffrent strings and retutn a list
	 * @param fileList
	 * @return list <String> of the files paths
	 */
	public java.util.List<String> getFileList(String fileList)
	{
		logger.info("getFileList was called with param: " + fileList);
		if (fileList.equals("none") || fileList == null)
		{
			logger.info("String is empty, return null");
			return null;
		}
		// separate the strings
		String [] files = fileList.split(fileListSeparetor);
		// allocate the list
		java.util.List<String> listToReturn = new ArrayList<String>();
		
		// add all of the Strings to the list
		for (int i=0; i<files.length ; i++)
		{
			listToReturn.add(files[i]);
		}
		
		logger.info("Returned list with: " +listToReturn.size() + "items");
		return listToReturn;
	}
	

}
