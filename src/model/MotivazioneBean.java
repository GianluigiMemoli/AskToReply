package model;

public class MotivazioneBean {

	public MotivazioneBean() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private int id;
	private String nome;
	
	public static int CONTENUTI_OFFENSIVI = 1;
	public static int OFFTOPIC = 2;
	
}
