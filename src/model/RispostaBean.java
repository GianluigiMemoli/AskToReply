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

	public RispostaBean(String id, String idDomanda, String corpo, String idAutore, Date dataPubblicazione) {
		super();
		this.id = id;
		this.idDomanda = idDomanda;
		this.corpo = corpo;
		// this.allegati = string;
		this.idAutore = idAutore;
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

	public String getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(String idAutore) {
		this.idAutore = idAutore;
	}

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



	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
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









	@Override
	public String toString() {
		return "RispostaBean [id=" + id + ", idDomanda=" + idDomanda + ", corpo=" + corpo + ", dataPubblicazione="
				+ dataPubblicazione + ", idAutore=" + idAutore + ", titoloDomanda=" + titoloDomanda + ", autore="
				+ autore + ", miPiace=" + miPiace + ", nonMiPiace=" + nonMiPiace + ", voti=" + voti + "]";
	}









	private String id;
	private String idDomanda;
	private String corpo;
	private Date dataPubblicazione;
	// private List<Part> allegati;
	private String idAutore;
	private String titoloDomanda;// 151220
	private String autore;
	private int miPiace;
	private int nonMiPiace;
	private ArrayList <VotazioneBean> voti; //aggiunto
}