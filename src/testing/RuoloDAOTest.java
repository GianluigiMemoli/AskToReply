package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.RuoloDAO;

class RuoloDAOTest {

	@BeforeEach
	public void init() {
		// TODO
	}
	
	@AfterEach
	public void clean() {
		// TODO
	}
	
	// TODO getRuoloByNome
	@Test
	public void getRuoloByNomeTest() {
		assertNotNull(RuoloDAO.getRuoloByName("Partecipante"));
		assertNotNull(RuoloDAO.getRuoloByName("Moderatore"));
		assertNotNull(RuoloDAO.getRuoloByName("MasterModeratore"));
	}
	
	// TODO getRuoloById
	@Test
	public void getRuoloByIdTest() {
		assertNotNull(RuoloDAO.getRuoloById(1));
		assertNotNull(RuoloDAO.getRuoloById(2));
		assertNotNull(RuoloDAO.getRuoloById(3));
	}
}
