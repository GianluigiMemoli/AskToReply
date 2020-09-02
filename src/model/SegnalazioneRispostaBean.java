package model;

public class SegnalazioneRispostaBean {
	
	/* Bisogna aggiungere i dati contenuti in 'Segnalazioni' (nel database)...credo */

	public SegnalazioneRispostaBean(String idSegnalazione, String idRisposta) {
		super();
		this.idSegnalazione = idSegnalazione;
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
	
	@Override
	public String toString() {
		return "SegnalazioneRispostaBean [idSegnalazione=" + idSegnalazione + ", idRisposta=" + idRisposta + "]";
	}
	
	private String idSegnalazione; 
	private String idRisposta; 
	
}