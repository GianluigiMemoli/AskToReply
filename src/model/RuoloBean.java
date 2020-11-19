package model;

public class RuoloBean {
	public static final String ROLE_PARTECIPANTE = "Partecipante";
	public static final String ROLE_MODERATORE = "Moderatore	";
	public static final String ROLE_MASTER_MODERATORE = "MasterModeratore";
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private String nome; 
	private int id; 
}
