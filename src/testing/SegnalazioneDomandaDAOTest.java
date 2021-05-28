package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

import model.DomandaDAO;
import model.MotivazioneBean;
import model.SegnalazioneDomandaBean;
import model.SegnalazioneDomandaDAO;

public class SegnalazioneDomandaDAOTest {

	@Test
	public void getAllTest(){
		int NUMERO_SEGNALAZIONI = 3;
		assertEquals(NUMERO_SEGNALAZIONI, SegnalazioneDomandaDAO.getAll().size());
	}
	
	@Test
	public void getNumeroSegnalazioniDomandaTest(){
		int NUMERO_SEGNALAZIONI = 3;
		assertEquals(NUMERO_SEGNALAZIONI, SegnalazioneDomandaDAO.getNumeroSegnalazioniDomanda());
	}
	
	@Test
	public void getSegnalazioniDomandaTest(){
		int NUMERO_SEGNALAZIONI = 3;
		assertEquals(NUMERO_SEGNALAZIONI, SegnalazioneDomandaDAO.getSegnalazioniDomanda(0,10));
	}
	
	@Test
	public void getSegnalazioneDomandaByIdTest(){
		/*Random random = new Random();
		assertNotNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById(SegnalazioneDomandaDAO.getAll().get(random.nextInt(SegnalazioneDomandaDAO.getAll().size())).getId()));*/
		assertNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("ID_NON_PRESENTE"));
		assertNotNull(SegnalazioneDomandaDAO.getSegnalazioneDomandaById("SE1ID"));
	}
		
	@Test
	public void addSegnalazioneDomandaTest(){
		int NUMERO_SEGNALAZIONI = 3;
		assertEquals(NUMERO_SEGNALAZIONI, SegnalazioneDomandaDAO.getAll().size());
		SegnalazioneDomandaBean segnalazione = new SegnalazioneDomandaBean();
		segnalazione.setCommento("Commento test");
		MotivazioneBean mb = new MotivazioneBean();
		mb.setId(3);
		segnalazione.setMotivazione(mb);
		segnalazione.setStato(1);
		segnalazione.setDomandaSegnalata(DomandaDAO.getDomandaById("DOM1ID"));
		segnalazione.setDataSegnalazione(new Date());
		SegnalazioneDomandaDAO.addSegnalazioneDomanda(segnalazione);
		NUMERO_SEGNALAZIONI = 4;
		assertEquals(NUMERO_SEGNALAZIONI, SegnalazioneDomandaDAO.getAll().size());
	}
	
	@Test
	public void updateStatoSegnalazioneDomandaTest(){
		SegnalazioneDomandaBean sb = SegnalazioneDomandaDAO.getSegnalazioneDomandaById("SE1ID");
		assertEquals(1, sb.getStato());
		sb.setStato(2);
		SegnalazioneDomandaDAO.updateStatoSegnalazioneDomanda(sb);
		assertEquals(2, sb.getStato());
	}


	
}