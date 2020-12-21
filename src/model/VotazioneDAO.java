package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class VotazioneDAO {
	
	static Logger log = Logger.getLogger(VotazioneDAO.class.getName()); // test

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
		
	public static void removeVotazioneRisposta(VotazioneBean votazione) { //
		//Utilizzo della S.P. [RemoveVotazioneRisposta(idRisposta)] { Di Benedetto }
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemoveVotazioneRisposta", 2);
			callProcedure.setString(1, votazione.getIdUtente());
			callProcedure.setString(2, votazione.getIdRisposta());
			callProcedure.executeUpdate();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	
	public static ArrayList<VotazioneBean> getVotazioniByIdRisposta(String idRisposta){
		
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<VotazioneBean> elencoVoti = new ArrayList<VotazioneBean>();

		VotazioneBean voto;
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetVotazioniByRisposta", 1);  //DA SCRIVERE (SP)
			callProcedure.setString(1, idRisposta);
			rs = callProcedure.executeQuery();
			
			log.info("prima del WHILEEE DI VOTAZIONE DAOOOO");

			if(rs!=null)
			while(rs.next()) {
				log.info("NEEEL WHILEEE DI VOTAZIONE DAOOOO");
				voto=new VotazioneBean();
				voto.setIdRisposta(rs.getString("idRisposta"));
				voto.setIdUtente(rs.getString("idUtente"));
				voto.setValore(rs.getInt("valore"));
				
				elencoVoti.add(voto);
			}
			
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return elencoVoti;
	}

	//Bisogna scrivere la SP GetVotazioniByRisposta e forse anche IsVotataByUser	
	
}