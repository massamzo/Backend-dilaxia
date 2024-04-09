package databasePack;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class User extends Database{
	
	private String email;
	private String nome;
	private String cognome;
	private String password;
	private String dataCreazione;
	
	private boolean userExists;

	public User(String email, String nome, String cognome, String password) {
	
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.dataCreazione = null;
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUserExists() {
		return userExists;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
	
	
	
	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public boolean userExists() throws SQLException {
		
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();
		 
		 String query = "SELECT "+utentiTable+".email_utente as email, nome, cognome, password FROM "+utentiTable+" INNER JOIN "+accountTable+" ON "+utentiTable+".email_utente = "+accountTable+".email_utente WHERE "+accountTable+".email_utente = '"+this.email+"'";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 if(rs.next()) {
			 if(this.email.equals(rs.getString("email"))) {
				 return true;
			 }
		 }
		 
		 return false;
		
	}
	
	
	
	public User userWithCryptedPass() throws SQLException {
		
		
		
		 Connection conn = getConn();
		 Statement stmt = conn.createStatement();
		 
		 String query = "SELECT "+utentiTable+".email_utente as email, nome, cognome, password, data_creazione FROM "+utentiTable+" INNER JOIN "+accountTable+" ON "+utentiTable+".email_utente = "+accountTable+".email_utente WHERE "+accountTable+".email_utente = '"+this.email+"'";
		 ResultSet rs = stmt.executeQuery(query);
		 
		 User newuser = null;
		 if(rs.next()) {
			 newuser = new User(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"), rs.getString("password"));
			 newuser.setDataCreazione(rs.getString("data_creazione"));
			 return newuser;
		 }
		 
		 
		 return newuser;
		
	}
	
	
	
}
