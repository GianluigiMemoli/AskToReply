package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Logger;

import Exceptions.CampiNonConformiException;
import Exceptions.CredenzialiNonValideException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;


public class AccountManager {
	static Logger log = Logger.getLogger(AccountManager.class.getName());
	
	public void RegisterUser(String nome,String cognome, String username, String email, String password, String[] interessi) throws CampiNonConformiException, NoSuchAlgorithmException, EmailPresenteException, UsernamePresenteException {
		try {
			UtenteBean newUser = generateUtenteBean(nome, cognome, username, email, password);
			
			UtenteDAO.doAddUtente(newUser);
			
			PartecipanteBean registeredPartecipante = PartecipanteDAO.getPartecipanteByEmail(newUser.getEmail()); 
			for (String interesse : interessi) {
				CategoriaBean categoria = new CategoriaBean();
				categoria.setNome(interesse);
				
				this.addInteressePartecipante(registeredPartecipante, categoria);
			}
		} catch(EmailPresenteException exc) {
			throw exc;
		}
		 catch(UsernamePresenteException exc) {
				throw exc;
			}
		catch(Exception exc) {
			//Se scaturisce un errore cancella tutto quello che � stato creato riguardo l'utente
			PartecipanteBean registeredPartecipante = PartecipanteDAO.getPartecipanteByEmail(email);
			if (registeredPartecipante != null)
					PartecipanteDAO.removePartecipanteById(registeredPartecipante);
			throw exc;
		}
	}
	
	public void RegistraModeratore(String email, String password, String username, String nome, String cognome) throws CampiNonConformiException, EmailPresenteException, UsernamePresenteException, NoSuchAlgorithmException, SQLException {				
		UtenteBean newUser =   generateUtenteBean(nome, cognome, username, email, password);		
		ModeratoreDAO.doAddModeratore(newUser);		
	}
	
	public UtenteBean autenticaUtente(String email, String password) throws CredenzialiNonValideException {		
		UtenteBean loggingUser = UtenteDAO.getUtenteByEmail(email);
		if(loggingUser == null) {
			throw new CredenzialiNonValideException();
		}
		try {
			String hashedPassword = this.getPasswordHash(password);
			if(loggingUser.getPasswordHash().equals(hashedPassword)) {
				return userFactory(loggingUser.getRuoloID(), loggingUser.getEmail());
				
			} else {
				throw new CredenzialiNonValideException();
			}
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public void addInteressePartecipante(PartecipanteBean partecipante, CategoriaBean categoria) throws CampiNonConformiException {
		log.info(categoria.getNome());
		if(CategoriaDAO.getCategoriaByNome(categoria.getNome()) == null) {			
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
	
	private UtenteBean generateUtenteBean(String nome,String cognome, String username, String email, String password) throws CampiNonConformiException, EmailPresenteException, UsernamePresenteException, NoSuchAlgorithmException {		
		UtenteBean newUser;	
		if(!Validator.validateRegistationFields(nome, cognome, email, username, password)) {
			throw new CampiNonConformiException();
		}				
				
		String passwordHash = this.getPasswordHash(password);
		newUser = new UtenteBean();
		newUser.setNome(nome);
		newUser.setCognome(cognome);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPasswordHash(passwordHash);
		
		if(!isEmailAvailable(newUser.getEmail()))
			throw new EmailPresenteException();
		
		if(!isUsernameAvailable(newUser.getUsername()))
			throw new UsernamePresenteException();						
			
			return newUser;
		}
	
	
	private UtenteBean userFactory(int roleId, String email) {
		RuoloBean role = RuoloDAO.getRuoloById(roleId);
		UtenteBean specializedUser = null; 
		
		if (role.getNome().equals(RuoloBean.ROLE_PARTECIPANTE)) {
			specializedUser = PartecipanteDAO.getPartecipanteByEmail(email);
		} else {
			UtenteBean user = UtenteDAO.getUtenteByEmail(email);
			if (role.getNome().equals(RuoloBean.ROLE_MODERATORE)) {
			specializedUser = new ModeratoreBean(email, user.getPasswordHash(),
					user.getNuovaPassword(), 
					user.getUsername(),
					user.getNome(),
					user.getCognome(),
					roleId,
					user.isDisattivato(),
					user.getId());
			} else if (role.getNome().equals(RuoloBean.ROLE_MASTER_MODERATORE)) {
				specializedUser = new MasterModeratoreBean(email, user.getPasswordHash(),
						user.getNuovaPassword(), 
						user.getUsername(),
						user.getNome(),
						user.getCognome(),
						roleId,
						user.isDisattivato(),
						user.getId());
			} 
		}
		
		return specializedUser;
	}
		
}