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
		logger = Logger.getLogger(MD5Converter.class.getName());
	}

	/**
	 * return the MD5 code of a string it recieved as  parameter
	 * @param pass
	 * @return the new password
	 */
	public String getMD5(String pass)
	{
		String md5Password = null;
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

	/**
	 * determine if the String is in MD5 or not
	 * @param s
	 * @return true is the string is MD5, else return false
	 */
	public static boolean isValidMD5(String s) {
	//	logger.info("isValidMD5 was called with: "+s);
	    return s.matches("[a-fA-F0-9]{32}");
	}


}
