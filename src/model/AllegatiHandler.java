package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.Part;

public class AllegatiHandler {

	private static final Logger logger = Logger.getLogger(AllegatiHandler.class.getName());
	
	public void caricaAllegati(List<Part> allegati, String destinationFolder) throws Exception {
		
		// Ricordare di passare l'id della domanda nella destination folder
		
		if(allegati.size() > 0) {
			
			File dir = new File(destinationFolder);
			
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
		    for (Part filePart : allegati) {
		    	
		    	// TODO C'è un problema col nome del file: al suo interno è presente anche il suo path assoluto
		        
		        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		        
		        InputStream fileContent = filePart.getInputStream();
		        byte[] data = new byte[fileContent.available()];
		        fileContent.read(data);
		        
		        File file = new File(destinationFolder + File.separator + fileName);
		        OutputStream out = new FileOutputStream(file);
		        out.write(data);
		        out.close();
		        
		    }
		    
		    
		}
	}
	
	public File[] getAllegati(String folder) {
		
		// Questo if serve perché se domanda.getId() == "", restituisce tutte le directory
		
		/*
		if(domanda.getId() == "")
			return null; 
		*/
		
		File[] files = new File(folder).listFiles();
	
		if(files != null) {
			for (File file : files) {
				logger.info(file.getName());
			}
		} else {
			files = new File[0];
			logger.info("Cartella '" + folder + "' non presente.");
		}
		
		return files;
	}
	
	public ArrayList<String> convertToBase64(File[] allegati) throws IOException {
		
		ArrayList<String> allegatiBase64 = new ArrayList<String>(allegati.length);
		
		for (File file : allegati) {
			Path path = file.toPath();
			byte[] bytes = Files.readAllBytes(path);
			String encodedBytes = Base64.getEncoder().encodeToString(bytes);
			allegatiBase64.add(encodedBytes);	
		}
		
		return allegatiBase64;
		
	}
	
}
