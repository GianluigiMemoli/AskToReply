package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandeManager;
import model.MotivazioneBean;
import model.RispostaBean;
import model.RisposteManager;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class RisolviSegnalazioneDomandaServlet
 */
@WebServlet("/RisolviSegnalazioneDomandaServlet")
public class RisolviSegnalazioneDomandaServlet extends CustomServlet {
	
	private static final long serialVersionUID = 1L;
 	
	private final static Logger LOGGER = Logger.getLogger(CambiaCategorieDomandaServlet.class.getName());
	
	private final static String PATH_ELENCO_SEGNALAZIONI_DOMANDA = "VisualizzaElencoSegnalazioniDomanda";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RisolviSegnalazioneDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
    		checkModeratore(req.getSession(), resp);
    	} catch (RuntimeException e) {
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	
    	super.service(req, resp);
    	
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
		
		// Controllo se sono stati inviati parametri
		
		String idSegnalazioneDaRisolvere = request.getParameter("idSegnalazione");
		
		if(idSegnalazioneDaRisolvere == null) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"ID segnalazione domanda mancante", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
			
		}
		
		// Controllo se la segnalazione esiste ed è per 'contenuti offensivi'
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		SegnalazioneDomandaBean segnalazioneDaRisolvere = 
				managerSegnalazioni.getSegnalazioneDomanda(idSegnalazioneDaRisolvere);
		
		if(segnalazioneDaRisolvere == null) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"La segnalazione con ID '" + idSegnalazioneDaRisolvere + "' non esiste", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
			
		}
		
		if(segnalazioneDaRisolvere.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"Questa classe non può risolvere segnalazioni Off-topic", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
			
		}
		
		String idDomandaSegnalata = segnalazioneDaRisolvere.getDomandaSegnalata().getId();
		
		// Eliminazione domande
		
		DomandeManager domandeManager = new DomandeManager();
		
		domandeManager.removeDomanda(idDomandaSegnalata);
		
		// Eliminazione risposte
		
		RisposteManager risposteManager = new RisposteManager();
		
		ArrayList<RispostaBean> risposteDaEliminare = new ArrayList<RispostaBean>();
		
		int i = 0;
		
		while(RisposteManager.getRisposteByIdDomanda(idDomandaSegnalata, i).size() != 0) {
			risposteDaEliminare.addAll(RisposteManager.getRisposteByIdDomanda(idDomandaSegnalata, i));
			i++;
		}
		
		for (int j = 0; j < risposteDaEliminare.size(); j++)
			risposteManager.removeRisposta(risposteDaEliminare.get(j));
		
		// Eliminazione allegati
		
		// TODO
		
		// Risoluzione di tutte le segnalazioni (contenuti offessinvi & offtopic)
		
		managerSegnalazioni.risolviSegnalazioneDomanda(segnalazioneDaRisolvere);
			
		setStringAttributeThenRedirect(
				"messaggioDiSuccesso", 
				"Segnalazione risolta con successo", 
				request, 
				response, 
				PATH_ELENCO_SEGNALAZIONI_DOMANDA);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
