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

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.DomandaBean;
import gestioneRisposta.RispostaBean;
import gestioneRisposta.RispostaDAO;
import moderazione.MotivazioneBean;
import moderazione.SegnalazioneRispostaBean;
import moderazione.SegnalazioneRispostaDAO;
import util.DBManager;
public class SegnalazioneRispostaDAOTest {
	@BeforeEach
	public  void setup() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/seed.sql");
		dbManager.executeFromScript("Database/testingQueries/initSegnalazioneRispostaDAO.sql");
	}
	@AfterEach
	public  void teardown() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
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
		PartecipanteBean autoreSegnalazione = new PartecipanteBean();
		autoreSegnalazione.setId("idautoremock");
		segnalazione.setUtente(autoreSegnalazione);
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
		SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(segn);
		SegnalazioneRispostaBean segn2 = SegnalazioneRispostaDAO.getSegnalazioneRispostaById("idSegn2");
		assertNull(segn2);
		
	}
}
