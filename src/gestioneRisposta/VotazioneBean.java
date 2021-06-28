package gestioneRisposta;

import gestioneAccount.PartecipanteBean;

public class VotazioneBean {
	
	public VotazioneBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VotazioneBean(PartecipanteBean utente, RispostaBean risposta, int valore) {
		super();
		this.utente = utente;
		this.risposta = risposta;
		this.valore = valore;
	}

	public PartecipanteBean getUtente() {
		return utente;
	}

	public void setUtente(PartecipanteBean utente) {
		this.utente = utente;
	}

	public RispostaBean getRisposta() {
		return risposta;
	}

	public void setRisposta(RispostaBean risposta) {
		this.risposta = risposta;
	}

	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}
	
	private PartecipanteBean utente;
	private RispostaBean risposta;
	private int valore;
	
}
