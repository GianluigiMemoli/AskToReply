package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public static  UtenteBean getUtenteByUsername(String username) {
		DBManager dbManager = DBManager.getInstance();
		UtenteBean searchedUtente = null;
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetUtenteByUsername", 1);		
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
	
	public static UtenteBean getUtenteById(String id) {
		DBManager dbManager = DBManager.getInstance();
		UtenteBean searchedUtente = null;
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetUtenteById", 1);		
		callProcedure.setString(1, id);
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
	
	public static void doAddModeratore(UtenteBean newUser) {
		DBManager dbManager = DBManager.getInstance();	
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RegistraModeratore", 5);
		
		callProcedure.setString(1, newUser.getEmail());
		callProcedure.setString(2, newUser.getPasswordHash());
		callProcedure.setString(3, newUser.getUsername());
		callProcedure.setString(4, newUser.getCognome());
		callProcedure.setString(5, newUser.getNome());																	
		callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static ArrayList<UtenteBean> doGetAllModeratori() {
		DBManager dbManager = DBManager.getInstance();	
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetModeratori", 0);
			ResultSet resultSet = callProcedure.executeQuery();
			ArrayList<UtenteBean> moderatori = new ArrayList<UtenteBean>();
			while(resultSet.next()){			
				moderatori.add(
					new UtenteBean(
						resultSet.getNString("email"),
						resultSet.getNString("passwordHash"),
						resultSet.getNString("nuovaPassword"),
						resultSet.getNString("username"),
						resultSet.getNString("nome"),
						resultSet.getNString("cognome"),
						resultSet.getInt("ruoloId"),
						resultSet.getBoolean("isDisattivato"),
						resultSet.getNString("id")					
					)
			);
			}		
		return moderatori;
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	public static void doDeactivateUser(String id) {
		DBManager dbManager = DBManager.getInstance();	
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("DisattivaAccount", 1);
		callProcedure.setNString(1, id);
		callProcedure.executeUpdate();
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	
}

