package model;

public class PartecipanteBean extends UtenteBean{
	public PartecipanteBean(String email, String passwordHash, String nuovaPassword, String username, String nome,
			String cognome, int ruoloID, boolean isDisattivato, String id, int punteggio, int numeroSegnalazioni) {
		super(email, passwordHash, nuovaPassword, username, nome, cognome, ruoloID, isDisattivato, id);
		this.punteggio = punteggio;
		this.numeroSegnalazioni = numeroSegnalazioni;
	}
	
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	public int getNumeroSegnalazioni() {
		return numeroSegnalazioni;
	}
	public void setNumeroSegnalazioni(int numeroSegnalazioni) {
		this.numeroSegnalazioni = numeroSegnalazioni;
	}
	
	
	
	@Override
	public String toString() {
		return super.toString() + "\nPartecipanteBean [punteggio=" + punteggio + ", numeroSegnalazioni=" + numeroSegnalazioni + "]";
	}



	int punteggio;
	int numeroSegnalazioni; 
}
