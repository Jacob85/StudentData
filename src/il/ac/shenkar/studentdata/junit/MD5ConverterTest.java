package il.ac.shenkar.studentdata.junit;

import static org.junit.Assert.*;
import il.ac.shenkar.studentdata.MD5Converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MD5ConverterTest {
	MD5Converter ob;

	@Before
	public void setUp() throws Exception
	{
		ob = new MD5Converter();
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testMD5Converter() 
	{
		String md5 = ob.getMD5(null);
		assertNull(md5);
		
		md5 = ob.getMD5("123456");
		assertTrue(md5.equals("e10adc3949ba59abbe56e057f20f883e"));
	}

	@Test
	public void testIsValidMD5() 
	{
		String md5 = "e10adc3949ba59abbe56e057f20f883e";
		assertTrue(MD5Converter.isValidMD5(md5));
	}

}
