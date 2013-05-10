package il.ac.shenkar.studentdata.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import il.ac.shenkar.studentdata.RequestParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.net.httpserver.Authenticator.Success;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class RequestParserTest {

	RequestParser ob;
	@Before
	public void setUp() throws Exception 
	{
		ob = new RequestParser();
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testGetRequestedAction() 
	{
		String reqString = ob.getRequestedAction("/StudentData/StudentData/Shenkar/Software%20Enganeir/c/Git/StudentData/cart.jsp");
		assertTrue(reqString.equals("cart.jsp"));
		
		reqString = ob.getRequestedAction("/StudentData/Shenkar/Software/a/java%20ee/POJO.PNG/StudentData/StudentData" +
										"/StudentData/shenkar/Software%20Enganeir/a/Design/get_files=true");
		assertTrue(reqString.equals("get_files=true"));
	}

	@Test
	public void testGetFilePath() 
	{
		String path = ob.getFilePath("/StudentData/Shenkar/Software/a/java%20ee/POJO.PNG/add_to_cart=true");
		assertTrue(path.equals("/Shenkar/Software/a/java ee/POJO.PNG"));
		
	}

	@Test
	public void testGetFileList()
	{
		java.util.List<String> list = ob.getFileList("none");
		assertNull(list);
		
		list = ob.getFileList(null);
		assertNull(list);
		
		list = ob.getFileList("file1%file2");
		assertNotNull(list);
		assertTrue(list.size() == 2);
	}

	@Test
	public void testGetCourseFromPath()
	{
		String course = ob.getCourseFromPath(null);
		assertNull(course);
		
		course = ob.getCourseFromPath("/StudentData/Shenkar/Software/a/java%20ee/POJO.PNG/");
		assertNull(course);
				
		course = ob.getCourseFromPath("/StudentData/Shenkar/Software/a/java%20ee/get_files=true");
		assertNotNull(course);
		assertTrue(course.equals("java ee"));
		
	}

}
