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
	
	@Override
	public String toString() {
		return "Domanda [id = " + id+ ", idAutore = " + autore.getId() + ", titolo = " + titolo + ", corpo = " + corpo + ", dataPubblicazione = " + dataPubblicazione + ", isArchiviata = " + isArchiviata + "]";
	}
	
	//
	
	private String id;
	private UtenteBean autore;
	private String titolo;
	private String corpo;
	private Date dataPubblicazione;
	private boolean isArchiviata;
	private ArrayList<CategoriaBean> categorie;
	 
}
