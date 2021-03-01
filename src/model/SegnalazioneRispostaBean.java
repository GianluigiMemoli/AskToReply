package model;

import java.util.Date;

public class SegnalazioneRispostaBean {
	
	public SegnalazioneRispostaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SegnalazioneRispostaBean(String idSegnalazione, int idMotivazione, String motivazione,
			Date dataSegnalazione, int stato, String commento, String idRisposta, String corpoRisposta,
			String filepathRisposta, RispostaBean rispostaSegnalata, String titoloDomanda, String corpoDomanda) {
		super();
		this.idSegnalazione = idSegnalazione;
		this.idMotivazione = idMotivazione;
		this.motivazione = motivazione;
		this.dataSegnalazione = dataSegnalazione;
		this.stato = stato;
		this.commento = commento;
		this.idRisposta = idRisposta;
		this.corpoRisposta = corpoRisposta;
		this.filepathRisposta = filepathRisposta;
		this.rispostaSegnalata = rispostaSegnalata;
		this.titoloDomanda = titoloDomanda;
		this.corpoDomanda = corpoDomanda;
	}

	
	
	
	public String getTitoloDomanda() {
		return titoloDomanda;
	}



	public void setTitoloDomanda(String titoloDomanda) {
		this.titoloDomanda = titoloDomanda;
	}



	public String getCorpoDomanda() {
		return corpoDomanda;
	}



	public void setCorpoDomanda(String corpoDomanda) {
		this.corpoDomanda = corpoDomanda;
	}



	public RispostaBean getRispostaSegnalata() {
		return rispostaSegnalata;
	}

	public void setRispostaSegnalata(RispostaBean rispostaSegnalata) {
		this.rispostaSegnalata = rispostaSegnalata;
	}
	
	public String getIdSegnalazione() {
		return idSegnalazione;
	}



	public int getIdMotivazione() {
		return idMotivazione;
	}

	public void setIdMotivazione(int idMotivazione) {
		this.idMotivazione = idMotivazione;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
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

	public String getIdRisposta() {
		return idRisposta;
	}

	public void setIdRisposta(String idRisposta) {
		this.idRisposta = idRisposta;
	}

	public String getCorpoRisposta() {
		return corpoRisposta;
	}

	public void setCorpoRisposta(String corpoRisposta) {
		this.corpoRisposta = corpoRisposta;
	}

	public String getFilepathRisposta() {
		return filepathRisposta;
	}

	public void setFilepathRisposta(String filepathRisposta) {
		this.filepathRisposta = filepathRisposta;
	}

	public void setIdSegnalazione(String idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}



	public RispostaBean getRisposta() {
		return risposta;
	}


	public void setRisposta(RispostaBean risposta) {
		this.risposta = risposta;
	}


	@Override
	public String toString() {
		return "SegnalazioneRispostaBean [titoloDomanda=" + titoloDomanda + ", corpoDomanda=" + corpoDomanda
				+ ", idSegnalazione=" + idSegnalazione + ", idMotivazione=" + idMotivazione + ", motivazione="
				+ motivazione + ", dataSegnalazione=" + dataSegnalazione + ", stato=" + stato + ", commento=" + commento
				+ ", idRisposta=" + idRisposta + ", corpoRisposta=" + corpoRisposta + ", filepathRisposta="
				+ filepathRisposta + ", rispostaSegnalata=" + rispostaSegnalata + "]";
	}



	private RispostaBean risposta;
	private String titoloDomanda;
	private String corpoDomanda;
	private String idSegnalazione; 
	private int idMotivazione;
	private String motivazione;
	private Date dataSegnalazione;
	private int stato;
	private String commento;
	private String idRisposta;
	private String corpoRisposta;
	private String filepathRisposta;
	private RispostaBean rispostaSegnalata; 
}


