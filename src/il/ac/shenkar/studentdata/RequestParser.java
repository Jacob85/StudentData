package il.ac.shenkar.studentdata;

public class RequestParser 
{
	private final String relativePath = "/StudentData/";
	private String separetor = "/";

	
	
	
	public String getRequestedAction(String uri)
	{
		
		
		if (relativePath.equals(uri))
		{
			// mean this is the first requset and we need to go to login.jsp
			return "Login.jsp";
		}
		
		//remove the relative path section

		String [] tokens = uri.split(separetor);
		if (tokens.length == 1)
			return tokens[0];
		else 
		{
			return tokens[tokens.length-1];
		}
				
	}

}
