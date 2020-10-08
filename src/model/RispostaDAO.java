package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class RispostaDAO {

	public static void addRisposta(RispostaBean risposta) {
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 4);
			callProcedure.setString(1, risposta.getIdDomanda());
			callProcedure.setString(2, risposta.getCorpo());
			//callProcedure.setString(3, risposta.getAllegati());				//CONTROLLARE la SP
			callProcedure.setString(3, risposta.getIdAutore());
			callProcedure.setDate(4, new java.sql.Date(risposta.getDataPubblicazione().getTime()));
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
	public static ArrayList<RispostaBean> getStoricoRisposte(String idUser){
		
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = null;
		RispostaBean risposta;
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRiposteByUser", 1);
			callProcedure.setString(1, idUser);
			rs = callProcedure.getResultSet();
			
			while(rs.next()) {
				risposta = new RispostaBean(
						rs.getString("id"),
						rs.getString("idDomanda"), 
						rs.getString("corpo"), 
						//rs.getString("allegati"), 
						rs.getString("idAutore"),
						rs.getDate("dataPubblicazione")
						);
				elencoRisposte.add(risposta);
			}
			
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return elencoRisposte;
	}
}

// da testare
