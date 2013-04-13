package il.ac.shenkar.studentdata;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.derby.tools.sysinfo;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.buf.UDecoder;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
@WebServlet("/StudentData/*")
//@WebServlet("/*")
public class StudentDataController extends HttpServlet 
{
	final String localPath = "C:\\Users\\Jacob\\workspaceEE\\StudentData\\Files\\";
	static Logger logger = Logger.getLogger(StudentDataController.class.getName());
	private RequestParser parser;
	
	public StudentDataController() 
	{
		parser = new RequestParser();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		//configure the logger OB
		BasicConfigurator.configure();
		
		String reqAction = parser.getRequestedAction(req.getRequestURI());
		if (reqAction.equals("Login.jsp"))
		{
			try {
				getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch (reqAction)
		{
		case "newuser.jsp":
		{
			try {
				getServletContext().getRequestDispatcher("/newuser.jsp").forward(req, resp);
				return;
			} catch (ServletException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

		default:
			break;
		}
		

		
		ServletContext sc = getServletContext();
		String path = "uni/trend/year/course" + req.getPathInfo();
		
		
		 // Get the absolute path of the image
    /*    ServletContext sc = getServletContext();
        String path = req.getPathInfo();*/
        String delims = "[/]";
		String[] tokens = path.split(delims);
		String filename = tokens[tokens.length-1];

        // Get the MIME type of the image
        String mimeType = "image/jpeg";
      /*  if (mimeType == null) {
            sc.log("Could not get MIME type of "+filename);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }*/
    
        // Set content type
        resp.setContentType(mimeType);
    
        File file =  FileSystemHandler.getInstande().getFile(path);
        resp.setContentLength((int)file.length());
        
        
        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();
        
        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) 
        {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
	}
        
       /* String myCurrentDir = System.getProperty("user.dir");

        System.out.println(myCurrentDir);
        
        // Set content size
        File file = new File("Files\\" + filename);
        resp.setContentLength((int)file.length());
    
        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = resp.getOutputStream();
    
        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
	}*/


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		//configure the logger OB
		BasicConfigurator.configure();
		
		String reqAction = parser.getRequestedAction(req.getRequestURI());
		
		switch (reqAction) 
		{
		case "loginto":
		{
			// the user entered username and password and we need to check if the user exist in the DB and to manage his session
			User user = (User) UserDAO.getInstance().getRecord(req.getParameter("email"));
			if (user != null)
			{
				if (!user.isPasswordOK(req.getParameter("password")))
				{
					//mean the isPasswordOK method return false and the user did not loged in sucssesfuly
					// we need to forword it to the same page again so he will try again;
					getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
					return;
				}
				else
				{
					// mean the user logged in successfuly and we need to get the user data from the DB
					//TODO to pass cadan the object and the data he need in order to create the user UI
					String[] columns = {"university", "trend"};
					String[] conditionls = {user.getUniversity(), user.getTrend()};
					java.util.List filesList = FileRecordDAO.getInstance().getRecordsWhere(columns, conditionls);
					// attached the list & the user to the Session
					HttpSession currSession = req.getSession();
					currSession.setAttribute("fiesList", filesList);
					currSession.setAttribute("user", user);
					
					getServletContext().getRequestDispatcher("/AfterLogin.jsp").forward(req, resp);
					return;
				}
			}
			else
			{
				//user does not exists
				getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
				return;
			}
		}
		case "register":
		{
			//first i'll check if the user is already exists
			if (UserDAO.getInstance().isExist(req.getParameter("email")))
			{
				// user is already exists
				req.setAttribute("massage","User "+ req.getParameter("email") +" is allready exists");
				getServletContext().getRequestDispatcher("/Login.jsp").forward(req, resp);
				return;
			}
			// the use entered his details and we need to register him to the system
			//TODO all of the validation of the form are performed in the client side, here i do not dial with it
			User newUser = new User();
			newUser.setEmail(req.getParameter("email"));
			newUser.setSubject(req.getParameter("subject"));
			newUser.setPassword(req.getParameter("password"));
			newUser.setTrend(req.getParameter("trend"));
			newUser.setUserName(req.getParameter("username"));
			newUser.setUniversity(req.getParameter("uni"));
			newUser.setYear(req.getParameter("year"));
			newUser.setFilesHistory("none");
			newUser.setFilesToView("none");
			// add the user to the DB
			
			if (UserDAO.getInstance().addRecord(newUser) == 1)
			{
				//get the current session and place the user OB to the current session 
				HttpSession userSession = req.getSession();
				userSession.setAttribute("user", newUser);
				//TODO here we can determine the Session parameters like timeout...
				
				//TODO get all of the user document and pass it to the client side
				
				
				req.getServletContext().getRequestDispatcher("/AfterLogin.jsp").forward(req, resp);
				return;
			}
			else
			{
				// mean the insert failed 
				getServletContext().getRequestDispatcher("/newuser.jsp").forward(req, resp);
				return;
			}	
		}

		default:
			break;
		}
		
		
		
		
		String path = "uni/trend/year/course";
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		System.out.println("request: "+req);
		if (!isMultipart)
		{
			System.out.println("File Not Uploaded");
		} 
		else 
		{
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			java.util.List<FileItem> items = null;
			try
			{
				items = upload.parseRequest(req);
				System.out.println("items: "+items);
				File saveTo = new File(localPath + "/"+ items.get(0).getName());
				
				path += "/"+ items.get(0).getName();
				if (FileSystemHandler.getInstande().saveFile(items.get(0),path) == 1)
				{	
					FileRecord record = new FileRecord(0, "uni", "trend", "year", "course", path , 0);
					FileRecordDAO.getInstance().addRecord(record);
					getServletContext().getRequestDispatcher("/it_works.jsp").forward(req, resp);
				}
				items.get(0).write(saveTo);
			} catch (FileUploadException e) 
			{
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		
		}
		
}