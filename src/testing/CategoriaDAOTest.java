package testing;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CategoriaBean;
import model.CategoriaDAO;
import model.DBManager;



public class CategoriaDAOTest {

	@BeforeEach
	public void setup() throws IOException, SQLException{
		System.out.println("@@@@@@###########@@@@@@@@@@");
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/popola.sql");
	}
	

	@Test
	public void getCategoriaByNomeTest() {
		String NOME = "CATEGORIA_DI_TEST";
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
		int NUMERO_CATEGORIE = 7;
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
	}
}
