package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneAccount.UtenteBean;
import gestioneAccount.UtenteDAO;
import util.DBManager;

public class UtenteDAOTest {
	
	@AfterEach
	public void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
	}
	
	@BeforeEach
	public void setup() throws IOException, SQLException{
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/seed.sql");
		dbManager.executeFromScript("Database/populate/populateUtenti.sql");
	}
	
	@Test
	public void doAddUtente() {
		UtenteBean ub = new UtenteBean("utente4@email.com", "notHashedPass", "user4", "Carmine", "Verdi", 1, false, null);
		assertNull(UtenteDAO.getUtenteByEmail("utente4@email.com"));
		assertNull(UtenteDAO.getUtenteByUsername("user4"));
		UtenteDAO.doAddUtente(ub);
		assertNotNull(UtenteDAO.getUtenteByUsername("user4"));
	}

	@Test
	public void getUtenteByEmail() {
		String email="utente3@email.com";
		assertNotNull(UtenteDAO.getUtenteByEmail(email));
		email="email@nonpresente.com";
		assertNull(UtenteDAO.getUtenteByEmail(email));
	}
	
	@Test
	public void doGetAllModeratori() {
		int NUMERO_MOD = 1;
		assertEquals(NUMERO_MOD, UtenteDAO.doGetAllModeratori().size());
		String ID_MOD = "MOD1ID";
		assertEquals(ID_MOD, UtenteDAO.doGetAllModeratori().get(0).getId());
	}
	
	@Test
	public void doAddModeratore() {
		String EMAIL = "mod2@email.com";
		String PASSWORD = "notHashedPass";
		String USERNAME = "mod2";
		String NOME = "Steve";
		String COGNOME = "Roger";
		UtenteBean ub = new UtenteBean(EMAIL, PASSWORD, USERNAME, NOME, COGNOME, 2, false, null);
		assertNull(UtenteDAO.getUtenteByUsername(USERNAME));
		UtenteDAO.doAddModeratore(ub);
		assertNotNull(UtenteDAO.getUtenteByUsername(USERNAME));
	}

	@Test
	public void doDeactivateUser(){
		String ID_UTENTE ="MOD1ID";
		assertEquals(false,(UtenteDAO.getUtenteById(ID_UTENTE)).isDisattivato());
		UtenteDAO.doDeactivateUser(ID_UTENTE);
		assertEquals(true,(UtenteDAO.getUtenteById(ID_UTENTE)).isDisattivato());
	}
	
	@Test
	public void getUtenteById(){	
		String id="USER1ID";
		assertNotNull(UtenteDAO.getUtenteById(id));
		id="idnonesistente";
		assertNull(UtenteDAO.getUtenteByUsername(id));
	}
	
	@Test
	public void getUtenteByUsername() {
		String username="user3";
		assertNotNull(UtenteDAO.getUtenteByUsername(username));
		username="usernonpresente";
		assertNull(UtenteDAO.getUtenteByUsername(username));
	}
}