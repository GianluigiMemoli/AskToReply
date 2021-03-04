package model;

import java.util.Date;

public class SegnalazioneRispostaBean {
	
	
	
	public SegnalazioneRispostaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public SegnalazioneRispostaBean(RispostaBean rispostaSegnalata, MotivazioneBean motivazione, Date dataSegnalazione,
			int stato, String commento, String idSegnalazione, DomandaBean domanda) {
		super();
		this.rispostaSegnalata = rispostaSegnalata;
		this.motivazione = motivazione;
		this.dataSegnalazione = dataSegnalazione;
		this.stato = stato;
		this.commento = commento;
		this.idSegnalazione = idSegnalazione;
		this.domanda = domanda;
	}

	
	
	public String getIdSegnalazione() {
		return idSegnalazione;
	}

	public void setIdSegnalazione(String idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
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

	public RispostaBean getRispostaSegnalata() {
		return rispostaSegnalata;
	}

	public void setRispostaSegnalata(RispostaBean rispostaSegnalata) {
		this.rispostaSegnalata = rispostaSegnalata;
	}

	public DomandaBean getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaBean domanda) {
		this.domanda = domanda;
	}

	public MotivazioneBean getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(MotivazioneBean motivazione) {
		this.motivazione = motivazione;
	}

	
	
	@Override
	public String toString() {
		return "SegnalazioneRispostaBean [rispostaSegnalata=" + rispostaSegnalata + ", motivazione=" + motivazione
				+ ", dataSegnalazione=" + dataSegnalazione + ", stato=" + stato + ", commento=" + commento
				+ ", idSegnalazione=" + idSegnalazione + ", domanda=" + domanda + "]";
	}

	
	
	private RispostaBean rispostaSegnalata; 
	private MotivazioneBean motivazione;
	private DomandaBean domanda;	
	private String idSegnalazione;
	private Date dataSegnalazione;
	private String commento;
	private int stato;
	
	
	
}
