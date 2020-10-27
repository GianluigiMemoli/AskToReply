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
	
	@Override
	public String toString() {
		String s = "Segnalazione[";
		
		s += "id = " + id + ", ";
		s += "idMotivazione = " + motivazione + ", ";
		s += "dataSegnalazione = " + dataSegnalazione + ", ";
		s += "stato = " + stato + ", ";
		s += "commento = " + commento + "]";
		
		return s;
	}
	
	private String id;
	private MotivazioneBean motivazione;
	private Date dataSegnalazione;
	private int stato;
	private String commento;
}
