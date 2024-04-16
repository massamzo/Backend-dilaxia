package databasePack;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


public class DbRegisterLogin extends Database {
	
	public DbRegisterLogin() {
		super();
		
	}
	
	 private void insertUtente(User utente) throws SQLException {
		 String now = dateTime();
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();
		 String sql = "INSERT INTO "+utentiTable+"(email_utente, nome, cognome, password,data_creazione) VALUES('"+utente.getEmail()+"', '"+utente.getNome()+"', '"+utente.getCognome()+"', '"+utente.getPassword()+"', '"+now+"')";
		 stmt.executeUpdate(sql);
	 }
	 
	 
	 private String dateTime(){
		 
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
	    return formattedDate;
	    
	 }
	 
	 

	 
	 private void insertTempUtente(User utente, String otp) throws SQLException {
		 String date_time = dateTime();
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();		 
		 String sql = "INSERT INTO "+tempUtentiTable+"(email_utente, nome, cognome, password, otp, expire_at) VALUES('"+utente.getEmail()+"', '"+utente.getNome()+"', '"+utente.getCognome()+"', '"+utente.getPassword()+"', '"+otp+"', '"+date_time+"')";
		 stmt.executeUpdate(sql);
	 }
	 
	 
	 
	public boolean createTempAccount(User utente, String otp) {
		
		Argon2 argon2 = Argon2Factory.create();
		 
		try {
			String hashPass = argon2.hash(10, 63312, 1, utente.getPassword());  
			String hashOTP = argon2.hash(10, 63312, 1, otp);
			 
			 //10 è il numero delle iterazioni
			 // 63312 è la memoria che utilizza in kb
			 // 1 è il numero dei thread
			 // calculating the date
			 
			
			 utente.setPassword(hashPass);
			 
			 insertTempUtente(utente,hashOTP);
			 utente.setPassword("");
			 return true;
			 
		}catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	
	public void confirmRegistration(User utente, String otp) throws SQLException {
		
		
		 Argon2 argon2 = Argon2Factory.create();
		 String now = dateTime();
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();
		 
		 String query = "SELECT "+tempUtentiTable+".email_utente as email, nome, cognome, password, otp, expire_at FROM "+tempUtentiTable+" WHERE email_utente = '"+utente.getEmail()+"'";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 User copyUtente = null;
		 
		 while(rs.next()) {
			 
			 if(argon2.verify(rs.getString("otp"), otp)) {
				 
				 // if the user with the otp has found then ...
				 
				 copyUtente = new User(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password"));
				 insertUtente(copyUtente);
				 break;
				 
				 
			 }
		 }
		 
		 if(copyUtente == null) {
			 throw new SQLException();
		 }
		 
//		 if(rs.next()) {
//			 
//			 boolean found = false;
//			 
//			 while(rs)
//			 copyUtente = new User(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password"));
//			 insertUtente(copyUtente);
//			 
//			 
//		 }else {
//			 
//			throw new SQLException();
//		 }
		
	}
	
	
	
	 

	// TODO Auto-generated constructor stub
	 
	 //usato per login
	 public boolean verifyUser(User utente) throws SQLException {
		 
		 if(utente.userExists()) {
			 
			 User cryptedUser = utente.userWithCryptedPass();
			 Argon2 argon2 = Argon2Factory.create();
			 
			 if(argon2.verify(cryptedUser.getPassword(), utente.getPassword())) {
				 	
				 utente.setNome(cryptedUser.getNome());
				 utente.setCognome(cryptedUser.getCognome());
				 return true;
			 }
			 
			 
		 }
		 
		 return false;
	 }
}


