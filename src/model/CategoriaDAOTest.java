package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoriaDAOTest {

	@Test
	void test() {
		CategoriaBean newCat = new CategoriaBean();
		newCat.setNome("Biologia");
		CategoriaDAO.addCategoria(newCat);
	}

}
