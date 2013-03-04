package il.ac.shenkar.studentdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentData/*")
public class StudentDataController extends HttpServlet 
{
	final String localPath = "C:\\Users\\Jacob\\workspaceEE\\StudentData\\Files\\";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		 // Get the absolute path of the image
        ServletContext sc = getServletContext();
        String path = req.getPathInfo();
        String delims = "[/]";
		String[] tokens = path.split(delims);
		String filename = tokens[tokens.length-1];

        // Get the MIME type of the image
        String mimeType = sc.getMimeType(filename);
        if (mimeType == null) {
            sc.log("Could not get MIME type of "+filename);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
    
        // Set content type
        resp.setContentType(mimeType);
    
        // Set content size
        File file = new File(this.localPath + filename);
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
    }


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
