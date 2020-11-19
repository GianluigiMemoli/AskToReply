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
	
	public void risolviSegnalazioneDomanda(SegnalazioneDomandaBean segnalazione) {
		segnalazione.setStato(SegnalazioneBean.APPROVATA);
		SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(segnalazione);
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
	
	
}
