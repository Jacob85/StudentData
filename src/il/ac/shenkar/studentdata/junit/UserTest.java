package il.ac.shenkar.studentdata.junit;

import static org.junit.Assert.*;
import il.ac.shenkar.studentdata.MD5Converter;
import il.ac.shenkar.studentdata.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User ob;
	@Before
	public void setUp() throws Exception 
	{
		ob = new  User("email@mail.com", "Yaki", "123456", "uni", "trend", "a", "course");
		assertTrue(MD5Converter.isValidMD5(ob.getPassword()));
		
		ob.setFilesHistory("none");
		ob.setFilesToView("none");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToCart()
	{
		ob.addToCart(null);
		assertTrue(ob.getFilesToView().equals("none"));
		
		ob.addToCart("");
		assertTrue(ob.getFilesToView().equals("none"));

		ob.addToCart("path/to/file/file.png");
		assertTrue(ob.getFilesToView().equals("path/to/file/file.png"));
		
		ob.addToCart("path/to/file/file.png");
		assertTrue(ob.getFilesToView().equals("path/to/file/file.png%path/to/file/file.png"));
	}
	@Test
	public void testIsExistInCart() 
	{
		ob.addToCart("path/to/file/file.png");
		assertTrue(!ob.isExistInCart(null));
		assertTrue(!ob.isExistInCart(""));
		assertTrue(ob.isExistInCart("path/to/file/file.png"));
	}

	@Test
	public void testRemoveFromCart() 
	{
		ob.addToCart("path/to/file/file.png");
		ob.addToCart("path/to/file/file.png");
		if (ob.isExistInCart("path/to/file/file.png"))
		{
			ob.removeFromCart("path/to/file/file.png");
			assertTrue(ob.getFilesHistory().contains("path/to/file/file.png"));
			assertTrue(!ob.getFilesHistory().contains("%") && !ob.getFilesToView().contains("%"));
		}
	}



}
