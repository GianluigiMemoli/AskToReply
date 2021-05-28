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


import model.DBManager;
import model.MotivazioneDAO;

public class MotivazioneDAOTest {

	@BeforeEach
	public void setup() throws IOException, SQLException{
		System.out.println("@@@@@@###########@@@@@@@@@@");
		DBManager dbManager = DBManager.getInstance();
		dbManager.executeFromScript("Database/popola.sql");
	}
	
	@Test
	public void getAllTest(){
		int NUMERO_MOTIVAZIONI = 6;
		assertEquals(NUMERO_MOTIVAZIONI, MotivazioneDAO.getAll().size());
	}

	@Test
	public void getMotivazioneByIdTest(){
		//Random random = new Random();
		//assertNotNull(MotivazioneDAO.getMotivazioneById(random.nextInt(MotivazioneDAO.getAll().size())+1));
		assertNotNull(MotivazioneDAO.getMotivazioneById(3));
		assertNull(MotivazioneDAO.getMotivazioneById(0));
	}

}
