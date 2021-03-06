package moderazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneRisposta.RispostaBean;
import gestioneRisposta.RispostaDAO;
import util.CustomServlet;

/**
 * Servlet implementation class gestioneSegnalazioneRispostaServlet
 */
@WebServlet("/gestioneSegnalazioneRispostaServlet")
public class GestioneSegnalazioneRispostaServlet extends CustomServlet {
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneSegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkModeratore(req.getSession(), resp);
    	} catch(Exception exc) {    		    	
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	super.service(req, resp);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    if (request.getParameter("approva") != null) {
	    	
			log.info("segnalazione "+request.getParameter("idSegnalazione")+" approvata");
			log.info("l'id della risposta segnalata � "+request.getParameter("idRisposta"));
			
			RispostaBean rb = new RispostaBean();
			rb.setId(request.getParameter("idRisposta"));
			RispostaDAO.removeRisposta(rb);
			SegnalazioneRispostaBean  srb = new SegnalazioneRispostaBean();
			srb.setIdSegnalazione(request.getParameter("idSegnalazione"));
			srb.setStato(SegnalazioneBean.APPROVATA);
			srb.setRispostaSegnalata(rb);
			SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(srb);
			
			//request.getRequestDispatcher("ElencoSegnalazioniRisposte.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/ElencoSegnalazioniRisposte.jsp");

			SegnalazioniManager manager = new SegnalazioniManager();
			ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = manager.getAllSegnalazioniRisposta();
			request.setAttribute("segnalazionirisposte", segnalazioniRisposte);
			request.getRequestDispatcher("ElencoSegnalazioniRisposte.jsp").forward(request, response);

	    } else if (request.getParameter("ignora") != null) {
			log.info("segnalazione "+request.getParameter("idSegnalazione")+" ignorata");
			
			SegnalazioneRispostaBean  srb = new SegnalazioneRispostaBean();
			srb.setIdSegnalazione(request.getParameter("idSegnalazione"));
			srb.setStato(SegnalazioneBean.DECLINATA);
			SegnalazioneRispostaDAO.updateStatoSegnalazioneRisposta(srb);
			
			//response.sendRedirect(request.getContextPath() + "/ElencoSegnalazioniRisposte.jsp");

			//request.getRequestDispatcher("ElencoSegnalazioniRisposte.jsp").forward(request, response);
			
			SegnalazioniManager manager = new SegnalazioniManager();
			ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = manager.getAllSegnalazioniRisposta();
			request.setAttribute("segnalazionirisposte", segnalazioniRisposte);
			request.getRequestDispatcher("ElencoSegnalazioniRisposte.jsp").forward(request, response);
			
	    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
