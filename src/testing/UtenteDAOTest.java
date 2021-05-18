package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import model.UtenteBean;
import model.UtenteDAO;

public class UtenteDAOTest {
	
	@Test
	public void doAddUtente() throws NoSuchAlgorithmException {
		UtenteBean ub = new UtenteBean("steve4@roger.com", getPasswordHash("america"), "cap4", "Steve", "Roger", 1, false, null);
		assertNull(UtenteDAO.getUtenteByEmail("steve4@roger.com"));
		assertNull(UtenteDAO.getUtenteByUsername("cap4"));
		UtenteDAO.doAddUtente(ub);
		assertNotNull(UtenteDAO.getUtenteByUsername("cap4"));
	}
	
	@Test
	public void getUtenteByEmail() {
		String email="steve4@roger.com";
		assertNotNull(UtenteDAO.getUtenteByEmail(email));
		email="email@nonpresente.com";
		assertNull(UtenteDAO.getUtenteByEmail(email));
	}
	
	@Test
	public void getUtenteByUsername() {
		String username="cap4";
		assertNotNull(UtenteDAO.getUtenteByUsername(username));
		username="usernonpresente";
		assertNull(UtenteDAO.getUtenteByUsername(username));
	}

	@Test
	public void getUtenteById(){	
		String id="???????";
		assertNotNull(UtenteDAO.getUtenteById(id));
		id="idnonesistente";
		assertNull(UtenteDAO.getUtenteByUsername(id));
	}

	@Test
	public void doAddModeratore() throws NoSuchAlgorithmException{
		String EMAIL = "steveMod@roger.com";
		String PASSWORD = getPasswordHash("america");
		String USERNAME = "cap0mod";
		String NOME = "Steve";
		String COGNOME = "Roger";
		
		UtenteBean ub = new UtenteBean(EMAIL, PASSWORD, USERNAME, NOME, COGNOME, 2, false, null); //2 non necessario
		assertNull(UtenteDAO.getUtenteByUsername(USERNAME));
		UtenteDAO.doAddModeratore(ub);
		assertNotNull(UtenteDAO.getUtenteByUsername(USERNAME));
	}

	@Test
	public void doGetAllModeratori() {
		
		int NUMERO_MOD = 12;
		
		assertEquals(NUMERO_MOD, UtenteDAO.doGetAllModeratori().size());
	}
	
	@Test
	public void doDeactivateUser(){
		
		String ID_UTENTE ="???????";
		
		assertEquals(false,(UtenteDAO.getUtenteById(ID_UTENTE)).isDisattivato());
		UtenteDAO.doDeactivateUser(ID_UTENTE);
		assertEquals(true,(UtenteDAO.getUtenteById(ID_UTENTE)).isDisattivato());

	}
	
	private String getPasswordHash(String password) throws NoSuchAlgorithmException{
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hashedPassword.length; i++) {
			String hex = Integer.toHexString(0xff & hashedPassword[i]);
            if(hex.length() == 1) 
            	hexString.append('0');
            
            hexString.append(hex);
		}
		return hexString.toString();					
	}
	
}
