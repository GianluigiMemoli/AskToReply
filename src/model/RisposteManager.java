package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import com.mysql.cj.jdbc.CallableStatement;

import Exceptions.ErrorePubblicazioneRispostaException;

public class RisposteManager {
	
	static Logger log = Logger.getLogger(RisposteManager.class.getName()); //test


	public void pubblicaRisposta(String idDomanda, String corpo, List<Part> allegati, String idAutore
			, Date dataPubblicazione) throws Exception{
		

		//if //controllo se l'utente ha già risposto
		
		DBManager manager = DBManager.getInstance();
		
			String query = "SELECT * FROM risposte WHERE idDomanda = ? AND idAutore = ?;";
			//String query = "SELECT * FROM risposte;";
			PreparedStatement statement = manager.createPreparedStatement(query);
			statement.setString(1, idDomanda);
			statement.setString(2, idAutore);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				
				log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				log.info("HAI GIA' RISPOSTOOOOOOOOOO ALLA DOMANDA");
				log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				throw new ErrorePubblicazioneRispostaException("L'utente ha già risposto alla domanda!");

			}
			
			else {
				log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				log.info("BENE, HAI PPENA DATO LA TUA RISPOSTA");
				log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			/*
			 * 
			CallableStatement procedure = manager.prepareStoredProcedureCall("GetDomandeByAutoreLimit", 3);
			
			procedure.setNString(1, idUtente);
			procedure.setInt(2, start);
			procedure.setInt(3, end);
			
			ResultSet rs = procedure.executeQuery();
			
			 * 
			 * */
		
		if(corpo.trim().length() < 2) {
			throw new ErrorePubblicazioneRispostaException("Il corpo della risposta deve contenere almeno due caratteri.");
		}

		RispostaBean risposta = new RispostaBean();

		risposta.setIdAutore(idAutore);
		risposta.setCorpo(corpo);
		risposta.setIdDomanda(idDomanda);
		risposta.setDataPubblicazione(dataPubblicazione);

		RispostaDAO.addRisposta(risposta);

		//caricaAllegati(allegati, risposta);
			}
	}


	public RispostaBean getRispostaById(String idRisposta) {
		return RispostaDAO.getRispostaById(idRisposta);
	}
	
	
	//DIBENEDETTO:
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

	//DIBENEDETTO:
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

	public void removeRisposta(RispostaBean risposta) {
		RispostaDAO.removeRisposta(risposta);
	}
	
	public int getNumeroRisposte(DomandaBean domanda) {
		return RispostaDAO.countRisposteByDomandaId(domanda.getId());
	}
	
	private static Logger logger = Logger.getLogger(DomandeManager.class.getName());

	private static final String UPLOAD_PATH = "C:\\uploads\\allegati_risposte\\";

}

//da testare
