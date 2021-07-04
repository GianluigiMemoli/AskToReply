package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneDomanda.CategoriaBean;
import gestioneDomanda.CategoriaDAO;
import util.DBManager;

public class CategoriaDAOTest {

	@BeforeAll
	public static void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
	}
	
	@BeforeEach
	public void setup() throws IOException, SQLException{
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/seed.sql");
		dbManager.executeFromScript("Database/populate/populateCategorie.sql");
	}
	
	@Test
	public void getCategoriaByNomeTest() {
		String NOME = "Economia";
		assertNull(CategoriaDAO.getCategoriaByNome("categorianonpresente"));
		assertNotNull(CategoriaDAO.getCategoriaByNome(NOME));
	}
	
	@Test
	public void addCategoriaTest() {
		String NOME = "CATEGORIA_DI_TEST";
		CategoriaBean cb = new CategoriaBean();
		cb.setNome(NOME);
		assertNull(CategoriaDAO.getCategoriaByNome(NOME));
		CategoriaDAO.addCategoria(cb);
		assertNotNull(CategoriaDAO.getCategoriaByNome(NOME));
	}

	@Test
	public void getAllTest() {
		int NUMERO_CATEGORIE = 6;
		assertEquals(NUMERO_CATEGORIE, CategoriaDAO.getAll().size());
	}
	
	@Test
	public void getCategorieDomandaByIdDomandaTest() {
		String ID_DOMANDA ="DOM2ID";
		int N_CATEGORIE_DOM = 3;
		assertEquals(N_CATEGORIE_DOM, CategoriaDAO.getCategorieDomandaByIdDomanda(ID_DOMANDA).size());
	}
	
	@Test
	public void getCategorieByUtenteTest() {
		int N_INTERESSI_USER = 3;
		assertEquals(N_INTERESSI_USER, CategoriaDAO.getCategorieByUtente("USER2ID").size());
		//vedere se ha preso quelli giusti.
	}
}
