package register;
import java.sql.Connection;
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
	
	 private void insertUtente(String email, String nome, String cognome) throws SQLException {
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();
		 String sql = "INSERT INTO "+utentiTable+"(email_utente, nome, cognome) VALUES('"+email+"', '"+nome+"', '"+cognome+"')";
		 stmt.executeUpdate(sql);
	 }
	 
	 
	 private String dateTime(){
		 
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
	    return formattedDate;
	    
	 }
	 
	 
	 private void insertAccount(String email, String password) throws SQLException {
		 Connection conn = getConn();
		 
		 //  ---------- HASHING DELLA PASSWORD ----------
		 
		 //creazione della istanza libreria
		 Argon2 argon2 = Argon2Factory.create();
		 
		try {
			String hashPass = argon2.hash(10, 63312, 1, password);  
			 
			 //10 è il numero delle iterazioni
			 // 63312 è la memoria che utilizza in kb
			 // 1 è il numero dei thread
			 
			 
			 // calculating the date
			 
			 String date = dateTime();
			 
			 Statement stmt = conn.createStatement();
			 String sql = "INSERT INTO "+accountTable+"(email_utente, password, data_creazione) VALUES('"+email+"', '"+hashPass+"', '"+date+"')";
			 stmt.executeUpdate(sql);
			 
		}catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		 
	 }
	 
	 
	
	 
	 public boolean createAccount(User utente) {
		 
		 try {
			if(!utente.userExists()) {
				insertUtente(utente.getEmail(), utente.getNome(), utente.getCognome());
				insertAccount(utente.getEmail(), utente.getPassword());
				return true;
			}else {
				
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	 }
	// TODO Auto-generated constructor stub
}


