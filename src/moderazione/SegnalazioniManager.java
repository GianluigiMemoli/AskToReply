package moderazione;

import java.util.ArrayList;
import java.util.Date;

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.CategoriaDAO;
import gestioneDomanda.DomandaBean;
import gestioneDomanda.DomandeManager;

public class SegnalazioniManager {

	private final static int LUNGHEZZA_MASSIMA_COMMENTO = 256;
	
	public void creazioneSegnalazioneDomanda(
			MotivazioneBean motivazione, 
			Date dataSegnalazione,
			String commento,
			DomandaBean domandaSegnalata,
			PartecipanteBean utente) throws Exception {
		
		if(commento.length() > LUNGHEZZA_MASSIMA_COMMENTO) {
			throw new Exception("Lunghezza commento segnalazione superiore a " + LUNGHEZZA_MASSIMA_COMMENTO);
		}

		
		SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
		segnalazione.setMotivazione(motivazione);
		segnalazione.setDataSegnalazione(dataSegnalazione);
		segnalazione.setStato(SegnalazioneBean.DA_GESTIRE);
		segnalazione.setCommento(commento);
		segnalazione.setDomandaSegnalata(domandaSegnalata);
		segnalazione.setUtente(utente);
		SegnalazioneDomandaDAO.addSegnalazioneDomanda(segnalazione);
		
	}
	
	public void creazioneSegnalazioneRisposta(SegnalazioneRispostaBean srb) throws Exception {
		
		if(srb.getCommento().length() > LUNGHEZZA_MASSIMA_COMMENTO) throw new Exception("Lunghezza commento segnalazione superiore a " + LUNGHEZZA_MASSIMA_COMMENTO);
		SegnalazioneRispostaDAO.addSegnalazioneRisposta(srb);
	}
	
	public ArrayList<SegnalazioneDomandaBean> getSegnalazioniDomanda(int start, int end) {
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = SegnalazioneDomandaDAO.getSegnalazioniDomanda(start, end);
		
		DomandeManager managerDomande = new DomandeManager();
		
		for (SegnalazioneDomandaBean segnalazione : segnalazioni) {
			
			// Inserimento della domanda
			
			String idDomandaSegnalata = segnalazione.getDomandaSegnalata().getId();
			
			DomandaBean domandaSegnalata = managerDomande.getDomandaById(idDomandaSegnalata);
			
			domandaSegnalata.setCategorie(CategoriaDAO.getCategorieDomandaByIdDomanda(domandaSegnalata.getId()));
			
			segnalazione.setDomandaSegnalata(domandaSegnalata);
			
			// Inserimento della motivazione
			
			int idMotivazioneSegnalazione = segnalazione.getMotivazione().getId();
			
			MotivazioneBean motivazioneSegnalazione = MotivazioneDAO.getMotivazioneById(idMotivazioneSegnalazione);
			
			segnalazione.setMotivazione(motivazioneSegnalazione);
			
		}
		
		return segnalazioni;
		
	}
	
	public ArrayList<SegnalazioneDomandaBean> getAllSegnalazioniDomanda() {
		return SegnalazioneDomandaDAO.getAll();
	}
	
	public ArrayList<SegnalazioneRispostaBean> getAllSegnalazioniRisposta() {
		// TODO
		ArrayList<SegnalazioneRispostaBean> elenco =  SegnalazioneRispostaDAO.getElencoSegnalazioniRisposte();
		for(int c=0; c<elenco.size(); c++) elenco.get(c).setMotivazione(MotivazioneDAO.getMotivazioneById(elenco.get(c).getMotivazione().getId()));
		return elenco;
	}

	public SegnalazioneDomandaBean getSegnalazioneDomanda(String id) {
		return SegnalazioneDomandaDAO.getSegnalazioneDomandaById(id);
	}

	public SegnalazioneRispostaBean getSegnalazioneRisposta(String id) {
		// TODO
		SegnalazioneRispostaBean srb = SegnalazioneRispostaDAO.getSegnalazioneRispostaById(id);
		srb.setMotivazione(MotivazioneDAO.getMotivazioneById(srb.getMotivazione().getId()));
		return srb;
	}

	public void risolviSegnalazioneDomanda(SegnalazioneDomandaBean segnalazioneDaRisolvere) {
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = getAllSegnalazioniDomanda();
		
		String idDomandaSegnalata = segnalazioneDaRisolvere.getDomandaSegnalata().getId();
		int idMotivazioneSegnalazioneDaRisolvere = segnalazioneDaRisolvere.getMotivazione().getId();
		
		for (SegnalazioneDomandaBean segnalazioneDomandaBean : segnalazioni) {
					
			if(segnalazioneDomandaBean.getDomandaSegnalata().getId().equals(idDomandaSegnalata)) {
				
				if(idMotivazioneSegnalazioneDaRisolvere == MotivazioneBean.OFFTOPIC) {
					
					if(segnalazioneDomandaBean.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
						segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
						SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					}
				
				} else {
					
					segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
					SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					
				}
				
				/*
				if(idMotivazioneSegnalazione == MotivazioneBean.CONTENUTI_OFFENSIVI) {
					
					segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
					SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					
				} else if(idMotivazioneSegnalazione == MotivazioneBean.OFFTOPIC) {
					
					if(segnalazioneDomandaBean.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
						segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
						SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					}
					
				}
				*/
				
			}
			
		}
		
		//  ---
	
		/* 
		 * Se l'id della motivazione corrisponde a "Contenuti offensivi", la domanda segnalata viene eliminata e
		 * tutte le altre segnalazioni con id "Contenuti offensivi" alla stessa domanda vengono approvate.
		 */
		/*
		if(segnalazione.getMotivazione().getId() == MotivazioneBean.CONTENUTI_OFFENSIVI) {
			
			for (SegnalazioneDomandaBean segnalazioneDomandaBean : segnalazioni) {
				
				if(segnalazioneDomandaBean.getDomandaSegnalata().getId() == segnalazione.getDomandaSegnalata().getId()) {
					segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
					SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
				}
				
			}
			
		}
		*/
		/* 
		 * Se l'id della motivazione corrisponde a "Off-topic", le categorie della domanda segnalata vengono
		 *  aggiornate e tutte le altre segnalazioni con id "Off-topic" alla stessa domanda vengono approvate.
		 */
		/*
		if(segnalazione.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
			
			System.out.println("Segnalazione Offtopic");
			
			for (SegnalazioneDomandaBean segnalazioneDomandaBean : segnalazioni) {
				
				System.out.println("foreach");
				
				if(segnalazioneDomandaBean.getDomandaSegnalata().getId() == segnalazione.getDomandaSegnalata().getId() 
						&& segnalazioneDomandaBean.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
					
					System.out.println("Trovata segnalazione offtopic alla stessa domanda");
					
					if(segnalazioneDomandaBean.getId() == segnalazione.getId()) {
						System.out.println("Trovata segnalazione");
					}
					
					segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
					SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					
				}
				
			}
			
		}
		*/
		
	}
	
	public void declinaSegnalazioneDomanda(SegnalazioneDomandaBean segnalazione) {
		segnalazione.setStato(SegnalazioneBean.DECLINATA);
		SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazione);
	}
	
	public void risolviSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		segnalazione.setStato(SegnalazioneBean.APPROVATA);
		SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(segnalazione);
	}
	
	public void declinaSegnalazioneRisposta(SegnalazioneRispostaBean segnalazione) {
		segnalazione.setStato(SegnalazioneBean.DECLINATA);
		SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(segnalazione);
	}
	
	public int getNumeroSegnalazioniDomanda() {
		return SegnalazioneDomandaDAO.getNumeroSegnalazioniDomanda();
	}
}
