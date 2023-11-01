package dictionary;
import java.io.IOException; 
import java.io.PrintWriter; 

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Servlet implementation class FormDataHandle 

// Annotation to map the Servlet URL 
@WebServlet("/ShowBooked") 
public class ShowBooked extends HttpServlet { 

	// HttpServlet doPost(HttpServletRequest request, HttpServletResponse response) method 
    @Override
	protected void doPost(HttpServletRequest request, 
                HttpServletResponse response) throws ServletException, IOException { 
		
	} 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);

        String idstring = request.getParameter("cottageId"); 
        CottageRepository cottageRepository = new CottageRepository();
        long id = Long.parseLong(idstring);
        String resultAsText = cottageRepository.ShowCottageInfo(id);
        
		// set the content type of response to 'text/html' 
		response.setContentType("text/html"); 
		
		// Get the PrintWriter object to write 
		// the response to the text-output stream 
		PrintWriter out = response.getWriter(); 
		// Convert the StringBuilder to a String
		
		// Print the data 
		out.print("<html><body>");  
		out.print("<h3>Details Entered</h3><br/>"); 
		//String addPrefix = "PREFIX g: <http://www.owl-ontologies.com/generations.owl#>\n" + resultAsText;
		out.print(resultAsText);
		out.print("</body></html>"); 
    }

}
