package gestioneRisposta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import gestioneAccount.PartecipanteBean;
import util.DBManager;

public class VotazioneDAO {
	
	static Logger log = Logger.getLogger(VotazioneDAO.class.getName()); // test

	public static void addVotazioneRisposta(VotazioneBean votazione) {
		//Utilizzo della S.P. [VotazioneRisposta(idRisposta, valore)] { Di Benedetto }
		
		DBManager dbManager = DBManager.getInstance();
		try {

			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("VotazioneRisposta", 3);
			callProcedure.setString(1, votazione.getUtente().getId());
			callProcedure.setString(2, votazione.getRisposta().getId());
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
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RimozioneVotazione", 2);
			callProcedure.setString(1, votazione.getRisposta().getId());
			callProcedure.setString(2, votazione.getUtente().getId());
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
		
			if(rs!=null)
			while(rs.next()) {
				voto=new VotazioneBean();
				RispostaBean rb = new RispostaBean();
				rb.setId(rs.getString("idRisposta"));
				voto.setRisposta(rb);
				PartecipanteBean pb = new PartecipanteBean();
				pb.setId(rs.getString("idUtente"));
				voto.setUtente(pb);
				voto.setValore(rs.getInt("valore"));
				
				elencoVoti.add(voto);
			}
			
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return elencoVoti;
	}	
}