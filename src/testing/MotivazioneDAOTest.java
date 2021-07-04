package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import moderazione.MotivazioneDAO;
import util.DBManager;

public class MotivazioneDAOTest {

	@BeforeAll
	public static void reset() throws IOException, SQLException {
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/reset.sql");
	}
	
	@BeforeEach
	public void setup() throws IOException, SQLException{
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/seed.sql");
	}
	
	@Test
	public void getAllTest(){
		int NUMERO_MOTIVAZIONI = 6;
		assertEquals(NUMERO_MOTIVAZIONI, MotivazioneDAO.getAll().size());
	}

	@Test
	public void getMotivazioneByIdTest(){
		assertNotNull(MotivazioneDAO.getMotivazioneById(3));
		assertNull(MotivazioneDAO.getMotivazioneById(0));
	}

}
