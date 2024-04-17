package register;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasePack.DbRegisterLogin;
import databasePack.User;
import mail.Mailer;
/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final int code_length = 5;
    private static final String codes = "01A2BCD3456EFGH78IJK9";
    
    private String error;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private String generateOTP() {
    	
    	char[] code = new char[code_length];
    	Random rn = new Random();
    	for(int i=0; i < code_length; i++) {
    		code[i] = codes.charAt(rn.nextInt(code.length));
    	}
    	
    	return new String(code);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500"); // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Session-ID");
	    response.setHeader("Access-Control-Allow-Credentials", "true"); 
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		
		String session_nome = (String) session.getAttribute("nome");
		
		if(session_nome != null) {
			response.sendRedirect("http://192.168.1.115:5500/homepage.html");  
		}else {
			response.sendRedirect("http://192.168.1.115:5500/index.html");  // register page
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.115:5500"); // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Session-ID");
	    response.setHeader("Access-Control-Allow-Credentials", "true"); 
	     
	     
		
		HttpSession session = request.getSession();
		
//		String sessionId = session.getId();
//
//		String cookieValue = String.format("JSESSIONID=%s; Path=/DilaxiaTornei; Domain=127.0.0.1:5500; Secure; HttpOnly; SameSite=None", sessionId);
//
//		response.addHeader("Set-Cookie", cookieValue);

		
		String session_nome = (String) session.getAttribute("nome");
		
		if(session_nome == null) {
			
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String password = request.getParameter("password");
			
			User utente = new User(email, nome, cognome, password);
			
			
			
			DbRegisterLogin database = new DbRegisterLogin();
			
			
			
			try {
				if(utente.userExists()) {
					
					// restituisco con errore
					error="Utente esiste!";
					response.sendRedirect("http://192.168.1.115:5500/index.html?error="+error); 
					
					
				}else {
					
					// salva dati in una tabella temporanea
					String otp = generateOTP();
					
					database.createTempAccount(utente, otp);
					
				
					// manda la mail 
					Mailer mail = new Mailer(email, nome, "http://192.168.1.115:8080/DilaxiaTornei/ConfirmRegistration?email="+email+"&otp="+otp);
					boolean sent = mail.send();
					
					//reinderizza sulla pagina di conferma
					
					response.sendRedirect("http://192.168.1.115:5500/toconfirm.html");  
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				error="Registrazione non è andata a buon fine";
				response.sendRedirect("http://192.168.1.115:5500/index.html?error="+error); 
				e.printStackTrace();
			}
			
			

		}else {
			
			response.sendRedirect("http://192.168.1.115:5500/homepage.html");
		}
		
		// mettere link frontend
	}

}
