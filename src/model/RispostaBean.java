package model;

public class RispostaBean {
		
	public RispostaBean(String id, String idDomanda, String corpo, String allegati, String idAutore) {
		super();
		this.id = id;
		this.idDomanda = idDomanda;
		this.corpo = corpo;
		this.allegati = allegati;
		this.idAutore = idAutore;
	}
		
	public RispostaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(String idDomanda) {
		this.idDomanda = idDomanda;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getAllegati() {
		return allegati;
	}
	public void setAllegati(String allegati) {
		this.allegati = allegati;
	}
	public String getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(String idAutore) {
		this.idAutore = idAutore;
	}
	@Override
	public String toString() {
		return "RispostaBean [id=" + id + ", idDomanda=" + idDomanda + ", corpo=" + corpo + ", allegati=" + allegati
				+ ", idAutore=" + idAutore + "]";
	}
	
	private String id; 
	private String idDomanda;
	private String corpo;
	private String allegati;
	private String idAutore;
}
