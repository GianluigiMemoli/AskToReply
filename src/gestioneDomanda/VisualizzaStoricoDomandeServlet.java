package gestioneDomanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneAccount.PartecipanteBean;
import gestioneAccount.UtenteBean;
import gestioneRisposta.RisposteManager;
import util.CustomServlet;

/**
 * Servlet implementation class VisualizzaStoricoDomandeServlet
 */
@WebServlet("/VisualizzaStoricoDomandeServlet")
public class VisualizzaStoricoDomandeServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaStoricoDomandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
    		checkPartecipante(req.getSession(), resp);
    	} catch(RuntimeException e) {
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	
    	super.service(req, resp);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int domandePerPagina = 10;
		int currentPage = 1;
	
		if(request.getParameter("p") != null) {
			
			try {
				currentPage = Integer.parseInt(request.getParameter("p"));
			} catch(NumberFormatException e) {
				currentPage = 1;
			}
			
		}
				
		DomandeManager manager = new DomandeManager();
		
		int numeroDomande = manager.getNumeroDomandeByAutore(getLoggedUser(request.getSession()).getId());
		
		
		int totalPages = (int) Math.ceil((double) numeroDomande/domandePerPagina);
		
		// TODO Cambiare in partecipanteBean
		
		PartecipanteBean utente = (PartecipanteBean) getLoggedUser(request.getSession());
		
		/*ArrayList<DomandaBean> domande = 
				(ArrayList<DomandaBean>) manager.getDomandeByAutore(
			utente.getId(), 
			(currentPage - 1) * domandePerPagina,
			currentPage * domandePerPagina
		);
		*/
		int startIndex = (currentPage - 1) * domandePerPagina;
		int endIndex = currentPage * domandePerPagina;
		if(endIndex > numeroDomande) {
			endIndex = numeroDomande;
		}
		ArrayList<DomandaBean> domande =  new ArrayList<DomandaBean>( 
				utente.getDomandeUtente().subList(startIndex,endIndex
				));
		
		//test
		HashMap<String, Integer> numeroRisposte = new HashMap<String, Integer>();
		RisposteManager risposteManager = new RisposteManager();
		for(DomandaBean domanda : domande) {
			numeroRisposte.put(domanda.getId(), risposteManager.getNumeroRisposte(domanda));
		}
		request.setAttribute("numeroRisposte", numeroRisposte);
		//test

		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("domande", domande);
		
		request.getRequestDispatcher("VisualizzaStoricoDomande.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
