package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
			if(interessi == null) {
				throw new CampiNonConformiException("Scegli almeno una categoria");
			}
			for (String interesse : interessi) {
				CategoriaBean categoria = new CategoriaBean();
				categoria.setNome(interesse);
				
				this.addInteressePartecipante(registeredPartecipante, categoria);
			}
		} 
		catch(Exception exc) {
			//Se scaturisce un errore cancella tutto quello che è stato creato riguardo l'utente
			PartecipanteBean registeredPartecipante = PartecipanteDAO.getPartecipanteByEmail(email);
			if (registeredPartecipante != null)
					PartecipanteDAO.removePartecipanteById(registeredPartecipante);
			throw exc;
		}
	}
		
	
	public void RegistraModeratore(String email, String password, String username, String nome, String cognome) throws CampiNonConformiException, EmailPresenteException, UsernamePresenteException, NoSuchAlgorithmException {				
		UtenteBean newUser =   generateUtenteBean(nome, cognome, username, email, password);				
		UtenteDAO.doAddModeratore(newUser);		
	}
	
	public UtenteBean autenticaUtente(String email, String password) throws CredenzialiNonValideException {		
		UtenteBean loggingUser = UtenteDAO.getUtenteByEmail(email);
		if(loggingUser == null) {
			throw new CredenzialiNonValideException();
		}
		try {
			String hashedPassword = this.getPasswordHash(password);
			if(loggingUser.getPasswordHash().equals(hashedPassword)) {
				return getUserInstance(loggingUser.getRuoloID(), loggingUser.getEmail());
				
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
	
	public void removeInteressePartecipante(PartecipanteBean partecipante, CategoriaBean categoria) throws CampiNonConformiException {
		log.info(categoria.getNome());
		if(CategoriaDAO.getCategoriaByNome(categoria.getNome()) == null) {			
			throw new CampiNonConformiException();
		}
		PartecipanteDAO.removeInteresse(partecipante, categoria);		
	}
	
	private void updateInteressiUtente(PartecipanteBean user, String[] interessi) throws CampiNonConformiException {
		ArrayList<CategoriaBean> newInteressi = new ArrayList<CategoriaBean>(); 
		for(String nomeInteresse : interessi) {
			newInteressi.add(CategoriaDAO.getCategoriaByNome(nomeInteresse));			
		}
		ArrayList<CategoriaBean> interessiUtente = CategoriaDAO.getCategorieByUtente(user.getId());
		
		
		for(CategoriaBean interesse : newInteressi) {
			if(!interessiUtente.contains(interesse)) {
				this.addInteressePartecipante(user, interesse);
			}
		}
		
		for(CategoriaBean interesse : interessiUtente) {
			if(!newInteressi.contains(interesse)) {
				this.removeInteressePartecipante(user, interesse);
			}
		}
		user.setInteressi(newInteressi);
	}
	
	
	public void updateUtente(PartecipanteBean user, String newNome, String newCognome, String newUsername, String newEmail, String[] interessi, String password) throws CampiNonConformiException, EmailPresenteException, UsernamePresenteException, NoSuchAlgorithmException {
		if(interessi == null) {
			throw new CampiNonConformiException("Inserisci almeno una categoria");
		}
		
		if(password != null) {
			if(!Validator.isPasswordValid(password)) {
				throw new CampiNonConformiException("Password non valida");
			}
		}
		
		if((!Validator.validateUpdateProfileFields(newNome, newCognome, newUsername, newEmail))) {
			throw new CampiNonConformiException();
		}
		
		if (interessi.length == 0) {
			throw new CampiNonConformiException("Inserire almeno una categoria");
		}	
		
		UtenteBean oldUser = UtenteDAO.getUtenteById(user.getId());
		
		if(!oldUser.getEmail().equals(newEmail) && !isEmailAvailable(newEmail))
			throw new EmailPresenteException();
		
		if(!oldUser.getUsername().equals(newUsername) && !isUsernameAvailable(newUsername))
			throw new UsernamePresenteException();
		
		user.setNome(newNome);
		user.setCognome(newCognome);
		user.setEmail(newEmail);
		user.setUsername(newUsername);	
		
		if(password != null) {
			user.setPasswordHash(getPasswordHash(password));
		} else {
			user.setPasswordHash(oldUser.getPasswordHash());
		}
		
		this.updateInteressiUtente(user, interessi);
		PartecipanteDAO.updateUtente(user);		
		
	}
	
	private String getPasswordHash(String password) throws NoSuchAlgorithmException{
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hashedPassword.length; i++) {
			String hex = Integer.toHexString(0xff & hashedPassword[i]);
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
		if(!isEmailAvailable(email))
			throw new EmailPresenteException();
		
		if(!isUsernameAvailable(username))
			throw new UsernamePresenteException();	
		
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
		
							
			
			return newUser;
		}
	
	
	private UtenteBean getUserInstance(int roleId, String email) {
		RuoloBean role = RuoloDAO.getRuoloById(roleId);
		
		if (role.getNome().equals(RuoloBean.ROLE_PARTECIPANTE)) {
			PartecipanteBean partecipante =  PartecipanteDAO.getPartecipanteByEmail(email);
			ArrayList<CategoriaBean> interessi = CategoriaDAO.getCategorieByUtente(partecipante.getId());
			int totaleDomandeUtente = DomandaDAO.getNumeroDomandeByAutore(partecipante.getId());
			ArrayList<DomandaBean> domandeUtente = DomandaDAO.getDomandeByUtente(partecipante.getId(), 0, totaleDomandeUtente);
			System.out.println("Utente accede con numero domande: " + domandeUtente.size());
			partecipante.setDomandeUtente(domandeUtente);
			int totaleRisposteUtente = RispostaDAO.getNumeroRisposteByUtente(partecipante);
			partecipante.setRisposteUtente(RispostaDAO.getStoricoRisposteByUtente(partecipante, 0, totaleRisposteUtente));
			partecipante.setInteressi(interessi);
			return partecipante;
		} else {
			// Se è mastermoderatore o moderatore
			return UtenteDAO.getUtenteByEmail(email);
		}				
	}
	
	public ArrayList<UtenteBean> getAllModeratori(){
		return UtenteDAO.doGetAllModeratori();
	}
	
	public void deleteUtente(String id){
		UtenteDAO.doDeactivateUser(id);
		
	}
		
}