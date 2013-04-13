package il.ac.shenkar.studentdata;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;



public class MD5Converter 
{
private static Logger logger;
	
	public MD5Converter()
	{	
	}

	public String getMD5(String pass)
	{
		String md5Password = null;
		logger = Logger.getRootLogger();
		
    	try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes(), 0, pass.length());
			md5Password = new BigInteger(1, md.digest()).toString(16);
		}
    	catch (NoSuchAlgorithmException e)
		{
    		logger.error("Wrong algorithm.");
			e.printStackTrace();
		}
    	
    	return md5Password;
	}

	public static boolean isValidMD5(String s) {
	    return s.matches("[a-fA-F0-9]{32}");
	}


}
