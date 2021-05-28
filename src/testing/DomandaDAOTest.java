package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import model.CategoriaBean;
import model.CategoriaDAO;
import model.DBManager;
import model.DomandaBean;
import model.DomandaDAO;
import model.PartecipanteBean;
import model.PartecipanteDAO;

public class DomandaDAOTest {
	/*RIVEDERE:
	 		* 	addCategoriaDomanda()
	 * FATTE:
	 * 		getDomandeByUtente
	 * 		getDomandePertinenti
	 * 		getDomandeRecenti
  	 		getDomandeRisposte
  			getNumeroDomandePertinenti
	  		getNumeroDomandeByAutore
	 
	 * ASPETTARE:
		  removeDomanda
		  updateCategorieDomanda
		  getDomandeCercate
	 * */
	
	@Test
	public void testAddDomanda() {
		
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
	
	@Test
	public void testGetDomandaByIDNotNull() {
		assertNotNull(DomandaDAO.getDomandaById("idmock"));
	}
	
	@Test
	public void testGetDomandaByIDNull() {
		assertNull(DomandaDAO.getDomandaById("idmock42"));
	}
	
	@Ignore 
	// non posso testarlo ??????
	public void testAddCategoriaDomanda() {
		CategoriaBean categoria = CategoriaDAO.getCategoriaByNome("pesca");
		DomandaBean domanda = DomandaDAO.getDomandaById("idmock");
		DomandaDAO.addCategoriaDomanda(domanda, categoria);		
	}
	
	@Test
	public void testGetDomandeByUtente() {
		ArrayList<DomandaBean> domandeUtente = DomandaDAO.getDomandeByUtente("idautoremock");
		for(DomandaBean domanda : domandeUtente) {
			assertEquals(domanda.getAutore().getId(), "idautoremock");
		}
	}
	
	@Test 
	public void testGetNumeroDomandeByAutore() {
		int amount = DomandaDAO.getNumeroDomandeByAutore("idautoremock");
		assertEquals(amount, 4);
	}
	
	@Test 
	public void testGetDomandePertinenti() {
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		CategoriaBean cat1 = new CategoriaBean();
		cat1.setId("id000");	
		cat1.setNome("pesca");
		CategoriaBean cat2 = new CategoriaBean();
		cat1.setId("id001");	
		cat1.setNome("tennis");
		categorie.add(cat1);
		categorie.add(cat2);
		ArrayList<DomandaBean> domande = DomandaDAO.getDomandePertinenti(categorie, 0, 1000);
		assertEquals(domande.size(), 3);
	}
	// praticamente è lo stesso test !? 
	
	@Test
	public void testGetNumeroDomandePertinenti() {
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		CategoriaBean cat1 = new CategoriaBean();
		cat1.setId("id000");	
		cat1.setNome("pesca");
		CategoriaBean cat2 = new CategoriaBean();
		cat1.setId("id001");	
		cat1.setNome("tennis");
		categorie.add(cat1);
		categorie.add(cat2);
		int amount = DomandaDAO.getNumeroDomandePertinenti(categorie);
		assertEquals(amount, 3);
	}
	
	@Test
	public void testGetDomandeRecenti() {
		ArrayList<DomandaBean> domandeRecenti = DomandaDAO.getDomandeRecenti(0, 1000);
		boolean sorted = true;
		for(int i = 0; i < domandeRecenti.size(); i++) {
			for(int j=i+1; j < domandeRecenti.size(); j++) {
				int compare = domandeRecenti.get(i).getDataPubblicazione().compareTo(domandeRecenti.get(j).getDataPubblicazione());
				if(compare > 0) {
					sorted = false;
				}
			}
			assertTrue(sorted);
		}
	}
	
	@Test
	public void testGetDomandeRisposte() {
		ArrayList<DomandaBean> domandeRisposte = DomandaDAO.getDomandeRisposte("idrisponditoremock");
		assertEquals(domandeRisposte.size(), 4);
	}
	/*
	 * Aspettando il nuovo DomandaBean
	@Test
	public void testRemoveDomanda() {
		DomandaDAO.removeDomanda("idmock");
		assertTrue(DomandaDAO.getDomandaById("idmock").is);
	}
	*/
}