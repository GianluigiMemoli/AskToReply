package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneAccount.RuoloDAO;
import util.DBManager;

class RuoloDAOTest {

	public static final String PATH_INIT_SCRIPT = "Database/testingQueries/initRuoloDAO.sql";
	public static final String PATH_TEAR_DOWN_SCRIPT = "Database/testingQueries/tearDownRuoloDAO.sql";
	
	@BeforeEach
	public void init() throws IOException, SQLException {
		DBManager manager = DBManager.getInstance();
		manager.executeFromScript("Database/seed.sql");
		manager.executeFromScript(PATH_INIT_SCRIPT);
	}
	
	@AfterEach
	public void clean() throws IOException, SQLException {
		DBManager manager = DBManager.getInstance();
		manager.executeFromScript("Database/reset.sql");
		manager.executeFromScript(PATH_TEAR_DOWN_SCRIPT);
	}
	
	// TODO getRuoloByNome
	@Test
	public void getRuoloByNomeTest() {
		assertNotNull(RuoloDAO.getRuoloByName("Ruolo Testing 1"));
		assertNotNull(RuoloDAO.getRuoloByName("Ruolo Testing 2"));
		assertNotNull(RuoloDAO.getRuoloByName("Ruolo Testing 3"));
	}
	
	// TODO getRuoloById
	@Test
	public void getRuoloByIdTest() {
		assertNotNull(RuoloDAO.getRuoloById(999997));
		assertNotNull(RuoloDAO.getRuoloById(999998));
		assertNotNull(RuoloDAO.getRuoloById(999999));
	}
}
