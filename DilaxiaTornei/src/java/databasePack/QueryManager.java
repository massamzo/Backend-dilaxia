package databasePack;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QueryManager {
	private static final String INSERT_USER = "INSERT INTO ?(email_utente, nome, cognome, password, data_nascita, sesso, data_creazione, privilegi_flg) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_TEMP_USER = "INSERT INTO ?(email_utente, nome, cognome, password, data_nascita, sesso, otp, privilegi_flg, expire_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER = "SELECT email_utente as email, nome, cognome, password, otp, expire_at FROM ? WHERE email_utente = ?";
	
	public static PreparedStatement INSERT_USER_STM;
	public static PreparedStatement INSERT_TEMP_USER_STM;
	public static PreparedStatement SELECT_USER_STM;
	
	public QueryManager(Connection conn) {
		INSERT_USER_STM = conn.prepareStatement(INSERT_USER);
		INSERT_TEMP_USER_STM = conn.prepareStatement(INSERT_TEMP_USER);
		SELECT_USER_STM = conn.prepareStatement(SELECT_USER);
	}
}
