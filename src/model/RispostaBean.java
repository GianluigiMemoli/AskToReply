package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

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

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public void setTitoloDomanda(String titolo) {
		this.titoloDomanda = titolo;
	}

	public String getTitoloDomanda() {
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

	@Override
	public String toString() {
		return "RispostaBean [id=" + id + ", idDomanda=" + idDomanda + ", corpo=" + corpo + ", dataPubblicazione="
				+ dataPubblicazione + ", autore=" + autore + ", titoloDomanda=" + titoloDomanda + ", miPiace=" + miPiace
				+ ", nonMiPiace=" + nonMiPiace + ", voti=" + voti + ", allegati=" + allegati + "]";
	}

	private String id;
	private String idDomanda;
	private String corpo;
	private Date dataPubblicazione;
	private PartecipanteBean autore;
	private String titoloDomanda;
	private int miPiace;
	private int nonMiPiace;
	private ArrayList <VotazioneBean> voti;
	private ArrayList<String> allegati;
}