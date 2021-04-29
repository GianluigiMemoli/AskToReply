package model;

public class PartecipanteBean extends UtenteBean{
	
	public PartecipanteBean() {
		super();
	}
	
	public PartecipanteBean(String email, String passwordHash, String username, String nome,
			String cognome, int ruoloID, boolean isDisattivato, String id, int numeroSegnalazioni) {
		super(email, passwordHash,  username, nome, cognome, ruoloID, isDisattivato, id);
		this.numeroSegnalazioni = numeroSegnalazioni;
	}
	

	public int getNumeroSegnalazioni() {
		return numeroSegnalazioni;
	}
	public void setNumeroSegnalazioni(int numeroSegnalazioni) {
		this.numeroSegnalazioni = numeroSegnalazioni;
	}
	
	
	



	int numeroSegnalazioni; 
}
