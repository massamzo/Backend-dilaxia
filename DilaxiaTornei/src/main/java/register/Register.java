package register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasePack.DbRegisterLogin;
import databasePack.User;
/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.119"); // Replace with your domain
	     response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	     response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	     response.setHeader("Access-Control-Allow-Credentials", "true"); // This allows the sharing of session cookies
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String password = request.getParameter("password");
			
			User utente = new User(email, nome, cognome, password);
			
			
			
			DbRegisterLogin database = new DbRegisterLogin();
			if(database.createAccount(utente)) { //if everything went right
				
				//create session
				
//				session.setAttribute("nome", nome);
//				session.setAttribute("cognome", cognome);
//				session.setAttribute("email", email);
				
				// mail sending
				
				// reindirizzamento a login
				
				response.sendRedirect("http://192.168.1.115:8080/DilaxiaTornei/Login");  
				
			}

		}else {
			
			System.out.println("session not created");
		}
		
		// mettere link frontend
	}

}
