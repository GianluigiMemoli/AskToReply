package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MotivazioneBean;
import model.RispostaBean;
import model.RisposteManager;
import model.SegnalazioneRispostaBean;
import model.SegnalazioneRispostaDAO;
import model.SegnalazioniManager;

/**
 * Servlet implementation class RisolviSegnalazioneRispostaServlet
 */
@WebServlet("/RisolviSegnalazioneRispostaServlet")
public class RisolviSegnalazioneRispostaServlet extends HttpServlet {
	
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RisolviSegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    if (request.getParameter("approva") != null) {
			log.info("segnalazione approvata");

	    } else if (request.getParameter("ignora") != null) {
			log.info("segnalazione ignorata");
	    }
		
		/*String idSegnalazione = request.getParameter("idSegnalazione");
		
		PrintWriter out = response.getWriter();
		
		if(idSegnalazione == null) {out.print("Errore! id segnalazione risposta mancante."); return;}

			SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
			
			SegnalazioneRispostaBean segnalazione = managerSegnalazioni.getSegnalazioneRisposta(idSegnalazione);
			
			if(segnalazione == null) {out.print("Errore! id segnalazione risposta non trovata."); return;}
				
				RisposteManager managerRispo = new RisposteManager();
				
				if(segnalazione.getIdMotivazione() == MotivazioneBean.CONTENUTI_OFFENSIVI) {
									
					RispostaBean rb= new RispostaBean();
					rb.setId(segnalazione.getRispostaSegnalata().getId());
					managerRispo.removeRisposta(rb);
					managerSegnalazioni.risolviSegnalazioneRisposta(segnalazione);
					out.print("Segnalazione risolta con successo");
					
				} else {
					out.print("La motivazione della segnalazione NON è 'Contenuto Offensivo'.");
				}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
