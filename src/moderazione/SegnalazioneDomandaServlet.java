package moderazione;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneAccount.PartecipanteBean;
import gestioneDomanda.DomandaBean;
import gestioneDomanda.DomandeManager;
import util.CustomServlet;

/**
 * Servlet implementation class SegnalazioneDomandaServlet
 */
@WebServlet("/SegnalazioneDomandaServlet")
public class SegnalazioneDomandaServlet extends CustomServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(SegnalazioneDomandaServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnalazioneDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	checkPartecipante(req.getSession(), resp);
    	
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		
		String idDomanda = request.getParameter("idDomandaSegnalata");
		String idMotivazione = request.getParameter("idMotivazione");
		String commento = request.getParameter("commento");
	
		String requestURL = "/VisualizzaHome";
		
		LOGGER.info(requestURL);
		
		if(idDomanda == null) {
			
			setStringAttributeThenRedirect(
					"error",
					"L'ID della domanda non pu� essere nullo", 
					request, 
					response,
					requestURL
			);
			
			return ;
		}
		
		if(idMotivazione == null) {
			
			setStringAttributeThenRedirect(
					"error", 
					"L'ID della motivazione non pu� essere nullo",
					request, 
					response, 
					requestURL
			);
			
			return ;
		}
		
		
		DomandeManager managerDomande = new DomandeManager();
		
		DomandaBean domandaSegnalata = managerDomande.getDomandaById(idDomanda);
		
		if(domandaSegnalata == null) {
			
			setStringAttributeThenRedirect(
					"error", 
					"La domanda segnalata con id = '" + idDomanda + "' non esiste", 
					request, 
					response, 
					requestURL
			);
			
			return ;
		}
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		// TODO Usare un metodo MotivazioneDAO.getMotivazioneByID() piuttosto che String.parseInt()
		
		MotivazioneBean motivazione = new MotivazioneBean();
		motivazione.setId(Integer.parseInt(idMotivazione));
		
		Date dataSegnalazione = new Date();
		PartecipanteBean utente = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");

		try {
			
			managerSegnalazioni.creazioneSegnalazioneDomanda(
					motivazione, 
					dataSegnalazione, 
					commento, 
					domandaSegnalata,
					utente
			);
			

			
			setStringAttributeThenRedirect(
					"successo", 
					"Segnalazione inviata con successo", 
					request, 
					response, 
					requestURL
			);
			
		} catch(ServletException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			
			setStringAttributeThenRedirect(
					"error", 
					e.getMessage(), 
					request, 
					response, 
					requestURL
			);
			
			return ;
			
		}
		
	}

}
