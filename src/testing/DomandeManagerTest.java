package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.CategoriaBean;
import gestioneDomanda.DomandaBean;
import gestioneDomanda.DomandaDAO;
import gestioneDomanda.DomandeManager;
import util.DBManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
public class DomandeManagerTest {

	@BeforeEach
	public void setup() throws IOException, SQLException{
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/initDomandaManager.sql");		
	}
	@AfterEach 
	public void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/teardownDomandaManager.sql");
	}


	@Ignore
	public void testPubblicaDomanda() throws Exception {
		PartecipanteBean autoreTest = new PartecipanteBean();
		autoreTest.setId("autoremock2");
		DomandeManager manager = new DomandeManager();
		CategoriaBean categoria = new CategoriaBean();
		categoria.setId("id000");
		categoria.setNome("pesca");
		String[] categorie = new String[] {categoria.getId()};		
		List<Part> allegati = new ArrayList<Part>();
		assertNotNull(manager.pubblicaDomanda(autoreTest, "un titolo", "un corpo", new Date(), categorie, allegati));
	}


	@Ignore
	public void testGetDomandaById() {
		DomandeManager manager = new DomandeManager();
		assertNotNull(manager.getDomandaById("idmockpertinenti1"));
	}

	@Test
	public void testGetDomandeByAutore() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDomanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCategorieDomanda() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopulateAllegati() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDomandeRecenti() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDomandePertinenti() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumOfDomandePertinenti() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumeroDomandeByAutore() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDomandeRisposte() {
		fail("Not yet implemented");
	}

}
