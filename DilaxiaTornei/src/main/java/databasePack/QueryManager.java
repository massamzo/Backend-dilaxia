package databasePack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryManager {
	private static final String INSERT_USER = "INSERT INTO utenti(email_utente, nome, cognome, password, data_nascita, sesso, data_creazione, privilegi_flg) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_TEMP_USER = "INSERT INTO temp_utenti(email_utente, nome, cognome, password, data_nascita, sesso, otp, privilegi_flg, expire_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_TEMP_USER = "SELECT email_utente as email, nome, cognome, password, otp, sesso, data_nascita, expire_at FROM temp_utenti WHERE email_utente = ?";
	private static final String SELECT_USER = "SELECT email_utente as email, nome, cognome, password, sesso, data_nascita, data_creazione FROM utenti WHERE email_utente = ?";
	
	public static PreparedStatement INSERT_USER_STM;
	public static PreparedStatement INSERT_TEMP_USER_STM;
	public static PreparedStatement SELECT_USER_STM;
	public static PreparedStatement SELECT_TEMP_USER_STM;
	
	public QueryManager(Connection conn) throws SQLException {
		INSERT_USER_STM = conn.prepareStatement(INSERT_USER);
		INSERT_TEMP_USER_STM = conn.prepareStatement(INSERT_TEMP_USER);
		SELECT_USER_STM = conn.prepareStatement(SELECT_USER);
		SELECT_TEMP_USER_STM = conn.prepareStatement(SELECT_TEMP_USER);
	}
}
