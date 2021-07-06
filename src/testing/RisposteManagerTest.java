package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.Part;

import java.sql.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.DomandaBean;
import gestioneRisposta.RispostaBean;
import gestioneRisposta.RisposteManager;
import util.DBManager;

class RisposteManagerTest {

	@BeforeEach
	public void setup() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		// dbManager.executeFromScript("Database/reset.sql");
		dbManager.executeFromScript("Database/seed.sql");
		dbManager.executeFromScript("Database/testingQueries/initRisposteManager.sql");	
	}
	
	@AfterEach
	public void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/tearDownRisposteManager.sql");	
		dbManager.executeFromScript("Database/reset.sql");
	}
	
	@Test
	public void testPubblicaRisposta() throws Exception {
		
		RisposteManager man = new RisposteManager();
		
		Date dataPubblicazione = new Date(System.currentTimeMillis());
		
		List<Part> allegati = new ArrayList<Part>();
		
		RispostaBean risposta = man.pubblicaRisposta(
			"id-domanda-testing-1", 
			"corpo risposta testPubblicaRisposta", 
			allegati, 
			"id-utente-testing-4", 
			"id-utente-testing-1",
			dataPubblicazione
		);
	
		System.out.println(risposta.getId());
		
		assertNotNull(RisposteManager.getRispostaById(risposta.getId()));
		
	}
	
	@Test
	public void testGetRispostaById() {
		
		RisposteManager man = new RisposteManager();
		
		// Risposta esistente
		assertNotNull(RisposteManager.getRispostaById("id-risposta-testing-1"));
		
	}
	
	@Test
	public void testGetRisposteByIdDomanda() throws IOException {
		
		RisposteManager man = new RisposteManager();
		
		assertEquals(2, RisposteManager.getRisposteByIdDomanda("id-domanda-testing-1", 0).size());
		// assertEquals(0, RisposteManager.getRisposteByIdDomanda("id-domanda-testing-999999", 1).size());
	
	}
	
	@Test
	public void testCaricaAllegati() {
		
		RisposteManager man = new RisposteManager();
		
		// TODO
	
	}
	
	@Test
	public void testRemoveRisposta() throws IOException {
		
		RisposteManager man = new RisposteManager();
		
		RispostaBean risposta = new RispostaBean();
		risposta.setId("id-risposta-testing-2");
		
		DomandaBean domanda = new DomandaBean();
		domanda.setId("id-domanda-testing-1");
		
		risposta.setDomanda(domanda);
		
		man.removeRisposta(risposta);
		
		ArrayList<RispostaBean> risposte = RisposteManager.getRisposteByIdDomanda(domanda.getId(), 0);
		
		assertEquals(false, risposte.contains(risposta));
		
	}
	
	@Test
	public void testGetNumeroRisposte() {
		
		RisposteManager man = new RisposteManager();
		
		DomandaBean domanda = new DomandaBean();
		
		domanda.setId("id-domanda-testing-1");
		
		assertEquals(man.getNumeroRisposte(domanda), 2);
	
	}
	
	@Test
	public void testGetNumeroRisposteByUtente() {
		RisposteManager man = new RisposteManager();
		
		PartecipanteBean utente = new PartecipanteBean();
		
		utente.setId("id-utente-testing-2");
		
		assertEquals(man.getNumeroRisposteByUtente(utente), 1);
	}
	
	@Test
	public void testGetStoricoRisposte() throws IOException {
		
		RisposteManager man = new RisposteManager();
		
		PartecipanteBean user = new PartecipanteBean();
		
		user.setId("id-utente-testing-2");
		
		assertEquals(1, man.getStoricoRisposte(user, 1, 10).size());
	
	}
	
	@Test
	public void testGetRisposteApprezzate() {
		
		RisposteManager man = new RisposteManager();
		
		PartecipanteBean utente = new PartecipanteBean();
		utente.setId("id-utente-testing-4");
		
		HashSet<RispostaBean> apprezzamenti = man.getRisposteApprezzate(utente);
		
		assertEquals(1, apprezzamenti.size());
		
	}
	
	@Test
	public void testGetRisposteNonApprezzate() {
		
		RisposteManager man = new RisposteManager();
		
		PartecipanteBean utente = new PartecipanteBean();
		utente.setId("id-utente-testing-4");
		
		HashSet<RispostaBean> nonApprezzamenti = man.getRisposteNonApprezzate(utente);
		
		assertEquals(1, nonApprezzamenti.size());
	
	}
	
}
