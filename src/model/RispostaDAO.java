package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RispostaDAO {

	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); // test

	public static RispostaBean addRisposta(RispostaBean risposta) {
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 5);
			callProcedure.setString(1, risposta.getIdDomanda());
			callProcedure.setString(2, risposta.getCorpo());
			callProcedure.setString(3, risposta.getIdAutore());
			callProcedure.setDate(4, new java.sql.Date(risposta.getDataPubblicazione().getTime()));
			callProcedure.registerOutParameter(5, Types.VARCHAR);
			callProcedure.executeUpdate();
			RispostaBean rb = new RispostaBean();
			rb.setId(callProcedure.getNString(5)); // forse è 6???
			return rb;
		} catch (SQLException exc) {
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
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}

	public static ArrayList<RispostaBean> getStoricoRisposteByUtente(UtenteBean utente, int numPagina) {//aggiunta: int x
		String idUser = utente.getId();
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = new ArrayList<RispostaBean>();
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRisposteByUser", 2); //modificato 1 in 2
			callProcedure.setString(1, idUser);
			callProcedure.setInt(2, numPagina*4); //rigo aggiunto
			// rs = callProcedure.getResultSet();//esplosione
			rs = callProcedure.executeQuery();
			while (rs.next()) {
				risposta = new RispostaBean();
				risposta.setId(rs.getString("id"));
				risposta.setIdDomanda(rs.getString("idDomanda"));
				risposta.setCorpo(rs.getString("corpo"));
				risposta.setIdAutore(rs.getString("idAutore"));
				risposta.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				risposta.setTitoloDomanda(DomandaDAO.getDomandaById(rs.getString("idDomanda")).getTitolo()); // 151220
				elencoRisposte.add(risposta);
			}
			return elencoRisposte;
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static RispostaBean getRispostaById(String id) {

		DBManager manager = DBManager.getInstance();

		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetRispostaById", 1);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				RispostaBean risp = new RispostaBean(rs.getString("id"), rs.getString("idDomanda"),
						rs.getString("corpo"), rs.getNString("idAutore"), rs.getDate("dataPubblicazione"));
				return risp;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static ArrayList<RispostaBean> getRisposteByIdDomanda(String idDomanda, int numPagina) {//aggiunta: int x
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = new ArrayList<RispostaBean>();
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRisposteByIdDomanda", 2); //modificato 1 in 2
			callProcedure.setString(1, idDomanda);
			callProcedure.setInt(2, numPagina*4); //rigo aggiunto
			// rs = callProcedure.getResultSet();//esplosione
			rs = callProcedure.executeQuery();
			log.info("prima del while");
			while (rs.next()) {
				log.info("EEEEEEEEEENNNNNNNTROOOOOOOOOOOO NEEEEEEEEE WHIIIIIIIIIIIIIIIIILLLLLLLLLLLLEEEEEEEEEEE");
				risposta = new RispostaBean();
				risposta.setId(rs.getString("id"));
				risposta.setIdDomanda(rs.getString("idDomanda"));
				risposta.setCorpo(rs.getString("corpo"));
				risposta.setIdAutore(rs.getString("idAutore"));
				risposta.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				risposta.setTitoloDomanda(DomandaDAO.getDomandaById(rs.getString("idDomanda")).getTitolo()); // 151220
				risposta.setAutore(UtenteDAO.getUtenteById(rs.getString("idAutore")).getUsername());
				
				int miPiace=0;
				int nonMiPiace=0;
				ArrayList <VotazioneBean> vb = VotazioneDAO.getVotazioniByIdRisposta(rs.getString("id"));
				risposta.setVoti(vb);//aggiunto
				if(vb!=null)for(int k=0; k<vb.size(); k++)if(vb.get(k).getValore()==1)miPiace+=1;else nonMiPiace+=1;
				
				log.info("SSSSSSSSSSSSSSSSSSSSSS");

				log.info("STA risposta["+rs.getString("corpo")+"]ha avuto "+miPiace+"mi Piace");
				log.info("STA risposta["+rs.getString("corpo")+"] ha avuto "+nonMiPiace+"Non mi Piace");

				log.info("SSSSSSSSSSSSSSSSSSSSSS");

				risposta.setMiPiace(miPiace);
				risposta.setNonMiPiace(nonMiPiace);
				elencoRisposte.add(risposta);
			}
			return elencoRisposte;
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	public static int countRisposteByDomandaId(String id) {
		DBManager manager = DBManager.getInstance();
		int num = 0;

		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetNumeroRisposteByDomanda", 1);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	
	
	

	public static ArrayList<RispostaBean> getRisposteApprezzate(String idutente){		
		String query = "SELECT idRisposta FROM votazioni WHERE idUtente = ? AND valore = 1";		
		DBManager dbManager = DBManager.getInstance();
		ArrayList<RispostaBean> risposte = new ArrayList<RispostaBean>();
		try {
			PreparedStatement stmt = dbManager.createPreparedStatement(query);
			stmt.setString(1, idutente);
			ResultSet records = stmt.executeQuery();			
			while(records.next()) {
				String idRisp= records.getNString("idRisposta");
				RispostaBean rispostaB = new RispostaBean();
				rispostaB.setId(idRisp);
				risposte.add(rispostaB);		
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return risposte;
	}
	
	
	public static ArrayList<RispostaBean> getRisposteNonApprezzate(String idutente){		
		String query = "SELECT idRisposta FROM votazioni WHERE idUtente = ? AND valore = -1";		
		DBManager dbManager = DBManager.getInstance();
		ArrayList<RispostaBean> risposte = new ArrayList<RispostaBean>();
		try {
			PreparedStatement stmt = dbManager.createPreparedStatement(query);
			stmt.setString(1, idutente);
			ResultSet records = stmt.executeQuery();			
			while(records.next()) {
				String idRisp= records.getNString("idRisposta");
				RispostaBean rispostaB = new RispostaBean();
				rispostaB.setId(idRisp);
				risposte.add(rispostaB);		
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return risposte;
	}
	
	

}