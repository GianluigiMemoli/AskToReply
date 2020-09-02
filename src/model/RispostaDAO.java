package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RispostaDAO {

	public static void addRisposta(RispostaBean risposta) {
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 4);
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
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemoveRisposta", 1);
			callProcedure.setString(1, idRisposta);
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	//DA CORREGGERE:
	public static ResultSet getStoricoRisposte(String idUser){
		DBManager dbManager = DBManager.getInstance();
		ResultSet storico = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRiposteByUser", 1);
			callProcedure.setString(1, idUser);
			storico = callProcedure.getResultSet();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return storico;
	}
}