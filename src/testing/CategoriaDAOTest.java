package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import model.CategoriaBean;
import model.CategoriaDAO;



public class CategoriaDAOTest {

	@Test
	public void addCategoriaTest() {
		String NOME = "CATEGORIA_DI_TEST8";
		CategoriaBean cb = new CategoriaBean();
		cb.setNome(NOME);

		assertNull(CategoriaDAO.getCategoriaByNome(NOME));
		CategoriaDAO.addCategoria(cb);
		assertNotNull(CategoriaDAO.getCategoriaByNome(NOME));
	}
	

	@Test
	public void getCategoriaByNomeTest() {
		String NOME = "CATEGORIA_DI_TEST8";
		
		assertNull(CategoriaDAO.getCategoriaByNome("categoriaNONpresente"));  //da togliere?
		assertNotNull(CategoriaDAO.getCategoriaByNome(NOME));
	}
	

	@Test
	public void getAllTest() {
		int NUMERO_CATEGORIE = 13;
		
		assertEquals(NUMERO_CATEGORIE, CategoriaDAO.getAll().size());
	}
	
	
	@Test
	public void getCategorieDomandaByIdDomandaTest() {
		String ID_DOMANDA ="??????";
		assertNotNull(CategoriaDAO.getCategorieDomandaByIdDomanda(ID_DOMANDA).size());
	}
	
	/*
	@Test
	public void getCategorieByUtenteTest() {
		
	}*/
}
