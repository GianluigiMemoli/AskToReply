package testing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exceptions.CampiNonConformiException;
import Exceptions.CredenzialiNonValideException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;
import gestioneAccount.AccountManager;
import gestioneAccount.PartecipanteBean;
import gestioneAccount.PartecipanteDAO;
import gestioneAccount.UtenteBean;
import util.DBManager;

public class AccountManagerTest {


	@BeforeAll
	public static void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
	}
	
	@BeforeEach
	public void setup() throws IOException, SQLException{
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/popola.sql");
	}
	
	
	@Test
	public void testRegisterUser() throws NoSuchAlgorithmException, CampiNonConformiException, EmailPresenteException, UsernamePresenteException, CredenzialiNonValideException{
		String NOME = "Carlo";
		String COGNOME = "Monti";
		String USERNAME = "carlom";
		String EMAIL = "carlo@monti.com";
		String PASSWORD = "password";
		AccountManager manager = new AccountManager();
		String[] INTERESSI = {"Storia", "Informatica"};
		manager.RegisterUser(NOME, COGNOME, USERNAME, EMAIL, PASSWORD, INTERESSI);
		assertNotNull(manager.autenticaUtente(EMAIL, PASSWORD));
	}
	
	@Test
	public void testRegistraModeratore() throws NoSuchAlgorithmException, CampiNonConformiException, EmailPresenteException, UsernamePresenteException, CredenzialiNonValideException{
		String EMAIL = "marco@tozzi.com";
		String PASSWORD = "password";
		String USERNAME = "marcotoz";
		String NOME = "Marco";
		String COGNOME = "Tozzi";
		AccountManager manager = new AccountManager();
		manager.RegistraModeratore(EMAIL, PASSWORD, USERNAME, NOME, COGNOME);
		assertNotNull(manager.autenticaUtente(EMAIL, PASSWORD));
	}
	
	@Test
	public void autenticaUtente() throws CredenzialiNonValideException{
		String EMAIL_PRESENTE = "giorgia@santi.it";
		String PASSWORD = "password";
		AccountManager manager = new AccountManager();
		assertNotNull(manager.autenticaUtente(EMAIL_PRESENTE, PASSWORD));
	}
	
	@Test
	public void updateUtente() throws NoSuchAlgorithmException, CampiNonConformiException, EmailPresenteException, UsernamePresenteException, CredenzialiNonValideException{
		PartecipanteBean partecipante = PartecipanteDAO.getPartecipanteByEmail("laura@marcocci.com");
		AccountManager manager = new AccountManager();
		String NEWNOME = "Laura";
		String NEWCOGNOME = "Marcocci";
		String USERNAME = "UserNew";
		String EMAIL = "laura@marcocci.com";
		String[] NEWINTERESSI = {"Storia", "Informatica"};
		String PASSWORD = "passnew";
		manager.updateUtente(partecipante, NEWNOME, NEWCOGNOME, USERNAME, EMAIL, NEWINTERESSI, PASSWORD);
		assertNotNull(manager.autenticaUtente(EMAIL, PASSWORD));
	}
	
	@Test
	public void getAllModeratori(){
		int NUM_MODS = 2;
		AccountManager manager = new AccountManager();
		assertEquals(NUM_MODS, manager.getAllModeratori().size());
	}
	
	@Test
	public void deleteUtente(){
		String ID_MOD_DA_ELIMINARE = "MOD2ID";
		AccountManager manager = new AccountManager();
		manager.deleteUtente(ID_MOD_DA_ELIMINARE);
		ArrayList<UtenteBean> listaModeratori = manager.getAllModeratori();
		boolean bool = false;
		for(int i=0; i<listaModeratori.size(); i++) {
			if(listaModeratori.get(i).getId().equals(ID_MOD_DA_ELIMINARE))
			{
				bool = listaModeratori.get(i).isDisattivato();
			}
		}
		assertTrue(bool);
	}
	
}
