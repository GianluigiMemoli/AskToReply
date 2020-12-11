package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RispostaDAO {

	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test

	
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
		log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		log.info("L'id dell'utente è: "+idUser);
		log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = new ArrayList<RispostaBean>();
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRisposteByUser", 1);
			callProcedure.setString(1, idUser);
			//rs = callProcedure.getResultSet();//esplosione
			rs = callProcedure.executeQuery();
			
			if(rs==null)log.info("RRRRRSSSS E' NUUUULLLL");
			else log.info("RRRRRSSSS E' PIEEENOOO");

		
			
			log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			log.info("Prima del while con rs.next");
			log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			while(rs.next()) {
				log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				log.info("Dentro il while");
				log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				risposta = new RispostaBean();
				risposta.setId(rs.getString("id"));
				risposta.setIdDomanda(rs.getString("idDomanda")); 
				risposta.setCorpo(rs.getString("corpo"));
				risposta.setIdAutore(rs.getString("idAutore"));
				risposta.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				elencoRisposte.add(risposta);
				log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				log.info(rs.getString("corpo"));
				log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			}
			log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			log.info("Dopo il while");
			log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
				RispostaBean risp = new RispostaBean(rs.getString("id"),rs.getString("idDomanda"),rs.getString("corpo"), rs.getNString("idAutore"),rs.getDate("dataPubblicazione"));		        
		        return risp;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//aggiunta
public static RispostaBean getCorpoRispostaById(String id) {
	
	DBManager manager = DBManager.getInstance();
	
	try {
		CallableStatement stmt = manager.prepareStoredProcedureCall("GetCorpoRispostaById", 1); //aggiungere procedura
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			RispostaBean risp = new RispostaBean();
			risp.setCorpo(rs.getString("corpo"));
	        return risp;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}