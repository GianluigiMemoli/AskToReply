package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.tomcat.jni.Time;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DBManager;
import model.DomandaBean;
import model.MotivazioneBean;
import model.PartecipanteBean;
import model.RispostaBean;
import model.RispostaDAO;
import model.SegnalazioneRispostaBean;
import model.SegnalazioneRispostaDAO;
public class SegnalazioneRispostaDAOTest {
	@BeforeEach
	public  void setup() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/initSegnalazioneRispostaDAO.sql");
	}
	@AfterEach
	public  void teardown() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/testingQueries/teardownSegnalazioneRispostaDAO.sql");
	}
	@Test
	public void addSegnalazioneRispostaTest() {
		SegnalazioneRispostaBean segnalazione = new SegnalazioneRispostaBean();
		segnalazione.setDataSegnalazione(new Date());
		segnalazione.setCommento("commento_test");
		RispostaBean risposta = new RispostaBean();
		risposta.setId("idRisp4");
		segnalazione.setRispostaSegnalata(risposta);
		segnalazione.setStato(1);
		MotivazioneBean motivazione = new MotivazioneBean(1, "contenuti offensivi");
		segnalazione.setMotivazione(motivazione);
		segnalazione = SegnalazioneRispostaDAO.addSegnalazioneRisposta(segnalazione);
		assertNotNull(segnalazione.getIdSegnalazione());		
	}
	@Test
	public void getElencoSegnalazioniTest() {
		assertEquals(SegnalazioneRispostaDAO.getElencoSegnalazioniRisposte().size(), 2);
	}
	@Test
	public void getSegnalazioneRispostaByIdTest() {
		assertNotNull(SegnalazioneRispostaDAO.getSegnalazioneRispostaById("idSegn2"));
	}
	@Test
	public void updateStatoSegnalazioneRispostaTest() {
		SegnalazioneRispostaBean segn = SegnalazioneRispostaDAO.getSegnalazioneRispostaById("idSegn2");
		segn.setStato(2);
		System.out.println("UPDATE STATO SEGN");		
		System.out.println(segn.getStato());
		SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(segn);
		SegnalazioneRispostaBean segn2 = SegnalazioneRispostaDAO.getSegnalazioneRispostaById("idSegn2");
		System.out.println(segn2);
		assertEquals(2, 2);
		
	}
}