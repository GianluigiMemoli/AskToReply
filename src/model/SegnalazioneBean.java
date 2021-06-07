package model;

import java.util.Date;

public abstract class SegnalazioneBean {

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public MotivazioneBean getMotivazione() {
		return motivazione;
	}
	
	public void setMotivazione(MotivazioneBean motivazione) {
		this.motivazione = motivazione;
	}
	public Date getDataSegnalazione() {
		return dataSegnalazione;
	}
	
	public void setDataSegnalazione(Date dataSegnalazione) {
		this.dataSegnalazione = dataSegnalazione;
	}
	
	public int getStato() {
		return stato;
	}
	
	public void setStato(int stato) {
		this.stato = stato;
	}
	
	public String getCommento() {
		return commento;
	}
	
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public PartecipanteBean getUtente() {
		return utente;
	}

	public void setUtente(PartecipanteBean utente) {
		this.utente = utente;
	}

	

	@Override
	public String toString() {
		return "SegnalazioneBean [id=" + id + ", motivazione=" + motivazione + ", dataSegnalazione=" + dataSegnalazione
				+ ", stato=" + stato + ", commento=" + commento + ", utente=" + utente + "]";
	}


	private String id;
	private MotivazioneBean motivazione;
	private Date dataSegnalazione;
	private int stato;
	private String commento;
	private PartecipanteBean utente;
	
	public static final int DA_GESTIRE = 1;
	public static final int APPROVATA = 2;
	public static final int DECLINATA = 3;
}
