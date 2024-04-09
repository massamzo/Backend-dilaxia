package login;

import java.io.IOException;

import javax.servlet.http.Cookie;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasePack.DbRegisterLogin;
import databasePack.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.119"); // Replace with your domain
	     response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	     response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	     response.setHeader("Access-Control-Allow-Credentials", "true"); // This allows the sharing of session cookies
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
		
		//login methods
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.119"); // Replace with your domain
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	    response.setHeader("Access-Control-Allow-Credentials", "true"); //
		
		
		HttpSession session = request.getSession();
		
		String sessionId = session.getId();

		String cookieValue = String.format("JSESSIONID=%s; Path=/DilaxiaTornei; Domain=192.168.1.119; Secure; HttpOnly; SameSite=None", sessionId);

		response.addHeader("Set-Cookie", cookieValue);
		
		String session_nome = (String) session.getAttribute("nome");
		
		if(session_nome == null) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User utente = new User(email, password);
			DbRegisterLogin database = new DbRegisterLogin();
			
			try {
				
				if(database.verifyUser(utente)) { // if the user has been authenticated
					
					// create the sessions and redirect him to home page
					
					session.setAttribute("nome", utente.getNome());
					session.setAttribute("cognome", utente.getCognome());
					session.setAttribute("email", utente.getEmail());
					
					
					// send session information to the sessioninfo servlet
				
					System.out.println("sono entrato qui");
					// cambiare con link vero frontend
					
					

					// Check if redirect attribute is set
					//response.getWriter().append("Session created");
					response.sendRedirect("http://192.168.1.119/dilaxia/homepage.html"); 
					
					
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			
			// cambiare con link vero frontend
			System.out.println("session not created login");
			response.sendRedirect("http://192.168.1.115:5500/homepage.html");  
			
		}
	}

}
