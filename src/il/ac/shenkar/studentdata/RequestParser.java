package il.ac.shenkar.studentdata;

import java.util.ArrayList;

import org.hibernate.mapping.List;

public class RequestParser 
{
	private final String relativePath = "/StudentData/";
	private String separetor = "/";
	private static String fileListSeparetor = "%";

	
	
	
	public String getRequestedAction(String uri)
	{
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
	
	/*the uri recieved look loke this /StudentData/StudentData/uni/trend/year/course/filename.png/upload*/
	public String getFilePath(String uri)
	{
		// remove all of the request headers;
		uri.replaceAll("/StudentData", "");
		StringBuilder builder = new StringBuilder();
		String[] tokens = uri.split(separetor);
		
		for (int i=0; i<tokens.length -1; i++)
		{
			builder.append(tokens[i]);
			if (i < tokens.length -2 )
				builder.append(separetor);
		}
		
		return builder.toString();
	}

	public java.util.List<String> getFileList(String fileList)
	{
		if (fileList.equals("none") || fileList == null)
			return null;
		String [] files = fileList.split(fileListSeparetor);
		
		java.util.List<String> listToReturn = new ArrayList<String>();
		
		for (int i=0; i<files.length ; i++)
		{
			listToReturn.add(files[i]);
		}
		
		return listToReturn;
	}
	

}
