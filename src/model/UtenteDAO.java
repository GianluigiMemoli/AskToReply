package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UtenteDAO {
	
	
	static Logger log = Logger.getLogger(UtenteDAO.class.getName());	

	public static void doAddUtente(UtenteBean newUser) {
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("Registrazione", 5);
			
			callProcedure.setString(1, newUser.getEmail());
			callProcedure.setString(2, newUser.getPasswordHash());
			callProcedure.setString(3, newUser.getUsername());
			callProcedure.setString(4, newUser.getCognome());
			callProcedure.setString(5, newUser.getNome());														
			
			callProcedure.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static UtenteBean getUtenteByEmail(String email) {
		DBManager dbManager = DBManager.getInstance();
		UtenteBean searchedUtente = null;
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetUtenteByEmail", 1);		
		callProcedure.setString(1, email);
		ResultSet rs = callProcedure.executeQuery();
		if(rs.next()) {
			searchedUtente = new UtenteBean(
					rs.getString("email"),
					rs.getString("passwordHash"), 
					rs.getString("nuovaPassword"), 
					rs.getString("username"), 
					rs.getString("nome"),
					rs.getString("cognome"), 
					rs.getInt("ruoloId"), 
					rs.getBoolean("isDisattivato"), 
					rs.getString("id")
					);
		}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchedUtente;
	}
	
	public static UtenteBean getUtenteByUsername(String username) {
		DBManager dbManager = DBManager.getInstance();
		UtenteBean searchedUtente = null;
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetUtenteByEmail", 1);		
		callProcedure.setString(1, username);
		ResultSet rs = callProcedure.executeQuery();
		if(rs.next()) {
			searchedUtente = new UtenteBean(
					rs.getString("email"),
					rs.getString("passwordHash"), 
					rs.getString("nuovaPassword"), 
					rs.getString("username"), 
					rs.getString("nome"),
					rs.getString("cognome"), 
					rs.getInt("ruoloId"), 
					rs.getBoolean("isDisattivato"), 
					rs.getString("id")
					);
		}
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchedUtente;
	}
}

