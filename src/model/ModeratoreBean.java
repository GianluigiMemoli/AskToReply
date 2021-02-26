package model;

public class ModeratoreBean extends UtenteBean{
	
	public ModeratoreBean(
			String email, 
			String passwordHash,
			String nuovaPassword, 
			String username, 
			String nome,
			String cognome, 
			int ruoloID, 
			boolean isDisattivato, 
			String id) {
		super(email, passwordHash, nuovaPassword, username, nome, cognome, ruoloID, isDisattivato, id);
		
	}
	


}
