package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneAccount.PartecipanteBean;
import gestioneAccount.PartecipanteDAO;
import gestioneDomanda.CategoriaBean;
import gestioneDomanda.CategoriaDAO;
import util.DBManager;

class PartecipanteDAOTest {

	public static final String PATH_INIT_SCRIPT = "Database/testingQueries/initPartecipanteDAO.sql";
	public static final String PATH_TEAR_DOWN_SCRIPT = "Database/testingQueries/tearDownPartecipanteDAO.sql";
	
	private final Logger LOGGER = Logger.getLogger(PartecipanteDAOTest.class.getName());
	
	@BeforeEach
	public void init() throws SQLException, IOException {
		DBManager manager = DBManager.getInstance();
		manager.executeFromScript("Database/seed.sql");
		manager.executeFromScript(PATH_INIT_SCRIPT);
	}
	
	@AfterEach
	public void tearDown() throws IOException, SQLException {
		DBManager manager = DBManager.getInstance();
		manager.executeFromScript("Database/reset.sql");
		manager.executeFromScript(PATH_TEAR_DOWN_SCRIPT);
	}
	
	@Test
	public void testGetPartecipanteByEmail() {
		PartecipanteBean partecipante = PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com");
		assertNotNull(partecipante);
		
		partecipante = PartecipanteDAO.getPartecipanteByEmail("email-utente-testing-non-presente@");
		assertNull(partecipante);
	}
	
	// I metodi testAddInteresse e testRemoveInteresse vanno testati nel Manager perché non è possibile testare senza usare CategoriaDAO
	
	@Ignore
	public void testAddInteresse() {
		
		PartecipanteBean partecipante = PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com");
		
		CategoriaBean categoriaDaAggiungere = CategoriaDAO.getCategoriaByNome("Cucina");
		
		PartecipanteDAO.addInteresse(partecipante, categoriaDaAggiungere);
		
		ArrayList<CategoriaBean> interessi = CategoriaDAO.getCategorieByUtente(partecipante.getId());
		
		assertEquals(3, interessi.size());
	}
	
	@Ignore
	public void testRemoveInteresse() {
		
		PartecipanteBean partecipante = PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com");
		
		CategoriaBean categoriaDaRimuovere = CategoriaDAO.getCategoriaByNome("Botanica");
		
		PartecipanteDAO.removeInteresse(partecipante, categoriaDaRimuovere);
		
		ArrayList<CategoriaBean> nuoviInteressi = CategoriaDAO.getCategorieByUtente(partecipante.getId());
		
		assertEquals(1, nuoviInteressi.size());
		
	}
	
	@Test
	public void testRemovePartecipanteById() {
		
		PartecipanteBean partecipanteDaRimuovere = PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com");
		
		PartecipanteDAO.removePartecipanteById(partecipanteDaRimuovere);
		
		assertNull(PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com"));
		
	}

	@Test
	public void testUpdateUtente() {
		
		PartecipanteBean partecipanteDaAggiornare = PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com");
		
		partecipanteDaAggiornare.setCognome("Winter");
		partecipanteDaAggiornare.setNome("Titus");
		partecipanteDaAggiornare.setEmail("winter.titus@gmail.com");
		partecipanteDaAggiornare.setUsername("w.titus");
		
		PartecipanteDAO.updateUtente(partecipanteDaAggiornare);
		
		assertNull(PartecipanteDAO.getPartecipanteByEmail("sommerville.ian@gmail.com"));
		assertNotNull(PartecipanteDAO.getPartecipanteByEmail("winter.titus@gmail.com"));
	}
	
}
