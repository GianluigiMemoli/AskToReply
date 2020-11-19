package model;

public class MasterModeratoreBean extends UtenteBean{
	
	public MasterModeratoreBean(
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
