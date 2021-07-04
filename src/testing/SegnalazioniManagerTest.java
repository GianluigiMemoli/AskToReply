package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.DomandaBean;
import gestioneRisposta.RispostaBean;
import moderazione.MotivazioneBean;
import moderazione.SegnalazioneBean;
import moderazione.SegnalazioneDomandaBean;
import moderazione.SegnalazioneDomandaDAO;
import moderazione.SegnalazioneRispostaBean;
import moderazione.SegnalazioniManager;
import util.DBManager;

class SegnalazioniManagerTest {

	@BeforeEach
	void setUp() throws Exception {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/seed.sql");		
		dbManager.executeFromScript("Database/testingQueries/initSegnalazioneRispostaDAO.sql");
		dbManager.executeFromScript("Database/testingQueries/initSegnalazioneManager.sql");
	}

	@AfterEach
	void tearDown() throws Exception {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");		
	}

	@Test
	void testCreazioneSegnalazioneDomanda() throws Exception {
		SegnalazioniManager manager = new SegnalazioniManager();			
		/*
		 * MotivazioneBean motivazione, 
			Date dataSegnalazione,
			String commento,
			DomandaBean domandaSegnalata,
			PartecipanteBean utente
			)
			*/
		MotivazioneBean motivazione = new MotivazioneBean();
		motivazione.setId(1);
		DomandaBean domanda = new DomandaBean();
		domanda.setId("DOM4ID");
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("USER3ID");
		manager.creazioneSegnalazioneDomanda(motivazione, new Date(), "commento1", domanda, partecipante);		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = SegnalazioneDomandaDAO.getSegnalazioniDomanda(0, 100);
		boolean segnalazionePresente = false;
		for(SegnalazioneDomandaBean segn : segnalazioni) {
			System.out.println(segn);
			if(segn.getDomandaSegnalata().getId().equals("DOM4ID")) {
				segnalazionePresente = true;
			}
		}
		assertTrue(segnalazionePresente);
	}

	@Test
	void testCreazioneSegnalazioneRisposta() throws Exception {
		SegnalazioniManager manager = new SegnalazioniManager();
		SegnalazioneRispostaBean segn = new SegnalazioneRispostaBean();
		RispostaBean risposta = new RispostaBean();
		MotivazioneBean motivazione = new MotivazioneBean();
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("USER11ID");
		motivazione.setId(1);
		risposta.setId("idRisp1");
		segn.setDataSegnalazione(new Date());		
		segn.setRispostaSegnalata(risposta);
		segn.setCommento("test");
		segn.setMotivazione(motivazione);
		segn.setUtente(partecipante);
		segn.setStato(1);
		
		manager.creazioneSegnalazioneRisposta(segn);
	}

	@Test
	void testGetSegnalazioniDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertEquals(3,manager.getSegnalazioniDomanda(0, 10).size());		
	}

	@Test
	void testGetAllSegnalazioniDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertEquals(3,manager.getAllSegnalazioniDomanda().size());
	}

	@Test
	void testGetAllSegnalazioniRisposta() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertEquals(2,manager.getAllSegnalazioniRisposta().size());

	}

	@Test
	void testGetSegnalazioneDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertNotNull(manager.getSegnalazioneDomanda("SE1ID"));
	}

	@Test
	void testGetSegnalazioneRisposta() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertNotNull(manager.getSegnalazioneRisposta("idsegn1"));	}

	@Test
	void testRisolviSegnalazioneDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		SegnalazioneDomandaBean segn = new SegnalazioneDomandaBean();
		MotivazioneBean motivazione = new MotivazioneBean();
		motivazione.setId(1);
		segn.setMotivazione(motivazione);
		segn.setId("SE1ID");
		DomandaBean domanda = new DomandaBean();
		domanda.setId("DOM3ID");
		segn.setDomandaSegnalata(domanda);
		manager.risolviSegnalazioneDomanda(segn);
		assertNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("SE1ID"));
	}

	@Test
	void testDeclinaSegnalazioneDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		SegnalazioneDomandaBean segn = new SegnalazioneDomandaBean();
		MotivazioneBean motivazione = new MotivazioneBean();
		motivazione.setId(1);
		segn.setMotivazione(motivazione);
		segn.setId("SE1ID");
		DomandaBean domanda = new DomandaBean();
		domanda.setId("DOM3ID");
		segn.setDomandaSegnalata(domanda);
		manager.declinaSegnalazioneDomanda(segn);
		assertNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("SE1ID"));
	}

	@Test
	void testRisolviSegnalazioneRisposta() {
		SegnalazioniManager manager = new SegnalazioniManager();
		SegnalazioneRispostaBean segn = new SegnalazioneRispostaBean();
		RispostaBean risposta = new RispostaBean();
		MotivazioneBean motivazione = new MotivazioneBean();
		PartecipanteBean partecipante = new PartecipanteBean();
		
		partecipante.setId("USER11ID");
		motivazione.setId(1);
		risposta.setId("idRisp1");
		segn.setDataSegnalazione(new Date());		
		segn.setRispostaSegnalata(risposta);
		segn.setCommento("test");
		segn.setMotivazione(motivazione);
		segn.setUtente(partecipante);
		segn.setStato(1);
		segn.setId("idsegn1");
		manager.risolviSegnalazioneRisposta(segn);		
		assertNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("idsegn1"));
		
	}

	@Test
	void testDeclinaSegnalazioneRisposta() {
		SegnalazioniManager manager = new SegnalazioniManager();
		SegnalazioneRispostaBean segn = new SegnalazioneRispostaBean();
		RispostaBean risposta = new RispostaBean();
		MotivazioneBean motivazione = new MotivazioneBean();
		PartecipanteBean partecipante = new PartecipanteBean();
		
		partecipante.setId("USER11ID");
		motivazione.setId(1);
		risposta.setId("idRisp1");
		segn.setDataSegnalazione(new Date());		
		segn.setRispostaSegnalata(risposta);
		segn.setCommento("test");
		segn.setMotivazione(motivazione);
		segn.setUtente(partecipante);
		segn.setStato(1);
		segn.setId("idsegn1");
		manager.declinaSegnalazioneRisposta(segn);		
		assertNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("idsegn1"));	}

	@Test
	void testGetNumeroSegnalazioniDomanda() {
		SegnalazioniManager manager = new SegnalazioniManager();
		assertEquals(3, manager.getNumeroSegnalazioniDomanda());
	}

}
