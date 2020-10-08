package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import Exceptions.ErrorePubblicazioneRispostaException;

public class RisposteManager {

	public void pubblicaRisposta(String idDomanda, String corpo, List<Part> allegati, String idAutore,
			Date dataPubblicazione) throws Exception{
		if(corpo.trim().length() < 2) {
			throw new ErrorePubblicazioneRispostaException("Il corpo della risposta deve contenere almeno due caratteri.");
		}
		
		RispostaBean risposta = new RispostaBean();
		
		risposta.setIdAutore(idAutore);
		risposta.setCorpo(corpo);
		risposta.setIdDomanda(idDomanda);
		risposta.setDataPubblicazione(dataPubblicazione);

		RispostaDAO.addRisposta(risposta);
		
		caricaAllegati(allegati, risposta);
	}
	
	
		
	//DIBENE:
	private void caricaAllegati(List<Part> allegati, RispostaBean risposta) throws Exception {
		if(allegati.size() > 0) {
			
			String destinationFolder =  UPLOAD_PATH + risposta.getId();
			
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
	
	//DIBENE:
	public File[] getAllegati(RispostaBean risposta) {
		
		/*
			Questo if serve perché se domanda.getId() == "", restituisce tutte le directory
		*/
		if(risposta.getId() == "")
			return null; 
		
		File[] files = new File("C:\\uploads\\allegati_risposte\\" + risposta.getId()).listFiles();
		
		/*
		if(files != null)
			for (File file : files)
				logger.info(file.getName());
		else
			logger.info("Cartella '" + domanda.getId() + "' non presente.");
		*/
		
		return files;
	}
	
	private static Logger logger = Logger.getLogger(DomandeManager.class.getName());
	
	private static final String UPLOAD_PATH = "C:\\uploads\\allegati_risposte\\";
	
}

//da testare
