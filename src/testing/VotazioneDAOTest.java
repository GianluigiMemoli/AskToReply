package testing;

import static org.junit.jupiter.api.Assertions.*; 

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.DBManager;
import model.PartecipanteBean;
import model.RispostaBean;
import model.VotazioneBean;
import model.VotazioneDAO;

class VotazioneDAOTest {
	
	@BeforeAll
	public static void init() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/initVotazioneDAO.sql");
	}
	
	@AfterAll
	public static void teardown() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/tearDownVotazioneDAO.sql");
	}
	
	
	
	@Test
	public void addVotazioneRispostaTest() {
		VotazioneBean votazione = new VotazioneBean();
		RispostaBean risposta = new RispostaBean();
		PartecipanteBean utente = new PartecipanteBean();
		utente.setId("idautoremock");
		risposta.setId("idRisp2");
		votazione.setRisposta(risposta);
		votazione.setUtente(utente);
		VotazioneDAO.addVotazioneRisposta(votazione);
		assertEquals(VotazioneDAO.getVotazioniByIdRisposta("idRisp2").size(), 1);
	}
	
	@Test public void getVotazioniByIdRispostaTest(){
		assertEquals(VotazioneDAO.getVotazioniByIdRisposta("idRisp1").size(), 1);
	}
	
	@Test public void removeVotazioneRispostaTest() {
		PartecipanteBean utente = new PartecipanteBean();
		utente.setId("idautoremock");
		RispostaBean risposta = new RispostaBean();
		risposta.setId("idRisp3");
		VotazioneBean votazione = new VotazioneBean(utente, risposta, 0);
		VotazioneDAO.removeVotazioneRisposta(votazione);
		assertEquals(VotazioneDAO.getVotazioniByIdRisposta("idRisp3").size(), 0);
	}
}
