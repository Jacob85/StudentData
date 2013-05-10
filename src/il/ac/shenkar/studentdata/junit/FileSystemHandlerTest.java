package il.ac.shenkar.studentdata.junit;

import static org.junit.Assert.*;


import il.ac.shenkar.studentdata.FileSystemHandler;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileSystemHandlerTest {


	@Test
	public void testGetFile() 
	{
		File f;
		try {
			f= FileSystemHandler.getInstande().getFile(null);
		} catch (NullPointerException e) 
		{
			System.out.println("a null pointer exeption thrown");
		}
		f = FileSystemHandler.getInstande().getFile("Shenkar\\Software Enganeir\\a\\design\\Desert.jpg");
		assertNotNull(f);
	}

	@Test
	public void testDeleteFile() 
	{
		try {
			FileSystemHandler.getInstande().deleteFile(null);
		} catch (NullPointerException e) 
		{
			System.out.println("a null pointer exeption thrown");
		}
		assertTrue(FileSystemHandler.getInstande().deleteFile("Shenkar\\Software Enganeir\\a\\design\\Desert.jpg"));

	}

}
