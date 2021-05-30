package model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Logger;


// DA COMPLETARE

public class SegnalazioneRispostaDAO {
	
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName());
	
	public static SegnalazioneRispostaBean addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazione", 5);
			callProcedure.setInt(1, segnalazione.getMotivazione().getId());
			callProcedure.setDate(2, new java.sql.Date(segnalazione.getDataSegnalazione().getTime()));
			callProcedure.setInt(3, segnalazione.getStato());
			callProcedure.setString(4, segnalazione.getCommento());
			callProcedure.registerOutParameter(5, Types.VARCHAR);
			ResultSet rsId = callProcedure.executeQuery();
			
			if (rsId.next()){
			CallableStatement callProcedure2 = dbManager.prepareStoredProcedureCall("CreateSegnalazioneRisposta", 2);
			callProcedure2.setString(1, rsId.getString("id"));
			segnalazione.setIdSegnalazione(rsId.getString("id"));
			callProcedure2.setString(2, segnalazione.getRispostaSegnalata().getId());
			callProcedure2.executeQuery();
			}}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return segnalazione;
	}

	public static void updateStatoSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("RisolviSegnalazione", 2);
			
			stmt.setString(1, segnalazione.getIdSegnalazione());
			//log.info(segnalazione.getStato());
			stmt.setInt(2, segnalazione.getStato());
			stmt.executeQuery();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<SegnalazioneRispostaBean> getElencoSegnalazioniRisposte() {
		ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = new ArrayList<SegnalazioneRispostaBean>();
		SegnalazioneRispostaBean segnalazioneRisposta = null;
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioniRisposte",0);
			rs = callProcedure.executeQuery();
			while(rs.next()){
				segnalazioneRisposta = new SegnalazioneRispostaBean();
				segnalazioneRisposta.setIdSegnalazione(rs.getString("id"));
				segnalazioneRisposta.setRispostaSegnalata(RisposteManager.getRispostaById(rs.getString("idRisposta")));
				segnalazioneRisposta.setDomanda(DomandaDAO.getDomandaById(RispostaDAO.getRispostaById((rs.getString("idRisposta"))).getDomanda().getId()));
				MotivazioneBean mb = new MotivazioneBean();
				mb.setId(rs.getInt("idMotivazione"));
				segnalazioneRisposta.setMotivazione(mb);
				segnalazioneRisposta.setDataSegnalazione(rs.getDate("dataSegnalazione")); 
				segnalazioneRisposta.setStato(rs.getInt("stato")); 
				segnalazioneRisposta.setCommento(rs.getString("commento"));
				segnalazioniRisposte.add(segnalazioneRisposta);
			}
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return segnalazioniRisposte;
	}
	
	public static SegnalazioneRispostaBean getSegnalazioneRispostaById(String idSegnalazione) {
		DBManager dbManager = DBManager.getInstance();
		SegnalazioneRispostaBean segnalazioneRisposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioneRispostaById", 1);		
			callProcedure.setString(1, idSegnalazione);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				segnalazioneRisposta = new SegnalazioneRispostaBean();
				segnalazioneRisposta.setIdSegnalazione(rs.getString("id"));
				MotivazioneBean mb = new MotivazioneBean();
				mb.setId(rs.getInt("idMotivazione"));
				segnalazioneRisposta.setMotivazione(mb);
				segnalazioneRisposta.setDataSegnalazione(rs.getDate("dataSegnalazione")); 
				segnalazioneRisposta.setStato(rs.getInt("stato")); 
				segnalazioneRisposta.setCommento(rs.getString("commento"));
				segnalazioneRisposta.setRispostaSegnalata(RisposteManager.getRispostaById(rs.getString("idRisposta")));
			}
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		return segnalazioneRisposta;
	}	
}
