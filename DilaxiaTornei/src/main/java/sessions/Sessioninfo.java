package sessions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Sessioninfo
 */
@WebServlet("/Sessioninfo2s")
public class Sessioninfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sessioninfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.115:5500");
		
		HttpSession session = request.getSession();
		
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		String email = (String) session.getAttribute("email");
		
		System.out.println(nome);
		System.out.println(cognome);
		
		
		  StringBuilder jsonResponse = new StringBuilder();
		    jsonResponse.append("{");
		    jsonResponse.append("\"nome\": \""+nome+"\",");
		    jsonResponse.append("\"cognome\": "+cognome+",");
		    jsonResponse.append("\"email\": \""+email+"\"");
		    jsonResponse.append("}");
		    
		   
		    
		 response.getWriter().append(jsonResponse);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		    
	}

}
