package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

public class RispostaBean {

	public RispostaBean() {
		super();
	}

	public RispostaBean(String id, DomandaBean domanda, String corpo,PartecipanteBean autore, Date dataPubblicazione) {
		super();
		this.id = id;
		this.domanda = domanda;
		this.corpo = corpo;
		this.autore = autore;
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public int getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(int miPiace) {
		this.miPiace = miPiace;
	}

	public int getNonMiPiace() {
		return nonMiPiace;
	}

	public void setNonMiPiace(int nonMiPiace) {
		this.nonMiPiace = nonMiPiace;
	}

	public ArrayList<VotazioneBean> getVoti() {
		return voti;
	}

	public void setVoti(ArrayList<VotazioneBean> voti) {
		this.voti = voti;
	}
	

	public ArrayList<String> getAllegati() {
		return allegati;
	}

	public void setAllegati(ArrayList<String> allegati) {
		this.allegati = allegati;
	}
	
	public PartecipanteBean getAutore() {
		return autore;
	}

	public void setAutore(PartecipanteBean autore) {
		this.autore = autore;
	}
	
	
	public DomandaBean getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaBean domanda) {
		this.domanda = domanda;
	}

	

	@Override
	public String toString() {
		return "RispostaBean [id=" + id + ", corpo=" + corpo + ", dataPubblicazione=" + dataPubblicazione + ", autore="
				+ autore + ", miPiace=" + miPiace + ", nonMiPiace=" + nonMiPiace + ", voti=" + voti + ", allegati="
				+ allegati + ", domanda=" + domanda + "]";
	}


	private String id;
	private String corpo;
	private Date dataPubblicazione;
	private PartecipanteBean autore;
	private int miPiace;
	private int nonMiPiace;
	private ArrayList <VotazioneBean> voti;
	private ArrayList<String> allegati;
	private DomandaBean domanda;
}