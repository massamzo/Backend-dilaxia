package register;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasePack.DbRegisterLogin;
import databasePack.User;

/**
 * Servlet implementation class ConfirmRegistration
 */
@WebServlet("/ConfirmRegistration")
public class ConfirmRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String otp = request.getParameter("otp");
		
		if(email == null || otp == null) {
			response.sendRedirect("http://192.168.1.115:5500/index.html");
		}else {
			
			// conferma la registrazione 
			
			DbRegisterLogin database = new DbRegisterLogin();
			User utente = new User(email,"");
			
			try {
				if(utente.userExists()) {
					
					// return to the registration page with error
					String error="Utente esiste!";
					response.sendRedirect("http://192.168.1.115:5500/index.html?error="+error); 
					
					
				}else {
					
					// andiamo a registrare il user
					
					database.confirmRegistration(utente, otp);
					
					// se tutto è andato a buon fine allora reindirizzo sulla pagina di conferma
					response.sendRedirect("http://192.168.1.115:5500/confirm.html"); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				String error="la registrazione non è andata a buon fine";
				response.sendRedirect("http://192.168.1.115:5500/index.html?error="+error);
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
