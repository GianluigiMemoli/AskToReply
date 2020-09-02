package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// DA COMPLETARE

public class SegnalazioneRispostaDAO {

	//ASPETTO DIBENE CHE CARICA LA PROCEDURA
	public static void addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		//Utilizzo della S.P. [CreateSegnalazioneRisposta(idRisposta, idMotivazione, commento)] { Di Benedetto }
		
		//La S.P. di DiBene non c'è quindi bisogna poi controllare
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazioneRisposta", 6);
			//callProcedure.setString(1, segnalazione.getIdSegnalazione());
			callProcedure.setString(2, segnalazione.getIdMotivazione());
			//callProcedure.setString(3, segnalazione.getDataSegnalazione());
			callProcedure.setString(4, segnalazione.getStato());
			callProcedure.setString(5, segnalazione.getCommento());
			callProcedure.setString(6, segnalazione.getIdRisposta());
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	
	
	public static ArrayList<SegnalazioneRispostaBean> getElencoSegnalazioniRisposte() {
		
		DBManager dbManager = DBManager.getInstance();
		ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = null; 
		SegnalazioneRispostaBean segnalazione = null;
		ResultSet rs = null;
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioniRisposte",0);
			rs = callProcedure.getResultSet();
			
			while(rs.next()) {
				segnalazione = new SegnalazioneRispostaBean(
						rs.getString("id"),
						rs.getString("idMotivazione"), 
						rs.getString("dataSegnalazione"), 
						rs.getString("stato"), 
						rs.getString("commento"),
						rs.getString("idRisposta")
						);
				segnalazioniRisposte.add(segnalazione);
			}
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return segnalazioniRisposte;
	}
	
	
	
	public static SegnalazioneRispostaBean getSegnalazioneRispostaById(String idSegnalazione) {
		//Utilizzo della S.P. [GetSegnalazioneRispostaById] { aggiungere S.P. }
		DBManager dbManager = DBManager.getInstance();
		SegnalazioneRispostaBean segnalazioneRisposta=null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioneRispostaById", 1);		
			callProcedure.setString(1, idSegnalazione);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				segnalazioneRisposta = new SegnalazioneRispostaBean(
						rs.getString("id"),
						rs.getString("idMotivazione"), 
						rs.getString("dataSegnalazione"), 
						rs.getString("stato"), 
						rs.getString("commento"),
						rs.getString("idRisposta")
						);
			}
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		return segnalazioneRisposta;
	}
	
}