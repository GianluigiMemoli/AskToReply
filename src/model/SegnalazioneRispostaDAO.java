package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

// DA COMPLETARE

public class SegnalazioneRispostaDAO {

	//ASPETTO DIBENE CHE CARICA LA PROCEDURA //Controllarle perchè c'è qualcosa che non va
	public static void addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		//Utilizzo della S.P. [CreateSegnalazioneRisposta(idRisposta, idMotivazione, commento)] { Di Benedetto }
		
		//La S.P. di DiBene non c'è quindi bisogna poi controllare // Ne ha aggiunte 2 e non so perchè
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazione", 4);
			callProcedure.setString(1, segnalazione.getIdMotivazione());
			callProcedure.setDate(2, new java.sql.Date(segnalazione.getDataSegnalazione().getTime()));
			callProcedure.setString(3, segnalazione.getStato());
			callProcedure.setString(4, segnalazione.getCommento());
			//callProcedure.executeUpdate();
			ResultSet rsId = callProcedure.getResultSet();
			CallableStatement callProcedure2 = dbManager.prepareStoredProcedureCall("CreateSegnalazioneRisposta", 2);
			callProcedure2.setString(1, rsId.getString("id"));
			callProcedure2.setString(2, segnalazione.getIdRisposta());
			callProcedure2.executeUpdate();
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
						rs.getString("idRisposta"),
						rs.getString("idMotivazione"), 
						rs.getDate("dataSegnalazione"), 
						rs.getString("stato"), 
						rs.getString("commento")
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
						rs.getString("idRisposta"),
						rs.getString("idMotivazione"), 
						rs.getDate("dataSegnalazione"), 
						rs.getString("stato"), 
						rs.getString("commento")
						);
			}
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		return segnalazioneRisposta;
	}
	
}