package model;

public class SegnalazioneRispostaBean {
	
	public SegnalazioneRispostaBean(String idSegnalazione, String idRisposta, String idMotivazione,
			String dataSegnalazione, String stato, String commento) {
		super();
		this.idSegnalazione = idSegnalazione;
		this.idMotivazione = idMotivazione;
		this.dataSegnalazione = dataSegnalazione;
		this.stato = stato;
		this.commento = commento;
		this.idRisposta = idRisposta;
	}

	public SegnalazioneRispostaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getIdSegnalazione() {
		return idSegnalazione;
	}
	public void setIdSegnalazione(String idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}
	public String getIdRisposta() {
		return idRisposta;
	}
	public void setIdRisposta(String idRisposta) {
		this.idRisposta = idRisposta;
	}
	public String getIdMotivazione() {
		return idMotivazione;
	}
	public void setIdMotivazione(String idMotivazione) {
		this.idMotivazione = idMotivazione;
	}
	public String getDataSegnalazione() {
		return dataSegnalazione;
	}
	public void setDataSegnalazione(String dataSegnalazione) {
		this.dataSegnalazione = dataSegnalazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
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
		return "SegnalazioneRispostaBean [idSegnalazione=" + idSegnalazione + ", idMotivazione=" + idMotivazione
				+ ", dataSegnalazione=" + dataSegnalazione + ", stato=" + stato + ", commento=" + commento
				+ ", idRisposta=" + idRisposta + "]";
	}
	
	private String idSegnalazione; 
	private String idMotivazione;
	private String dataSegnalazione;
	private String stato;
	private String commento;
	private String idRisposta; 

	
}