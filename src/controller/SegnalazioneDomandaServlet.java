package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.MotivazioneBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class SegnalazioneDomandaServlet
 */
@WebServlet("/SegnalazioneDomandaServlet")
public class SegnalazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
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
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		
		String idDomanda = request.getParameter("idDomandaSegnalata");
		String idMotivazione = request.getParameter("idMotivazione");
		String commento = request.getParameter("commento");
	
		PrintWriter writer = response.getWriter();
		
		if(idDomanda != null) {
			
			if(idMotivazione != null) {
				
				DomandeManager managerDomande = new DomandeManager();
				DomandaBean domandaSegnalata = managerDomande.getDomandaById(idDomanda);
				
				if(domandaSegnalata != null) {
					
					SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
					
					// TODO Usare un metodo MotivazioneDAO.getMotivazioneByID() piuttosto che String.parseInt()
					
					MotivazioneBean motivazione = new MotivazioneBean();
					motivazione.setId(Integer.parseInt(idMotivazione));
					
					Date dataSegnalazione = new Date();
					
					managerSegnalazioni.creazioneSegnalazioneDomanda(motivazione, dataSegnalazione, commento, domandaSegnalata);
					
				} else {
					writer.print("La domanda segnalata con id = '" + idDomanda + "' non esiste.");
				}
				
			} else {
				writer.print("L'ID della motivazione non può essere nullo.");
			}
				
		} else {
			writer.print("L'ID della domanda non può essere nullo.");
		}
		
	}

}
