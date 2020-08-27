package model;

public class VotazioneBean {
	
	public VotazioneBean(String idUtente, String idRisposta, int valore) {
		super();
		this.idUtente = idUtente;
		this.idRisposta = idRisposta;
		this.valore = valore;
	}
	
	public VotazioneBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}
	public String getIdRisposta() {
		return idRisposta;
	}
	public void setIdRisposta(String idRisposta) {
		this.idRisposta = idRisposta;
	}
	public int getValore() {
		return valore;
	}
	public void setValore(int valore) {
		this.valore = valore;
	}
	
	@Override
	public String toString() {
		return "SegnalazioneRispostaBean [idUtente=" + idUtente + ", idRisposta=" + idRisposta + ", valore=" + valore
				+ "]";
	}
	
	private String idUtente;
	private String idRisposta;
	private int valore;
	
}
