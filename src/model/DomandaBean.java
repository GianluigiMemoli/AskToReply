package model;

import java.util.Date;
import java.util.ArrayList;

public class DomandaBean {
	

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public UtenteBean getAutore() {
		return autore;
	}
	
	public void setAutore(UtenteBean autore) {
		this.autore = autore;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public boolean isArchiviata() {
		return isArchiviata;
	}
	
	public void setArchiviata(boolean isArchiviata) {
		this.isArchiviata = isArchiviata;
	}
	
	public ArrayList<CategoriaBean> getCategorie() {
		return categorie;
	}
	public void setCategorie(ArrayList<CategoriaBean> categorie) {
		this.categorie = categorie;
	}
		
	public ArrayList<RispostaBean> getRisposte() {
		return risposte;
	}

	public void setRisposte(ArrayList<RispostaBean> risposte) {
		this.risposte = risposte;
	}
	
	public ArrayList<String> getAllegati() {
		return allegati;
	}

	public void setAllegati(ArrayList<String> allegati) {
		this.allegati = allegati;
	}
	
	@Override
	public String toString() {
		return "Domanda [id = " + id+ ", idAutore = " + autore.getId() + ", titolo = " + titolo + ", corpo = " + corpo + ", dataPubblicazione = " + dataPubblicazione + ", isArchiviata = " + isArchiviata + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		DomandaBean toCompare = (DomandaBean) obj;
		return toCompare.getId().equals(this.id);
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();  
        return result;
    }

	private String id;
	private UtenteBean autore;
	private String titolo;
	private String corpo;
	private Date dataPubblicazione;
	private boolean isArchiviata;
	private ArrayList<CategoriaBean> categorie;
	private ArrayList<RispostaBean> risposte;
	private ArrayList<String> allegati;
}
