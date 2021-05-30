package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DBManager;
import model.DomandaBean;
import model.PartecipanteBean;
import model.RispostaBean;
import model.RispostaDAO;

class RispostaDAOTest {
	@BeforeEach
	public void setup() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/initRispostaDAO.sql");
	}
	
	@AfterEach
	public void teardown() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/teardownRispostaDAO.sql");
	}
	@Test
	public void addRispostaTest() {
		DomandaBean domandaDaRispondere = new DomandaBean();
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("idrisponditoremock"); 
		domandaDaRispondere.setId("domandaDaRispondere");
		RispostaBean risposta = new RispostaBean("", domandaDaRispondere, "corpo", partecipante, new Date());
		assertNotNull(RispostaDAO.addRisposta(risposta));
	}
	
	@Test 
	public void countRisposteByDomandaIdTest() {
		assertEquals(RispostaDAO.countRisposteByDomandaId("iddomandamock"), 4);
	}
	
	@Test public void getNumeroRisposteByUtenteTest() {
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("idrisponditoremock");
		assertEquals(RispostaDAO.getNumeroRisposteByUtente(partecipante), 4);
	}
	@ Test public void getRispostaByIdTest() {
		assertNotNull(RispostaDAO.getRispostaById("idRisp1"));
	}
	
	@ Test public void getRisposteApprezzateTest() {
		assertEquals(2, RispostaDAO.getRisposteApprezzate("idautoremock").size());
	}
	
	@ Test public void getRisposteNonApprezzateTest() {
		assertEquals(2, RispostaDAO.getRisposteNonApprezzate("idautoremock").size());
	}

	@ Test public void getRisposteByIdDomanda() {
		ArrayList<RispostaBean> risposte = RispostaDAO.getRisposteByIdDomanda("iddomandamock", 1);
		for(RispostaBean risposta : risposte) {
			assertEquals(risposta.getDomanda().getId(), "iddomandamock");
		}
		
	}
	@ Test public void getStoricoRisposteByUtenteTest() {
		PartecipanteBean partecipante = new PartecipanteBean();
		partecipante.setId("idrisponditoremock");
		ArrayList<RispostaBean> risposte = RispostaDAO.getStoricoRisposteByUtente(partecipante, 1, 1000);
		for(RispostaBean risposta : risposte) {
			assertEquals(risposta.getAutore().getId(), "idrisponditoremock");
		}
		
	}
	/*
	@Test public void removeRispostaTest() {
		RispostaBean risposta = RispostaDAO.getRispostaById("idRisp1");
		RispostaDAO.removeRisposta(risposta);
		RispostaBean rispostaNascosta = RispostaDAO.getRispostaById("idRisp1");
		assertTrue(rispostaNascosta.is)
	}
*/
}
