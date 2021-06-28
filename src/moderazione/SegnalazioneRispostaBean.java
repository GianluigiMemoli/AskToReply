package moderazione;

import java.util.Date;

import gestioneRisposta.RispostaBean;

public class SegnalazioneRispostaBean extends SegnalazioneBean{
	
	public SegnalazioneRispostaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SegnalazioneRispostaBean(RispostaBean rispostaSegnalata, MotivazioneBean motivazione, Date dataSegnalazione,
			int stato, String commento, String idSegnalazione) {
		super();
		this.rispostaSegnalata = rispostaSegnalata;
		this.motivazione = motivazione;
		this.dataSegnalazione = dataSegnalazione;
		this.stato = stato;
		this.commento = commento;
		this.idSegnalazione = idSegnalazione;
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
				+ ", idSegnalazione=" + idSegnalazione + "]";
	}

	
	
	private RispostaBean rispostaSegnalata; 
	private MotivazioneBean motivazione;
	private String idSegnalazione;
	private Date dataSegnalazione;
	private String commento;
	private int stato;
	
	
	
}
