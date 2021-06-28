package gestioneDomanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneAccount.PartecipanteBean;
import gestioneRisposta.RispostaBean;
import gestioneRisposta.RispostaDAO;
import gestioneRisposta.RisposteManager;
import moderazione.MotivazioniManager;
import util.CustomServlet;

/**
 * Servlet implementation class VisualizzaHome
 */
@WebServlet("/VisualizzaHome")
public class VisualizzaHome extends CustomServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaHome() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkPartecipante(req.getSession(), resp);
    	} catch(Exception exc) {    		    	
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	super.service(req, resp);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    Logger log = Logger.getAnonymousLogger();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int offset = 10;
		if(request.getParameter("page") != null) {			
			page = Integer.parseInt(request.getParameter("page"));	
		}
		DomandeManager managerDomande = new DomandeManager();
		PartecipanteBean utente = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		int start = (page - 1) * offset;		
		ArrayList<DomandaBean> domande = managerDomande.getDomandePertinenti(utente, start, offset);
		request.setAttribute("domande", domande);
		int amountDomandePertinenti = managerDomande.getNumOfDomandePertinenti(utente);
		
		HashMap<String, Integer> numeroRisposte = new HashMap<String, Integer>();
		RisposteManager risposteManager = new RisposteManager();
		for(DomandaBean domanda : domande) {
			numeroRisposte.put(domanda.getId(), risposteManager.getNumeroRisposte(domanda));
			//allegati?		
		}
		ArrayList<DomandaBean> domandeRisposte = new ArrayList<DomandaBean>();
		for(RispostaBean rispostaUtente : utente.getRisposteUtente()) {
			domandeRisposte.add(rispostaUtente.getDomanda());
		}
		MotivazioniManager managerMotivazioni = new MotivazioniManager();
		request.setAttribute("motivazioni", managerMotivazioni.getAll());
		request.setAttribute("numeroRisposte", numeroRisposte);
		request.setAttribute("domandeRisposte", domandeRisposte);
		//request.setAttribute("domandeRisposte", managerDomande.getDomandeRisposte(utente));
		boolean hasNext = start + offset < amountDomandePertinenti; 
		log.info("start: " + start); 
		log.info("hasNext: " + hasNext); 
		request.setAttribute("hasNext", hasNext);   
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
