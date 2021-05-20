package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import model.CategoriaBean;
import model.CategoriaDAO;
import model.DomandaBean;
import model.DomandaDAO;
import model.PartecipanteBean;
import model.PartecipanteDAO;

public class DomandaDAOTest {


	
	@Test
	void testAddDomanda() {
		PartecipanteBean autore = PartecipanteDAO.getPartecipanteByEmail("BERNDO@BRUEGGE.COM");
		DomandaBean domanda = new DomandaBean();
		CategoriaBean categoriaDomanda = CategoriaDAO.getCategoriaByNome("biologia");
		domanda.setTitolo("Domanda di test");
		domanda.setCorpo("Questo qui è un test");
		domanda.setAutore(autore);
		domanda.setDataPubblicazione(new Date());
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		categorie.add(categoriaDomanda);
		domanda.setCategorie(categorie);
		assertNotNull(DomandaDAO.addDomanda(domanda));									
	}
}
