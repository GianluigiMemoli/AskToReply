package gestioneRisposta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Logger;

import gestioneAccount.PartecipanteBean;
import gestioneAccount.UtenteBean;
import gestioneDomanda.DomandaBean;
import moderazione.SegnalazioneRispostaDAO;
import util.DBManager;

public class RispostaDAO {

	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName());

	public static RispostaBean addRisposta(RispostaBean risposta) {
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateRisposta", 5);
			callProcedure.setString(1, risposta.getDomanda().getId());
			callProcedure.setString(2, risposta.getCorpo());
			callProcedure.setString(3, risposta.getAutore().getId());
			callProcedure.setDate(4, new java.sql.Date(risposta.getDataPubblicazione().getTime()));
			callProcedure.registerOutParameter(5, Types.VARCHAR);
			callProcedure.executeUpdate();
			
			risposta.setId(callProcedure.getNString(5));
			return risposta;
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

	public static ArrayList<RispostaBean> getStoricoRisposteByUtente(UtenteBean utente, int start, int offset) {
		String idUser = utente.getId();
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = new ArrayList<RispostaBean>();
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRisposteByUser", 3);
			callProcedure.setString(1, idUser);
			callProcedure.setInt(2, start);
			callProcedure.setInt(3, offset);
			rs = callProcedure.executeQuery();
			while (rs.next()) {
				risposta = new RispostaBean();
				risposta.setId(rs.getString("id"));
				risposta.setCorpo(rs.getString("corpo"));
				PartecipanteBean pb = new PartecipanteBean();
				pb.setId(rs.getString("idAutore"));
				risposta.setAutore(pb);
				risposta.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				DomandaBean dom = new DomandaBean();
				dom.setId((rs.getString("idDomanda")));
				risposta.setDomanda(dom);
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
				PartecipanteBean pb=new PartecipanteBean();
				pb.setId(rs.getNString("idAutore"));
				DomandaBean dom = new DomandaBean();
				dom.setId((rs.getString("idDomanda")));
				RispostaBean risp = new RispostaBean(rs.getString("id"), dom,
						rs.getString("corpo"), pb, rs.getDate("dataPubblicazione"));
				return risp;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getNumeroRisposteByUtente(PartecipanteBean user) {
		DBManager manager = DBManager.getInstance();
		int amount = 0;
		
		try {
		CallableStatement stmt = manager.prepareStoredProcedureCall("GetNumeroRisposteByUtente", 1);
		stmt.setString(1, user.getId());
		ResultSet rs = stmt.executeQuery();	
		if (rs.next()) {
			amount = rs.getInt("numeroRisposte");
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return amount;
	}
	
	
	public static ArrayList<RispostaBean> getRisposteByIdDomanda(String idDomanda, int numPagina) {//aggiunta: int x
		DBManager dbManager = DBManager.getInstance();
		ResultSet rs = null;
		ArrayList<RispostaBean> elencoRisposte = new ArrayList<RispostaBean>();
		RispostaBean risposta = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRisposteByIdDomanda", 2); //modificato 1 in 2
			callProcedure.setString(1, idDomanda);
			callProcedure.setInt(2, numPagina*4);
			rs = callProcedure.executeQuery();
			log.info("prima del while");
			while (rs.next()) {
				risposta = new RispostaBean();
				risposta.setId(rs.getString("id"));
				risposta.setCorpo(rs.getString("corpo"));
				risposta.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				PartecipanteBean autore = new PartecipanteBean();
				autore.setId(rs.getString("idAutore"));
				risposta.setAutore(autore);
				DomandaBean dom = new DomandaBean();
				dom.setId((rs.getString("idDomanda")));
				risposta.setDomanda(dom);
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