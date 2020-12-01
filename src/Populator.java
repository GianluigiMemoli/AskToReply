import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.Part;

import Exceptions.CampiNonConformiException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;
import model.AccountManager;
import model.CategoriaBean;
import model.CategoriaDAO;
import model.DomandeManager;
import model.PartecipanteBean;
import model.PartecipanteDAO;

public class Populator {
	public static String generaStringa(int length) {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = length;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    return generatedString;
	}
	public static String makeEmail() {
		String name = generaStringa(5);
		String domain = generaStringa(4);
		return name + "@" + domain + ".com";
	}
	public static String[] getRandomInteressi (int num) {
		ArrayList<CategoriaBean> availableCategorie =  CategoriaDAO.getAll();
		String [] interessi = new String[num];
		Set<String> chosen = new HashSet<String>(num);
		Random rand = new Random();
		int currentInsert = 0;
		while(chosen.size() != num) {
			int nextChoice = rand.nextInt(availableCategorie.size());
			if(!chosen.contains(availableCategorie.get(nextChoice).getNome())) {
				interessi[currentInsert] = availableCategorie.get(nextChoice).getNome();
				chosen.add(availableCategorie.get(nextChoice).getNome());
				System.out.println("add: " + interessi[currentInsert]);
				currentInsert++;
			}
		}
		return interessi;
	}
	public static String[] getRandomCategorie (int num) {
		ArrayList<CategoriaBean> availableCategorie =  CategoriaDAO.getAll();
		String [] interessi = new String[num];
		Set<String> chosen = new HashSet<String>(num);
		Random rand = new Random();
		int currentInsert = 0;
		while(chosen.size() != num) {
			int nextChoice = rand.nextInt(availableCategorie.size());
			if(!chosen.contains(availableCategorie.get(nextChoice).getNome())) {
				interessi[currentInsert] = ( availableCategorie.get(nextChoice).getId());
				chosen.add(availableCategorie.get(nextChoice).getNome());
				System.out.println("add: " + interessi[currentInsert]);
				currentInsert++;
			}
		}
		return interessi;
	}
	public static void createUsers(int numUsers) {
		AccountManager managerAccount = new AccountManager();						
		for(int i = 0; i < numUsers; i++) {
			String nome = generaStringa(5);
			String cognome = generaStringa(5);
			String username = generaStringa(5);
			String email = makeEmail();
			String password = "password";
			String[] interessi = getRandomInteressi(2);			
			try {
				managerAccount.RegisterUser(nome, cognome, username, email, password, interessi);
			} catch (NoSuchAlgorithmException | CampiNonConformiException | EmailPresenteException
					| UsernamePresenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void populateUsers() {
		createUsers(10);
	}
	
	public static void populateDomande(int numDomande) {
		DomandeManager domandaManager = new DomandeManager();
		PartecipanteBean autore = PartecipanteDAO.getPartecipanteByEmail("SMROSSWASSAI@GMAIL.COM");
		for(int i=0; i < numDomande; i++) {
			String titolo = generaStringa(10);
			String corpo = generaStringa(30);
			Date dataPubblicazione = new Date();
			String idCategorie[] = getRandomCategorie(1);
			List<Part> parts = new ArrayList<Part>();
			try {
				domandaManager.pubblicaDomanda(autore, titolo, corpo, dataPubblicazione, idCategorie, parts);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
