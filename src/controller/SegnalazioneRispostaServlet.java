package controller;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MotivazioneBean;
import model.PartecipanteBean;
import model.RispostaBean;
import model.SegnalazioneRispostaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class SegnalazioneRispostaServlet
 */
@WebServlet("/SegnalazioneRispostaServlet")
public class SegnalazioneRispostaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SegnalazioneRispostaServlet.class.getName()); //test

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			checkPartecipante(req.getSession(), resp);
		} catch(RuntimeException exc) {
			req.getRequestDispatcher("/accesso").forward(req, resp);
		}
		super.service(req, resp);  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		idRisposta = request.getParameter("idRisp");
		
		if(idRisposta != null) {
			
			if(request.getParameter("idMotivazione") != null) {
			
				idMotivazione = Integer.parseInt(request.getParameter("idMotivazione"));
				commento = request.getParameter("commento");
				stato=1;
				dataSegnalazione = new Date();		
				SegnalazioneRispostaBean sr=new SegnalazioneRispostaBean();
				RispostaBean rb = new RispostaBean();
				rb.setId(idRisposta);
				sr.setRispostaSegnalata(rb);
				sr.setDataSegnalazione(dataSegnalazione);
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setId(idMotivazione);
				sr.setMotivazione(motivazione);
				sr.setStato(stato);
				sr.setCommento(commento);
				PartecipanteBean utente = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
				sr.setUtente(utente);
				SegnalazioniManager sm = new SegnalazioniManager();
				
				try {
					sm.creazioneSegnalazioneRisposta(sr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

			} else {
				log.info("Errore: Id motivazione Null");		
				request.setAttribute("errore", "Segnalazione non effettuata");
				response.setStatus(401);
			}
					
		} else {
			log.info("Errore: Id risposta Null");		
			request.setAttribute("errore", "Segnalazione non effettuata");
			response.setStatus(401);
		}

		request.getRequestDispatcher("VisualizzaDomandaServlet?id="+request.getParameter("idDomanda")).forward(request, response);
		
	}
	
	private String idRisposta;
	private int idMotivazione;
	private String commento;
	private int stato;
	private Date dataSegnalazione;
	
}
