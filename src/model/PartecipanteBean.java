package model;

import java.util.ArrayList;

public class PartecipanteBean extends UtenteBean{
	
	public PartecipanteBean() {
		super();
	}
	
	public PartecipanteBean(String email, String passwordHash, String username, String nome,
			String cognome, int ruoloID, boolean isDisattivato, String id, int numeroSegnalazioni, ArrayList<CategoriaBean> interessi) {
		super(email, passwordHash,  username, nome, cognome, ruoloID, isDisattivato, id);
		this.numeroSegnalazioni = numeroSegnalazioni;
		this.interessi = interessi;
	}
	

	public int getNumeroSegnalazioni() {
		return numeroSegnalazioni;
	}
	public void setNumeroSegnalazioni(int numeroSegnalazioni) {
		this.numeroSegnalazioni = numeroSegnalazioni;
	}
	
	
	


	public ArrayList<CategoriaBean> getInteressi() {
		return interessi;
	}

	public void setInteressi(ArrayList<CategoriaBean> interessi) {
		this.interessi = interessi;
	}





	private ArrayList<CategoriaBean> interessi;
	private int numeroSegnalazioni; 
}
