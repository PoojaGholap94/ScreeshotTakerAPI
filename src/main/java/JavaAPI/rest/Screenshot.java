package JavaAPI.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Screenshot extends HttpServlet{

	
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        PrintWriter out = resp.getWriter();  

		resp.setContentType("text/html");
		String url=req.getParameter("url");
		
		ScreenshottakerService.takeScreenshot(url);
		
		out.print("<h3>Your Screenshot has been Saved !!! Please check your folder</h3>");
		
		
	}

}
