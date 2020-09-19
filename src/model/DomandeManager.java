package model;

import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import Exceptions.ErrorePubblicazioneDomandaException;

public class DomandeManager {

	public void pubblicaDomanda (
		String idAutore,
		String titolo,
		String corpo,
		Date dataPubblicazione,
		String[] idCategorie,
		List<Part> allegati) 
				throws Exception
	{
		int lunghezzaTitolo = titolo.trim().length(); 
		
		logger.info("Lunghezza titolo = " + lunghezzaTitolo);
		
		if(lunghezzaTitolo < 2) {
			throw new ErrorePubblicazioneDomandaException("Il titolo deve contenere almeno due caratteri.");
		}
		
		int lunghezzaCorpo = corpo.trim().length(), numeroAllegati = allegati.size();
		
		logger.info("Lunghezza corpo = " + lunghezzaCorpo);
		logger.info("Numero di allegati = " + numeroAllegati);
		
		if(lunghezzaCorpo == 0 &&  numeroAllegati == 0) {
			throw new ErrorePubblicazioneDomandaException("Il corpo e gli allegati di una domanda non possono essere entrambi nulli.");
		}
		
		int numeroCategorie = idCategorie.length;
		
		logger.info("Numero di categorie = " + numeroCategorie);
		
		if(numeroCategorie == 0) {
			throw new ErrorePubblicazioneDomandaException("Bisogna scegliere almeno un categoria per poter pubblicare una domanda");
		}
		
		//TODO Controllo su estensione e dimensione degli allegati ci vuole solo per le certificazioni e non anche questo
		
		// Inserimento domanda all'interno della tabella domande
		
		DomandaBean domanda = new DomandaBean();
		
		domanda.setIdAutore(idAutore);
		domanda.setTitolo(titolo);
		domanda.setCorpo(corpo);
		domanda.setDataPubblicazione(dataPubblicazione);
		domanda.setArchiviata(false);
		
		DomandaDAO.addDomanda(domanda);
		
		// Inserimento categorie all'interno della tabella categoriedomande
	
		CategoriaBean categoria = new CategoriaBean();
		
		for (String idCategoria : idCategorie) {		
			categoria.setId(idCategoria);
			DomandaDAO.addCategoriaDomanda(domanda, categoria);
		}
		
		// Inserimento allegati
		
		caricaAllegati(allegati, domanda);
		
	}
	
	private void caricaAllegati(List<Part> allegati, DomandaBean domanda) throws Exception {
		if(allegati.size() > 0) {
			
			String destinationFolder =  UPLOAD_PATH + domanda.getId();
			
			File dir = new File(destinationFolder);
			
			if(!dir.exists())
				dir.mkdirs();
			
			OutputStream out = null;
			
		    for (Part filePart : allegati) {
		        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		        InputStream fileContent = filePart.getInputStream();
		        
		        // TODO Risolvere il problema del nome del file (contiene anche il path)
		        
		        byte[] data = new byte[fileContent.available()];
		        
		        fileContent.read(data);
		        
		        File file = new File(destinationFolder + "\\" + fileName);
		        
		        out = new FileOutputStream(file);
		        out.write(data);
		    }
		    
		    out.close();
		}
	}
	
	public File[] getAllegati(DomandaBean domanda) {
		
		/*
			Questo if serve perché se domanda.getId() == "", restituisce tutte le directory
		*/
		if(domanda.getId() == "")
			return null; 
		
		File[] files = new File("C:\\uploads\\allegati_domande\\" + domanda.getId()).listFiles();
		
		/*
		if(files != null)
			for (File file : files)
				logger.info(file.getName());
		else
			logger.info("Cartella '" + domanda.getId() + "' non presente.");
		*/
		
		return files;
	}
	
	//
	
	private static Logger logger = Logger.getLogger(DomandeManager.class.getName());
	
	private static final String UPLOAD_PATH = "C:\\uploads\\allegati_domande\\";
}
