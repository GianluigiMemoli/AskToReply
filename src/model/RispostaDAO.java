package model;

import java.sql.SQLException;

public class RispostaDAO {

	public static void addRisposta(RispostaBean risposta) {
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatment callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 4);
			callProcedure.setString(1, risposta.getIdDomanda());
			callProcedure.setString(2, risposta.getCorpo());
			callProcedure.setString(3, risposta.getAllegati());
			callProcedure.setString(4, risposta.getIdAutore());
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void removeRisposta(String idRisposta) {
		/* */
	}
	
	//getRisposteByUser 
	
}
