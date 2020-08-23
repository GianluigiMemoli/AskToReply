package model;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UtenteDAO {
	
	
	static Logger log = Logger.getLogger(UtenteDAO.class.getName());
	
	public static void doAddUtente(UtenteBean newUser) {
		DBManager dbManager = DBManager.getInstance();
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateUtente", 7);
			log.info(callProcedure.toString());
			callProcedure.setString(1, newUser.getEmail());
			callProcedure.setString(2, newUser.getPasswordHash());
			callProcedure.setString(3, newUser.getUsername());
			callProcedure.setString(4, newUser.getCognome());
			callProcedure.setString(5, newUser.getNome());
			callProcedure.setInt(6, newUser.getRuoloID());
			
			//callProcedure.registerOutParameter(7, java.sql.Types.VARCHAR);
			int result = callProcedure.executeUpdate();
			
			log.info("user insertion got outcome: " + result);
			log.info("id = " + callProcedure.getString(7));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

