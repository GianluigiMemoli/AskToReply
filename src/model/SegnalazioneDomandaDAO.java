package model;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SegnalazioneDomandaDAO {

	public static void addSegnalazioneDomanda(SegnalazioneDomandaBean segnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			// Segnalazione:
			
			/*
			 * N.B. Se la Stored non è presente, l'eccezione lanciata è comunque
			 * SQLException: Parameter nummber X is not an OUT parameter
			 */
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("CreateSegnalazioneDomanda", 6);
			
			stmt.setInt(1, segnalazione.getMotivazione().getId());
			stmt.setDate(2, new Date(segnalazione.getDataSegnalazione().getTime()));
			stmt.setInt(3, segnalazione.getStato());
			stmt.setNString(4, segnalazione.getCommento());
			stmt.setNString(5, segnalazione.getDomandaSegnalata().getId());
			stmt.setNString(6, segnalazione.getUtente().getId());

			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<SegnalazioneDomandaBean> getAll() {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetSegnalazioniDomande", 0);
			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<SegnalazioneDomandaBean> segnalazioni = new ArrayList<SegnalazioneDomandaBean>();
			
			while(rs.next()) {
				
				SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
				
				segnalazione.setId(rs.getString("id"));
				
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setId(rs.getInt("idMotivazione"));
				segnalazione.setMotivazione(motivazione);
				
				segnalazione.setDataSegnalazione(rs.getDate("dataSegnalazione"));
				
				segnalazione.setStato(rs.getInt("stato"));
				
				segnalazione.setCommento(rs.getString("commento"));
				
				DomandaBean domanda = new DomandaBean();
				domanda.setId(rs.getString("idDomanda"));
				segnalazione.setDomandaSegnalata(domanda);
				
				PartecipanteBean partecipante = new PartecipanteBean();
				partecipante.setId(rs.getString("idUtente"));
				
				segnalazioni.add(segnalazione);
				
			}
			
			return segnalazioni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static SegnalazioneDomandaBean getSegnalazioneDomandaById(String idSegnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetSegnalazioneDomandaById", 1);
			
			stmt.setString(1, idSegnalazione);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
				
				segnalazione.setId(rs.getString("id"));
				
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setId(rs.getInt("idMotivazione"));
				segnalazione.setMotivazione(motivazione);
				
				segnalazione.setDataSegnalazione(rs.getDate("dataSegnalazione"));
				
				segnalazione.setStato(rs.getInt("stato"));
				
				segnalazione.setCommento(rs.getString("commento"));
				
				DomandaBean domanda = new DomandaBean();
				domanda.setId(rs.getString("idDomanda"));
				segnalazione.setDomandaSegnalata(domanda);
				
				PartecipanteBean partecipante = new PartecipanteBean();
				partecipante.setId(rs.getString("idUtente"));
				
				return segnalazione;
				
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void updateStatoSegnalazioneDomanda(SegnalazioneDomandaBean segnalazione) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("RisolviSegnalazione", 2);
			
			stmt.setString(1, segnalazione.getId());
			stmt.setInt(2, segnalazione.getStato());
			
			stmt.executeQuery();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<SegnalazioneDomandaBean> getSegnalazioniDomanda(int start, int end) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetSegnalazioniDomandaLimit", 2);
			
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<SegnalazioneDomandaBean> segnalazioni = new ArrayList<SegnalazioneDomandaBean>();
			
			while(rs.next()) {
				
				SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
				
				segnalazione.setId(rs.getNString("s.id"));
				
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setId(rs.getInt("s.idMotivazione"));
				segnalazione.setMotivazione(motivazione);
				
				segnalazione.setDataSegnalazione(rs.getDate("s.dataSegnalazione"));
				segnalazione.setStato(rs.getInt("s.stato"));
				segnalazione.setCommento(rs.getString("s.commento"));
				
				DomandaBean domanda = new DomandaBean();
				domanda.setId(rs.getString("sd.idDomanda"));
				segnalazione.setDomandaSegnalata(domanda);
				
				PartecipanteBean partecipante = new PartecipanteBean();
				partecipante.setId(rs.getString("s.idUtente"));
				
				segnalazioni.add(segnalazione);
				
			}
			
			return segnalazioni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static int getNumeroSegnalazioniDomanda() {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetNumeroSegnalazioniDomanda", 0);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
}
