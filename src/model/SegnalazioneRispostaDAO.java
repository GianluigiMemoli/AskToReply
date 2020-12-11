package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


// DA COMPLETARE

public class SegnalazioneRispostaDAO {
	
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test
	

	//ASPETTO DIBENE CHE CARICA LA PROCEDURA //Controllarle perchè c'è qualcosa che non va
	public static void addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		//Utilizzo della S.P. [CreateSegnalazioneRisposta(idRisposta, idMotivazione, commento)] { Di Benedetto }
		
		//La S.P. di DiBene non c'è quindi bisogna poi controllare // Ne ha aggiunte 2 e non so perchè
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazione", 4);
			callProcedure.setInt(1, segnalazione.getIdMotivazione());
			callProcedure.setDate(2, new java.sql.Date(segnalazione.getDataSegnalazione().getTime()));
			callProcedure.setInt(3, segnalazione.getStato());
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

	
	
	public static void updateStatoSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("RisolviSegnalazione", 2);
			
			stmt.setString(1, segnalazione.getIdSegnalazione());
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
			//rs = callProcedure.getResultSet();
			rs = callProcedure.executeQuery();
			//log.info("pw");
			while(rs.next()){
				segnalazioneRisposta = new SegnalazioneRispostaBean();
				segnalazioneRisposta.setIdSegnalazione(rs.getString("id"));
				segnalazioneRisposta.setCorpoRisposta(RispostaDAO.getCorpoRispostaById((rs.getString("idRisposta"))).getCorpo());
				segnalazioneRisposta.setTitoloDomanda((DomandaDAO.getDomandaById(RispostaDAO.getRispostaById((rs.getString("idRisposta"))).getIdDomanda())).getTitolo());
				segnalazioneRisposta.setCorpoDomanda((DomandaDAO.getDomandaById(RispostaDAO.getRispostaById((rs.getString("idRisposta"))).getIdDomanda())).getCorpo());
				segnalazioneRisposta.setMotivazione((MotivazioneDAO.getMotivazioneById(rs.getInt("idMotivazione"))).getNome());
				segnalazioneRisposta.setDataSegnalazione(rs.getDate("dataSegnalazione")); 
				segnalazioneRisposta.setStato(rs.getInt("stato")); 
				segnalazioneRisposta.setCommento(rs.getString("commento"));
				segnalazioneRisposta.setIdRisposta(rs.getString("idRisposta")); //aggiunta ora
				segnalazioniRisposte.add(segnalazioneRisposta);
			}
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		return segnalazioniRisposte;
	}
	
	
	
	public static SegnalazioneRispostaBean getSegnalazioneRispostaById(String idSegnalazione) {
		//Utilizzo della S.P. [GetSegnalazioneRispostaById] { aggiungere S.P. }
		DBManager dbManager = DBManager.getInstance();
		SegnalazioneRispostaBean segnalazioneRisposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioneRispostaById", 1);		
			callProcedure.setString(1, idSegnalazione);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				segnalazioneRisposta = new SegnalazioneRispostaBean();
				segnalazioneRisposta.setIdSegnalazione(rs.getString("id"));
				segnalazioneRisposta.setCorpoRisposta(RispostaDAO.getCorpoRispostaById((rs.getString("idRisposta"))).getCorpo());
				segnalazioneRisposta.setIdMotivazione(rs.getInt("idMotivazione"));
				segnalazioneRisposta.setDataSegnalazione(rs.getDate("dataSegnalazione")); 
				segnalazioneRisposta.setStato(rs.getInt("stato")); 
				segnalazioneRisposta.setCommento(rs.getString("commento"));
				segnalazioneRisposta.setIdRisposta(rs.getString("idRisposta")); //aggiunta ora
			}
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		return segnalazioneRisposta;
	}	
}



/*package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


// DA COMPLETARE

public class SegnalazioneRispostaDAO {
	
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test
	

	//ASPETTO DIBENE CHE CARICA LA PROCEDURA //Controllarle perchè c'è qualcosa che non va
	public static void addSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		//Utilizzo della S.P. [CreateSegnalazioneRisposta(idRisposta, idMotivazione, commento)] { Di Benedetto }
		
		//La S.P. di DiBene non c'è quindi bisogna poi controllare // Ne ha aggiunte 2 e non so perchè
		
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateSegnalazione", 4);
			callProcedure.setString(1, segnalazione.getIdMotivazione());
			callProcedure.setDate(2, new java.sql.Date(segnalazione.getDataSegnalazione().getTime()));
			callProcedure.setInt(3, segnalazione.getStato());
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

	
	
	public static void updateStatoSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("RisolviSegnalazione", 2);
			
			stmt.setString(1, segnalazione.getIdSegnalazione());
			stmt.setInt(2, segnalazione.getStato());
			
			stmt.executeQuery();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<SegnalazioneRispostaBean> getElencoSegnalazioniRisposte() {
		ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = new ArrayList<SegnalazioneRispostaBean>();

		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetSegnalazioniRisposte",0);
			//rs = callProcedure.getResultSet();
			rs = callProcedure.executeQuery();
			//log.info("pw");
			while(rs.next()) {
				//log.info("dw");
				log.info(rs.getString("commento"));
				SegnalazioneRispostaBean segnalazione = new SegnalazioneRispostaBean(
						rs.getString("id"),	
						rs.getString("idRisposta"),
						rs.getString("idMotivazione"), 
						rs.getDate("dataSegnalazione"), 
						rs.getInt("stato"), 
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
						rs.getInt("stato"), 
						rs.getString("commento")
						);
			}
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		return segnalazioneRisposta;
	}	
}*/