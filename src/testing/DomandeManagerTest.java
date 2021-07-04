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
		dbManager.executeFromScript("Database/seed.sql");		
		dbManager.executeFromScript("Database/testingQueries/initDomandaManager.sql");		
	}
	@AfterEach 
	public void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
	}


	@Test
	public void testPubblicaDomanda() throws Exception {
		PartecipanteBean autoreTest = new PartecipanteBean();
		autoreTest.setId("idautoretest");
		DomandeManager manager = new DomandeManager();
		CategoriaBean categoria = new CategoriaBean();
		categoria.setId("id000");
		categoria.setNome("pesca");
		String[] categorie = new String[] {categoria.getId()};		
		List<Part> allegati = new ArrayList<Part>();
		assertNotNull(manager.pubblicaDomanda(autoreTest, "un titolo", "un corpo", new Date(), categorie, allegati));
	}

	@Test
	public void testGetDomandaById() {
		DomandeManager manager = new DomandeManager();
		assertNotNull(manager.getDomandaById("idmockpertinenti1"));
	}

	@Test
	public void testGetDomandeByAutore() {
		DomandeManager manager = new DomandeManager();
		assertNotNull(manager.getDomandeByAutore("idautoremock",0,10));
	}

	@Test
	public void testRemoveDomanda() {
		DomandeManager manager = new DomandeManager();
		manager.removeDomanda("idmockpertinenti1");
		assertNull(manager.getDomandaById("idmockpertinenti1"));
	}

	@Test
	public void testUpdateCategorieDomanda() {
		DomandeManager manager = new DomandeManager();
		DomandaBean domandaToUpdate = manager.getDomandaById("idmockpertinenti1");
		CategoriaBean removedCat = domandaToUpdate.getCategorie().remove(0);
		
		manager.updateCategorieDomanda(domandaToUpdate);
		
		DomandaBean domandaUptodated = manager.getDomandaById("idmockpertinenti1");
		
		boolean isRemovedCategoriaPresente = false;
		for(CategoriaBean cat : domandaUptodated.getCategorie()) {
			if(cat.getId().equals(removedCat.getId())) {
				isRemovedCategoriaPresente = true;
			}
		}
		assertFalse(isRemovedCategoriaPresente);
	}



	@Test
	public void testGetDomandePertinenti() {
		DomandeManager manager = new DomandeManager();
		PartecipanteBean partecipante = new PartecipanteBean();
		ArrayList<CategoriaBean> interessi = new ArrayList<CategoriaBean>();
		CategoriaBean cat1 = new CategoriaBean();
		cat1.setId("id000");
		cat1.setNome("pesca");
		CategoriaBean cat2 = new CategoriaBean();
		cat2.setId("id001");
		cat2.setNome("tennis");
		interessi.add(cat1);
		interessi.add(cat2);
		partecipante.setInteressi(interessi);
		ArrayList<DomandaBean> domandePertinenti = manager.getDomandePertinenti(partecipante, 0, 10);
		for(DomandaBean dom : domandePertinenti) {
			boolean isPertinente = false;
			for(CategoriaBean cat : dom.getCategorie()) {
				if(
						(cat.getId().equals(cat1.getId()))
						||
						(cat.getId().equals(cat2.getId()))
						) {
					isPertinente = true;
				}
					
			}
			assertTrue(isPertinente);
		}
		
	}

	@Test
	public void testGetNumOfDomandePertinenti() {
		DomandeManager manager = new DomandeManager();
		PartecipanteBean utente = new PartecipanteBean();
		utente.setId("idautoremock");
		assertEquals(3, manager.getNumOfDomandePertinenti(utente));
	}

	@Test
	public void testGetNumeroDomandeByAutore() {
		DomandeManager manager = new DomandeManager();
		assertEquals(4, manager.getNumeroDomandeByAutore("idautoremock"));
	}

	@Test
	public void testGetDomandeRisposte() {
		DomandeManager manager = new DomandeManager();
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("idrisponditoremock");
		assertEquals(4, manager.getDomandeRisposte(partecipante).size());
	}

}
