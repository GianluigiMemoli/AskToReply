package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeratoreDAO {
	
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
	
	public static ArrayList<ModeratoreBean> doGetAllModeratori() {
		DBManager dbManager = DBManager.getInstance();	
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetModeratori", 0);
			ResultSet resultSet = callProcedure.executeQuery();
			ArrayList<ModeratoreBean> moderatori = new ArrayList<ModeratoreBean>();
			while(resultSet.next()){			
				moderatori.add(
					new ModeratoreBean(
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
	
	public static void doDeactivateModeratore(String id) {
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
  

