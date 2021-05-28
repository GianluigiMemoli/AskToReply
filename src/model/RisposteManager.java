package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.Part;

import com.mysql.cj.jdbc.CallableStatement;

import Exceptions.ErrorePubblicazioneDomandaException;
import Exceptions.ErrorePubblicazioneRispostaException;

public class RisposteManager {

	static Logger log = Logger.getLogger(RisposteManager.class.getName()); //test
	private static final String UPLOAD_PATH = "C:\\uploads\\allegati_risposte\\";


	public void pubblicaRisposta(String idDomanda, String corpo, List<Part> allegati, String idAutore
			,String idAutoreDomanda, Date dataPubblicazione) throws Exception{


		//controllo se l'utente tenta di rispondere ad una propria domanda

		if(idAutore.equals(idAutoreDomanda))throw new ErrorePubblicazioneRispostaException("La domanda è stata pubblicata dall'utente che sta tentando di rispondere.");


		//controllo se l'utente ha già risposto

		DBManager manager = DBManager.getInstance();

		String query = "SELECT * FROM risposte WHERE idDomanda = ? AND idAutore = ? AND isNascosta=0;";
		//String query = "SELECT * FROM risposte;";
		PreparedStatement statement = manager.createPreparedStatement(query);
		statement.setString(1, idDomanda);
		statement.setString(2, idAutore);
		ResultSet rs = statement.executeQuery();

		if(rs.next()){

			log.info("L'UTENTE HA GIA' RISPOSTO ALLA DOMANDA");
			throw new ErrorePubblicazioneRispostaException("L'utente ha già risposto alla domanda!");

		}

		else {


			if(corpo.trim().length() < 1) {
				throw new ErrorePubblicazioneRispostaException("Il corpo della risposta deve contenere almeno un carattere.");
			}

			RispostaBean risposta = new RispostaBean();

			PartecipanteBean pb = new PartecipanteBean();
			pb.setId(idAutore);
			risposta.setAutore(pb);
			risposta.setCorpo(corpo);
			DomandaBean domb = new DomandaBean();
			domb.setId(idDomanda);
			risposta.setDomanda(domb);
			risposta.setDataPubblicazione(dataPubblicazione);


			risposta=RispostaDAO.addRisposta(risposta);

			AllegatiHandler allegatiHandler = new AllegatiHandler();
			try {
				log.info("Carico allegati (Risposte Manager)");
				allegatiHandler.caricaAllegati(allegati, UPLOAD_PATH + risposta.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("errore ALLEGATI 0000");
				e.printStackTrace();
				RispostaDAO.removeRisposta(risposta);
				throw new ErrorePubblicazioneDomandaException(e.getMessage());

			}

			log.info("RISPOSTA INVIATA CON SUCCESSO");

		}
	}


	public static RispostaBean getRispostaById(String idRisposta) {

		RispostaBean rb = RispostaDAO.getRispostaById(idRisposta);

		AllegatiHandler allegatiHandler = new AllegatiHandler();
		File[] allegati = allegatiHandler.getAllegati(UPLOAD_PATH + idRisposta);

		try {
			rb.setAllegati(allegatiHandler.convertToBase64(allegati));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rb;
	}


	public static ArrayList<RispostaBean> getRisposteByIdDomanda(String idDomanda, int numPagina) throws IOException {//aggiunta: int x
		ArrayList<RispostaBean> arrayListRisposte = RispostaDAO.getRisposteByIdDomanda(idDomanda, numPagina);
		ArrayList<RispostaBean> arrayListRisposteConAllegati = new ArrayList<RispostaBean>();

		for (int counter = 0; counter < arrayListRisposte.size(); counter++) { 	
			
			arrayListRisposte.get(counter).setAutore(PartecipanteDAO.getPartecipanteByEmail(UtenteDAO.getUtenteById(arrayListRisposte.get(counter).getAutore().getId()).getEmail()));
			arrayListRisposte.get(counter).setDomanda(DomandaDAO.getDomandaById(arrayListRisposte.get(counter).getDomanda().getId()));

			
			int miPiace=0;
			int nonMiPiace=0;
			ArrayList <VotazioneBean> vb = VotazioneDAO.getVotazioniByIdRisposta(arrayListRisposte.get(counter).getId());
			arrayListRisposte.get(counter).setVoti(vb);
			if(vb!=null)for(int k=0; k<vb.size(); k++)if(vb.get(k).getValore()==1)miPiace+=1;else nonMiPiace+=1;
			arrayListRisposte.get(counter).setMiPiace(miPiace);
			arrayListRisposte.get(counter).setNonMiPiace(nonMiPiace);
			
			
			AllegatiHandler allegatiHandler = new AllegatiHandler();
			File[] allegati = allegatiHandler.getAllegati(UPLOAD_PATH + arrayListRisposte.get(counter).getId());
			arrayListRisposte.get(counter).setAllegati(allegatiHandler.convertToBase64(allegati));
			arrayListRisposteConAllegati.add(arrayListRisposte.get(counter));
		}   
		return arrayListRisposteConAllegati;
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


	public void removeRisposta(RispostaBean risposta) {
		RispostaDAO.removeRisposta(risposta);
	}

	public int getNumeroRisposte(DomandaBean domanda) {
		return RispostaDAO.countRisposteByDomandaId(domanda.getId());
	}
	
	public int getNumeroRisposteByUtente(PartecipanteBean user) {
		return RispostaDAO.getNumeroRisposteByUtente(user);
	}
	
	public ArrayList<RispostaBean> getStoricoRisposte(PartecipanteBean user, int page, int offset) throws IOException{
		int start = (page-1) * offset;
		
		
		ArrayList<RispostaBean> arrayListRisposte =  RispostaDAO.getStoricoRisposteByUtente(user, start, offset);
		ArrayList<RispostaBean> arrayListRisposteConAllegati = new ArrayList<RispostaBean>();

		for (int counter = 0; counter < arrayListRisposte.size(); counter++) { 
			
			//dal DAO
			
			DomandaBean dom = arrayListRisposte.get(counter).getDomanda();
			dom.setTitolo(DomandaDAO.getDomandaById(arrayListRisposte.get(counter).getDomanda().getId()).getTitolo());
			arrayListRisposte.get(counter).setDomanda(dom);
			
			int miPiace=0;
			int nonMiPiace=0;
			ArrayList <VotazioneBean> vb = VotazioneDAO.getVotazioniByIdRisposta(arrayListRisposte.get(counter).getId());
			arrayListRisposte.get(counter).setVoti(vb);
			if(vb!=null)for(int k=0; k<vb.size(); k++)if(vb.get(k).getValore()==1)miPiace+=1;else nonMiPiace+=1;
			arrayListRisposte.get(counter).setMiPiace(miPiace);
			arrayListRisposte.get(counter).setNonMiPiace(nonMiPiace);
			
			//
			
			AllegatiHandler allegatiHandler = new AllegatiHandler();
			File[] allegati = allegatiHandler.getAllegati(UPLOAD_PATH + arrayListRisposte.get(counter).getId());
			arrayListRisposte.get(counter).setAllegati(allegatiHandler.convertToBase64(allegati));
			arrayListRisposteConAllegati.add(arrayListRisposte.get(counter));
		}   
		return arrayListRisposteConAllegati;
	}

	public HashSet<RispostaBean> getRisposteApprezzate(UtenteBean utente){
		HashSet<RispostaBean> risposteApprezzate = new HashSet<RispostaBean>();		
		risposteApprezzate.addAll(RispostaDAO.getRisposteApprezzate(utente.getId()));
		return risposteApprezzate;
	}


	public HashSet<RispostaBean> getRisposteNonApprezzate(UtenteBean utente){
		HashSet<RispostaBean> risposteNonApprezzate = new HashSet<RispostaBean>();		
		risposteNonApprezzate.addAll(RispostaDAO.getRisposteNonApprezzate(utente.getId()));
		return risposteNonApprezzate;
	}



}