package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.MotivazioneBean;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;
import model.CategoriaBean;

/**
 * Servlet implementation class CambiaCategorieDomandaServlet
 */
@WebServlet("/CambiaCategorieDomandaServlet")
public class CambiaCategorieDomandaServlet extends CustomServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOGGER = Logger.getLogger(CambiaCategorieDomandaServlet.class.getName());
	
	private final static String PATH_ELENCO_SEGNALAZIONI_DOMANDA = "VisualizzaElencoSegnalazioniDomanda";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaCategorieDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
    		checkModeratore(req.getSession(), resp);
		} catch (RuntimeException e) {
			LOGGER.info("L'utente non è autenticato ma se lo è, non è un moderatore");
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
		
		String idSegnalazioneDaRisolvere = request.getParameter("idSegnalazione");
		String[] idNuoveCategorie = request.getParameterValues("categorieDomanda");
		
		if(idSegnalazioneDaRisolvere == null || idNuoveCategorie == null || idNuoveCategorie.length == 0) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"Sono stati compiuti errori durante l'invio dei parametri", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			
			return ;
			
		}
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		SegnalazioneDomandaBean segnalazioneDaRisolvere = managerSegnalazioni.getSegnalazioneDomanda(idSegnalazioneDaRisolvere);
		
		if(segnalazioneDaRisolvere == null) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"Segnalazione con ID '" + idSegnalazioneDaRisolvere + "' inesistente", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
		}
		
		if(segnalazioneDaRisolvere.getMotivazione().getId() != MotivazioneBean.OFFTOPIC) {
			
			setStringAttributeThenRedirect(
					"errore", 
					"La segnalazione con ID '" + idSegnalazioneDaRisolvere + "' non è offtopic", 
					request, 
					response, 
					PATH_ELENCO_SEGNALAZIONI_DOMANDA);
			
			return ;
		}
		
		// Aggiornamento categorie
		
		DomandeManager managerDomande = new DomandeManager();
		
		String idDomandaSegnalata = segnalazioneDaRisolvere.getDomandaSegnalata().getId();
		
		DomandaBean domandaSegnalata = managerDomande.getDomandaById(idDomandaSegnalata);
		
		ArrayList<CategoriaBean> nuoveCategorie = new ArrayList<CategoriaBean>();
		
		for(int i = 0; i < idNuoveCategorie.length; i++) {
			CategoriaBean categoria = new CategoriaBean();
			categoria.setId(idNuoveCategorie[i]);
			nuoveCategorie.add(categoria);
		}
		
		domandaSegnalata.setCategorie(nuoveCategorie);
		
		managerDomande.updateCategorieDomanda(domandaSegnalata);
		
		// Risoluzione di TUTTE le segnalazioni off-topic
		
		managerSegnalazioni.risolviSegnalazioneDomanda(segnalazioneDaRisolvere);
			
		setStringAttributeThenRedirect(
				"messaggioDiSuccesso", 
				"Segnalazione risolta con successo", 
				request, 
				response, 
				PATH_ELENCO_SEGNALAZIONI_DOMANDA
			);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
