package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sessions.Sessionmanager;

import javax.servlet.http.Cookie;

/**
 * Servlet implementation class Sessioninfo
 */
@WebServlet("/Sessioninfo")
public class Sessioninfo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sessioninfo2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.115:5500"); // Allow requests from any origin
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
	    response.setHeader("Access-Control-Allow-Credentials", "true"); 
	    
	    try {
	    //	System.out.println("sto eseguendo get");
	    	String sessionIdValue = request.getParameter("sessionID");
	    	HttpSession session = request.getSession();
	    	String sessionId = "";
		    
	    	if(sessionIdValue.equals("retrieve")) {
	    		
	    		sessionId = session.getId();
	    		String nome = (String) session.getAttribute("nome");
	    		
	    		if(nome != null) {
	    			response.sendRedirect("http://192.168.1.115:5500/homepage.html?sessionIDspysphare="+sessionId);
	    		}else {
	    			response.getWriter().append("null");
					//response.sendRedirect("http://192.168.1.115:8080/DilaxiaTornei/Login");
				 }
	    		
	    	//System.out.println("redirection erro");
	    		
	    	}else if(sessionIdValue != null) {
//	    		request.setAttribute("Session-ID", sessionIdValue);
//	    		session = request.getSession();
//	    		session.invalidate();
//	    		
//	    		session = request.getSession(true); // Create a new session
//	    		session.setAttribute("Session-ID", sessionIdValue);
//	    		System.out.println("recieved something");
//		    	
//				sessionId = session.getId();
//				
//				System.out.println("id:"+sessionId);
//			
//
//				
//
//				System.out.println(sessionId);
	    		
	    		boolean sessionFound = true;
	    		try {
	    			session = Sessionmanager.sessionMap.get(sessionIdValue);
	    		}catch(Exception e) {
	    			sessionFound = false;
	    			session = request.getSession();
	    		}
	    		
	    		System.out.println(Sessionmanager.sessionMap.size());
				
				String nome = (String) session.getAttribute("nome");
				String cognome = (String) session.getAttribute("cognome");
				String email = (String) session.getAttribute("email");
//				System.out.println("localeee");
//				System.out.println(nome);
//				System.out.println(cognome);
				
				
				  StringBuilder jsonResponse = new StringBuilder();
				    jsonResponse.append("{");
				    jsonResponse.append("\"nome\": \""+nome+"\",");
				    jsonResponse.append("\"cognome\": \""+cognome+"\",");
				    jsonResponse.append("\"email\": \""+email+"\"");
				    jsonResponse.append("}");
				    
				   
				    
				 
				 
				 if(nome != null) {
					 
					 System.out.println("risposta data");
					 response.getWriter().append(jsonResponse);
					
				 }else {
					 response.getWriter().append(jsonResponse); 
					 response.sendRedirect("http://192.168.1.115:5500/login.html");
				 
				 }
	    	}else {
	    		response.sendRedirect("http://192.168.1.115:5500/login.html");
	    	}
	    }catch(Exception e) {
	    	response.sendRedirect("http://192.168.1.115:5500/login.html");
	    	e.printStackTrace();
	    }
	   
	   
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		    
	}

}
