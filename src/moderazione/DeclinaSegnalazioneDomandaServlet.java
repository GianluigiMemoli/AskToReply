package moderazione;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CustomServlet;

/**
 * Servlet implementation class DeclinaSegnalazioneServlet
 */
@WebServlet("/DeclinaSegnalazioneDomandaServlet")
public class DeclinaSegnalazioneDomandaServlet extends CustomServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = Logger.getLogger(CambiaCategorieDomandaServlet.class.getName());
	
	private final static String PATH_ELENCO_SEGNALAZIONI_DOMANDA = "VisualizzaElencoSegnalazioniDomanda";

    /**
     * Default constructor. 
     */
    public DeclinaSegnalazioneDomandaServlet() {
        // TODO Auto-generated constructor stub
    }
    
    private void setStringAttributeThenRedirect(
    		String nomeAttributo, 
    		String messaggioAttributo, 
    		HttpServletRequest request,
    		HttpServletResponse response, 
    		String path) throws ServletException, IOException {
    	
    	LOGGER.info(messaggioAttributo);
    	request.setAttribute(nomeAttributo, messaggioAttributo);
    	request.getRequestDispatcher(path).forward(request, response);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idSegnalazioneDaDeclinare = request.getParameter("idSegnalazione");
		
		if(idSegnalazioneDaDeclinare == null) {
			
			setStringAttributeThenRedirect(
					"messaggioDiErrore", 
					"Parametro ID segnalazione mancante", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
			
		}
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		SegnalazioneDomandaBean segnalazioneDaDeclinare = 
				managerSegnalazioni.getSegnalazioneDomanda(idSegnalazioneDaDeclinare);
		
		if(segnalazioneDaDeclinare == null) {

			setStringAttributeThenRedirect(
					"messaggioDiErrore", 
					"Segnalazione con ID '" + idSegnalazioneDaDeclinare + "' inesistente", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
			
		}
		
		managerSegnalazioni.declinaSegnalazioneDomanda(segnalazioneDaDeclinare);
		
		setStringAttributeThenRedirect(
				"messaggioDiSuccesso", 
				"Segnalazione declinata con successo", 
				request, 
				response, 
				PATH_ELENCO_SEGNALAZIONI_DOMANDA);
		
	}

}
