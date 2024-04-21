package databasePack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryManager {
	private static final String INSERT_USER = "INSERT INTO utenti(email_utente, nome, cognome, password, data_nascita, sesso, data_creazione, privilegi_flg) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_TEMP_USER = "INSERT INTO temp_utenti(email_utente, nome, cognome, password, data_nascita, sesso, otp, privilegi_flg, expire_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_TEMP_PASS = "INSERT INTO temp_pass_recover(email_utente, passkey, expire_at) VALUES(?, ?, ?)";
	
	private static final String SELECT_TEMP_USER = "SELECT email_utente as email, nome, cognome, password, otp, sesso, data_nascita, expire_at FROM temp_utenti WHERE email_utente = ?";
	private static final String SELECT_USER = "SELECT email_utente as email, nome, cognome, password, sesso, data_nascita, data_creazione FROM utenti WHERE email_utente = ?";
	private static final String SELECT_TEMP_PASS = "SELECT email_utente as email FROM temp_pass_recover WHERE passkey = ?";
	
	
	private static final String UPDATE_PASS = "UPDATE utenti SET password = ? WHERE email_utente = ?";
	private static final String DELETE_RESET_PASS_REQUEST = "DELETE FROM temp_pass_recover WHERE email_utente = ?";
	
	
	public static PreparedStatement INSERT_USER_STM;
	public static PreparedStatement INSERT_TEMP_USER_STM;
	public static PreparedStatement SELECT_USER_STM;
	public static PreparedStatement SELECT_TEMP_USER_STM;
	public static PreparedStatement INSERT_TEMP_PASS_STM;
	public static PreparedStatement SELECT_TEMP_PASS_STM;
	public static PreparedStatement UPDATE_PASS_STM;
	public static PreparedStatement DELETE_RESET_PASS_REQUEST_STM;
	
	public QueryManager(Connection conn) throws SQLException {
		INSERT_USER_STM = conn.prepareStatement(INSERT_USER);
		INSERT_TEMP_USER_STM = conn.prepareStatement(INSERT_TEMP_USER);
		SELECT_USER_STM = conn.prepareStatement(SELECT_USER);
		SELECT_TEMP_USER_STM = conn.prepareStatement(SELECT_TEMP_USER);
		INSERT_TEMP_PASS_STM = conn.prepareStatement(INSERT_TEMP_PASS);
		SELECT_TEMP_PASS_STM = conn.prepareStatement(SELECT_TEMP_PASS);
		UPDATE_PASS_STM = conn.prepareStatement(UPDATE_PASS);
		DELETE_RESET_PASS_REQUEST_STM = conn.prepareStatement(DELETE_RESET_PASS_REQUEST);
	}
}
