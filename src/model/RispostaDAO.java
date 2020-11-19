package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class RispostaDAO {

	public static RispostaBean addRisposta(RispostaBean risposta) {
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 4);
			callProcedure.setString(1, risposta.getIdDomanda());
			callProcedure.setString(2, risposta.getCorpo());	
			callProcedure.setString(3, risposta.getIdAutore());
			//callProcedure.setDate(4, new java.sql.Date(risposta.getDataPubblicazione().getTime())); //Apportare modifica al DB?
			callProcedure.registerOutParameter(4, Types.VARCHAR);
			callProcedure.executeUpdate();
			RispostaBean rb = new RispostaBean();
			rb.setId(callProcedure.getNString(5));
			return rb;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static void removeRisposta(RispostaBean risposta) {
		DBManager dbManager = DBManager.getInstance();
		String idRisposta = risposta.getId();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemoveRisposta", 1);
			callProcedure.setString(1, idRisposta);
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}

	public static ArrayList<RispostaBean> getStoricoRisposteByUtente(UtenteBean utente){
		String idUser = utente.getId();
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = null;
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRiposteByUser", 1);
			callProcedure.setString(1, idUser);
			rs = callProcedure.getResultSet();

			while(rs.next()) {
				risposta = new RispostaBean(
						rs.getString("id"),
						rs.getString("idDomanda"), 
						rs.getString("corpo"), 
						rs.getString("idAutore"),
						rs.getDate("dataPubblicazione")
						);
				elencoRisposte.add(risposta);
			}
			return elencoRisposte;
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	
	
public static RispostaBean getRispostaById(String id) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetRispostaById", 1); //aggiungere procedura
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				RispostaBean risp = new RispostaBean(rs.getString("id"),rs.getString("idDomanda"),rs.getString("corpo"), rs.getNString("idAutore"),rs.getDate("DataPubblicazione"));		        
		        return risp;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}