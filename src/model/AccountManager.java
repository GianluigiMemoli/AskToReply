package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import Exceptions.CampiNonConformiException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;

public class AccountManager {
	static Logger log = Logger.getLogger(AccountManager.class.getName());
	
	public void RegisterUser(
			String nome,
			String cognome, 
			String username, 
			String email, 
			String password,
			String[] interessi) throws CampiNonConformiException, NoSuchAlgorithmException, EmailPresenteException, UsernamePresenteException {
		
		if(!Validator.validateRegistationFields(nome, cognome, email, username, password)) {
			throw new CampiNonConformiException();
		}						
		
		String passwordHash = this.getPasswordHash(password);
		UtenteBean newUser = new UtenteBean();
		newUser.setNome(nome);
		newUser.setCognome(cognome);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPasswordHash(passwordHash);
		
		if(!this.isEmailAvailable(newUser.getEmail()))
			throw new EmailPresenteException();
		
		if(!this.isUsernameAvailable(newUser.getUsername()))
			throw new UsernamePresenteException();
		
		UtenteDAO.doAddUtente(newUser);
		
		PartecipanteBean registeredPartecipante = PartecipanteDAO.getPartecipanteByEmail(newUser.getEmail()); 
		for (String interesse : interessi) {
			CategoriaBean categoria = new CategoriaBean();
			categoria.setNome(interesse);
			
			this.addInteressePartecipante(registeredPartecipante, categoria);
		}
	}
	
	public void addInteressePartecipante(PartecipanteBean partecipante, CategoriaBean categoria) throws CampiNonConformiException {
		log.info(categoria.getNome());
		if(CategoriaDAO.getCategoriaByNome(categoria.getNome()) == null) {
			PartecipanteDAO.removePartecipanteById(partecipante);
			throw new CampiNonConformiException();
		}
		PartecipanteDAO.addInteresse(partecipante, categoria);		
	}
	
	private String getPasswordHash(String password) throws NoSuchAlgorithmException{
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int ii = 0; ii < hashedPassword.length; ii++) {
			String hex = Integer.toHexString(0xff & hashedPassword[ii]);
            if(hex.length() == 1) 
            	hexString.append('0');
            
            hexString.append(hex);
		}
		return hexString.toString();					
	}
	
	private static boolean isEmailAvailable(String email) {
		return UtenteDAO.getUtenteByEmail(email) == null;				
	}
	
	private static boolean isUsernameAvailable(String username) {
		return UtenteDAO.getUtenteByUsername(username) == null;				
	}
	
	
	
}
