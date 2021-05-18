package model;

import java.util.ArrayList;
import java.util.Date;

public class SegnalazioniManager {

	public void creazioneSegnalazioneDomanda(
			MotivazioneBean motivazione, 
			Date dataSegnalazione,
			String commento,
			DomandaBean domandaSegnalata) {
		
		SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
		
		segnalazione.setMotivazione(motivazione);
		segnalazione.setDataSegnalazione(dataSegnalazione);
		segnalazione.setStato(SegnalazioneBean.DA_GESTIRE);
		segnalazione.setCommento(commento);
		segnalazione.setDomandaSegnalata(domandaSegnalata);
		
		SegnalazioneDomandaDAO.addSegnalazioneDomanda(segnalazione);
		
	}
	
	public ArrayList<SegnalazioneDomandaBean> getSegnalazioniDomanda(int start, int end) {
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = SegnalazioneDomandaDAO.getSegnalazioniDomanda(start, end);
		
		DomandeManager managerDomande = new DomandeManager();
		
		for (SegnalazioneDomandaBean segnalazione : segnalazioni) {
			
			String idDomandaSegnalata = segnalazione.getDomandaSegnalata().getId();
			DomandaBean domandaSegnalata = managerDomande.getDomandaById(idDomandaSegnalata);	
			segnalazione.setDomandaSegnalata(domandaSegnalata);
				
			if(segnalazione.getMotivazione().getId() == MotivazioneBean.OFFTOPIC 
					&& domandaSegnalata != null) {
				domandaSegnalata.setCategorie(CategoriaDAO.getCategorieDomandaByIdDomanda(domandaSegnalata.getId()));
			}
			
		}
		
		return segnalazioni;
		
	}
	
	public ArrayList<SegnalazioneDomandaBean> getAllSegnalazioniDomanda() {
		return SegnalazioneDomandaDAO.getAll();
	}
	
	public ArrayList<SegnalazioneRispostaBean> getAllSegnalazioniRisposta() {
		// TODO
		return SegnalazioneRispostaDAO.getElencoSegnalazioniRisposte();
	}

	public SegnalazioneDomandaBean getSegnalazioneDomanda(String id) {
		return SegnalazioneDomandaDAO.getSegnalazioneDomandaById(id);
	}

	public SegnalazioneRispostaBean getSegnalazioneRisposta(String id) {
		// TODO
		return SegnalazioneRispostaDAO.getSegnalazioneRispostaById(id);
	}

	/*
	 * TODO Possibile refactoring: Fare una classe SegnalazioneDAO in modo da mettere al suo interno 
	 * updateStatoSegnalazione() ed usare quest'ultimo metodo invece di avere un metodo risolvi
	 * e declina per domanda e risposta.
	 */
	
	public void risolviSegnalazioneDomanda(SegnalazioneDomandaBean segnalazioneDaRisolvere) {
		
		/*
		 *  TODO: Per migliorare le perfomance si potrebbero prelevare dal DB solo le segnalazioni ad un certa domanda
		 *  piuttosto che tutte.
		 */
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = getAllSegnalazioniDomanda();
		
		String idDomandaSegnalata = segnalazioneDaRisolvere.getDomandaSegnalata().getId();
		int idMotivazioneSegnalazione = segnalazioneDaRisolvere.getMotivazione().getId();
		
		for (SegnalazioneDomandaBean segnalazioneDomandaBean : segnalazioni) {
					
			if(segnalazioneDomandaBean.getDomandaSegnalata().getId().equals(idDomandaSegnalata)) {
				
				if(idMotivazioneSegnalazione == MotivazioneBean.CONTENUTI_OFFENSIVI) {
					
					segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
					SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					
				} else if(idMotivazioneSegnalazione == MotivazioneBean.OFFTOPIC) {
					
					if(segnalazioneDomandaBean.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
						segnalazioneDomandaBean.setStato(SegnalazioneBean.APPROVATA);
						SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazioneDomandaBean);
					}
					
				}
				
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
