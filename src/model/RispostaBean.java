package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

@SuppressWarnings("unused")
public class RispostaBean {

	public RispostaBean() {
		super();
	}

	public RispostaBean(String id, String idDomanda, String corpo,PartecipanteBean autore, Date dataPubblicazione) {
		super();
		this.id = id;
		this.idDomanda = idDomanda;
		this.corpo = corpo;
		this.autore = autore;
		// this.allegati = string;
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdDomanda() {
		return idDomanda;
	}

	public void setIdDomanda(String idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	/*
	 * public List<Part> getAllegati() { return allegati; } public void
	 * setAllegati(List<Part> allegati) { this.allegati = allegati; }
	 */


	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public void setTitoloDomanda(String titolo) { // 151220
		this.titoloDomanda = titolo;
	}

	public String getTitoloDomanda() { // 151220
		return titoloDomanda;
	}




	public int getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(int miPiace) {
		this.miPiace = miPiace;
	}

	public int getNonMiPiace() {
		return nonMiPiace;
	}

	public void setNonMiPiace(int nonMiPiace) {
		this.nonMiPiace = nonMiPiace;
	}



	public ArrayList<VotazioneBean> getVoti() {
		return voti;
	}

	public void setVoti(ArrayList<VotazioneBean> voti) {
		this.voti = voti;
	}
	

	public ArrayList<String> getAllegati() {
		return allegati;
	}

	public void setAllegati(ArrayList<String> allegati) {
		this.allegati = allegati;
	}


	
	
	public PartecipanteBean getAutore() {
		return autore;
	}

	public void setAutore(PartecipanteBean autore) {
		this.autore = autore;
	}




	private String id;
	private String idDomanda;
	private String corpo;
	private Date dataPubblicazione;
	// private List<Part> allegati;
	private PartecipanteBean autore;
	private String titoloDomanda;// 151220
	private int miPiace;
	private int nonMiPiace;
	private ArrayList <VotazioneBean> voti; //aggiunto
	private ArrayList<String> allegati;
}