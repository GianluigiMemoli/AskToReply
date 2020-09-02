package model;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class VotazioneDAO {
	
	public static void addVotazioneRisposta(VotazioneBean votazione) {
		//Utilizzo della S.P. [VotazioneRisposta(idRisposta, valore)] { Di Benedetto }
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("VotazioneRisposta", 3);
			callProcedure.setString(1, votazione.getIdUtente());
			callProcedure.setString(2, votazione.getIdRisposta());
			callProcedure.setInt(3, votazione.getValore());				//	setInt oppure setString	???
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
		
	public static void removeVotazioneRisposta(String idVotazione) {
		//Utilizzo della S.P. [RemoveVotazioneRisposta(idRisposta)] { Di Benedetto }
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemoveVotazioneRisposta", 1);
			callProcedure.setString(1, idVotazione);
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
}