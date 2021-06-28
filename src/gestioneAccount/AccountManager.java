package gestioneAccount;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Logger;

import Exceptions.CampiNonConformiException;
import Exceptions.CredenzialiNonValideException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;
import gestioneDomanda.CategoriaBean;
import gestioneDomanda.CategoriaDAO;
import gestioneDomanda.DomandaBean;
import gestioneDomanda.DomandaDAO;
import gestioneRisposta.RispostaDAO;
import util.Validator;

/**
 * @author Gmemo
 *
 */

public class AccountManager {
	static Logger log = Logger.getLogger(AccountManager.class.getName());
	
	/**
	 * Crea un record di tipo Partecipante nel database
	 * @param nome
	 * String che rappresenta il  nome utente
	 * @param cognome
	 * String che rappreenta il cognome del utente
	 * @param username
	 * String che rappresenta l'username del utente
	 * @param email
	 * String email  del utente
	 * @param password
	 * La password che verrà hashata (SHA-256)
	 * @param interessi
	 * Un array di String, che rappresentano gli ID delle categorie scelte dall'utente
	 * 
	 * @throws CampiNonConformiException
	 * @throws NoSuchAlgorithmException
	 * @throws EmailPresenteException
	 * @throws UsernamePresenteException
	 */
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
		
	
	/**
	 Crea un record di tipo Moderatore nel database
	 * @param nome
	 * String che rappresenta il  nome Moderatore
	 * @param cognome
	 * String che rappresenta il cognome del Moderatore
	 * @param username
	 * String che rappresenta l'username del Moderatore
	 * @param email
	 * String email  del Moderatore
	 * @param password
	 * La password che verrà hashata (SHA-256)
	 * @throws CampiNonConformiException
	 * @throws EmailPresenteException
	 * @throws UsernamePresenteException
	 * @throws NoSuchAlgorithmException
	 */
	public void RegistraModeratore(String email, String password, String username, String nome, String cognome) throws CampiNonConformiException, EmailPresenteException, UsernamePresenteException, NoSuchAlgorithmException {				
		UtenteBean newUser =   generateUtenteBean(nome, cognome, username, email, password);				
		UtenteDAO.doAddModeratore(newUser);		
	}
	
	/**
	 * @param email
	 * String email per identificare univocamente l'utente da autenticare
	 * @param password
	 * String password che verrà hashata e confrontata con l'hash presente nel record
	 * @return UtenteBean che rappresenta l'istanza dell'utente autenticato
	 * @throws CredenzialiNonValideException
	 * Lanciata nel momento in cui non c'è corrispondenza in DB con le credenziali inserite
	 */
	public UtenteBean autenticaUtente(String email, String password) throws CredenzialiNonValideException {		
		UtenteBean loggingUser = UtenteDAO.getUtenteByEmail(email);
		if(loggingUser == null || loggingUser.isDisattivato()) {
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
	
	/**Inserisce un interesse relativo al Partecipante.
	 * @param partecipante
	 * Partecipante a cui aggiungere un interesse
	 * @param categoria
	 * La categoria a cui appartiene l'interesse
	 * @throws CampiNonConformiException
	 * Se non esiste alcuna categoria con quel ID (causa manipolazione del frontend)
	 */
	public void addInteressePartecipante(PartecipanteBean partecipante, CategoriaBean categoria) throws CampiNonConformiException {
		log.info(categoria.getNome());
		if(CategoriaDAO.getCategoriaByNome(categoria.getNome()) == null) {			
			throw new CampiNonConformiException();
		}
		PartecipanteDAO.addInteresse(partecipante, categoria);		
	}
	
	/**Aggiorna la lista di interessi di un utente, rimuovendo la categoria passata come parametro
	 * @param partecipante
	 * Partecipante di cui aggiornare lista interessi
	 * @param categoria
	 * Categoria da rimuovere dalla lista interessi
	 * @throws CampiNonConformiException
	 * Se la categoria non esiste
	 */
	private void removeInteressePartecipante(PartecipanteBean partecipante, CategoriaBean categoria) throws CampiNonConformiException {
		log.info(categoria.getNome());
		if(CategoriaDAO.getCategoriaByNome(categoria.getNome()) == null) {			
			throw new CampiNonConformiException();
		}
		PartecipanteDAO.removeInteresse(partecipante, categoria);		
	}
	
	/** Aggiorna la lista di interessi dell'utente. In questo modo: 
	 * -> Per ogni Categoria c appartenente a intersezione(interessi, user.getInteressi()) c viene rimossa dalla lista interessi user.
	 * -> Per ogni categoria c' appartenente a differenza(interessi, user.getInteressi()) c' viene aggiunta alla lista interessi user.
	 * 
	 * @param user
	 * PartecipanteBean di cui aggiornare lista interessi
	 * @param interessi
	 * Array di String interessi con cui manipolare la lista di interessi utente 
	 * @throws CampiNonConformiException
	 */
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
	
	
	/** Aggiorna i dati del record PartecipanteBean. 
	 * Dato che vengono ricevuti tutti i campi del profilo, anche se non sono stati modificati il record viene aggiornato con i campi solo se:
	 * oldField != newField
	 * @param user
	 * PartecipanteBean da aggiornare
	 * @param newNome
	 * String per il nuovo attributo "nome"
	 * @param newCognome
	 * String per il nuovo attributo "cognome"
	 * @param newUsername
	 * String per il nuovo attributo "username"
	 * @param newEmail
	 * String per il nuovo attributo "email"
	 * @param interessi
	 * String[] contenenti i nuovi interessi
	 * @param password
	 * String per il nuovo attributo "password"
	 * @throws CampiNonConformiException
	 * Se il formato dei campi non è corretto
	 * @throws EmailPresenteException
	 * Se la newEmail è in uso
	 * @throws UsernamePresenteException
	 * Se newUsername è in uso
	 * @throws NoSuchAlgorithmException
	 */
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
	
	/**Controlla che la email non sia già in uso
	 * @param email
	 * @return boolean
	 */
	private static boolean isEmailAvailable(String email) {
		return UtenteDAO.getUtenteByEmail(email) == null;				
	}
	
	/** Controlla che username non sia in uso
	 * @param username
	 * @return boolean
	 */
	private static boolean isUsernameAvailable(String username) {
		return UtenteDAO.getUtenteByUsername(username) == null;				
	}
	
	/**
	 * Crea un record di tipo Utente nel database
	 * @param nome
	 * String che rappresenta il  nome utente
	 * @param cognome
	 * String che rappreenta il cognome del utente
	 * @param username
	 * String che rappresenta l'username del utente
	 * @param email
	 * String email  del utente
	 * @param password
	 * La password che verrà hashata (SHA-256)	 
	 * 
	 * @throws CampiNonConformiException
	 * @throws NoSuchAlgorithmException
	 * @throws EmailPresenteException
	 * @throws UsernamePresenteException
	 */
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
	
	
	/** Dato il ruolo e la email restituisce la giusta specializzazione del Utente, il client deve sapere a costa castarlo. (Da sostituire con AbstractFactory)
	 * @param roleId
	 * @param email
	 * @return
	 */
	private UtenteBean getUserInstance(int roleId, String email) {
		RuoloBean role = RuoloDAO.getRuoloById(roleId);
		
		if (role.getNome().equals(RuoloBean.ROLE_PARTECIPANTE)) {
			PartecipanteBean partecipante =  PartecipanteDAO.getPartecipanteByEmail(email);
			ArrayList<CategoriaBean> interessi = CategoriaDAO.getCategorieByUtente(partecipante.getId());
			int totaleDomandeUtente = DomandaDAO.getNumeroDomandeByAutore(partecipante.getId());
			ArrayList<DomandaBean> domandeUtente = DomandaDAO.getDomandeByUtente(partecipante.getId(), 0, totaleDomandeUtente);
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