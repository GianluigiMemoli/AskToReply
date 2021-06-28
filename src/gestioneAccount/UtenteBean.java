package gestioneAccount;

public class UtenteBean {
		
	private String email;
	private String passwordHash;
	private String username;
	private String nome;
	private String cognome;
	private int ruoloID;
	private boolean isDisattivato;
	
	
	
	public UtenteBean(String email, String passwordHash, String username, String nome,
			String cognome, int ruoloID, boolean isDisattivato, String id) {
		super();
		this.email = email;
		this.passwordHash = passwordHash;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.ruoloID = ruoloID;
		this.isDisattivato = isDisattivato;
		this.id = id;
	}
	
	public UtenteBean() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getRuoloID() {
		return ruoloID;
	}
	public void setRuoloID(int ruoloID) {
		this.ruoloID = ruoloID;
	}
	public boolean isDisattivato() {
		return isDisattivato;
	}
	public void setDisattivato(boolean isDisattivato) {
		this.isDisattivato = isDisattivato;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "UtenteBean [email=" + email + ", passwordHash=" + passwordHash 
				+ ", username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", ruoloID=" + ruoloID
				+ ", isDisattivato=" + isDisattivato + ", id=" + id + "]";
	}


	private String id; 
}
