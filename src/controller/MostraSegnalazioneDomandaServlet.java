package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class MostraSegnalazioneDomandaServlet
 */
@WebServlet("/MostraSegnalazioneDomandaServlet")
public class MostraSegnalazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraSegnalazioneDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	checkModeratore(req.getSession(), resp);
    	
    	super.service(req, resp);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idSegnalazione = request.getParameter("idSegnalazione");
		
		PrintWriter out = response.getWriter();
		
		if(idSegnalazione != null) {
			
			SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
			SegnalazioneDomandaBean segnalazione = managerSegnalazioni.getSegnalazioneDomanda(idSegnalazione);
			
			if(segnalazione != null) {
				
				DomandeManager managerDomande = new DomandeManager();
				
				DomandaBean domandaSegnalata = managerDomande.getDomandaById(segnalazione.getId());
				segnalazione.setDomandaSegnalata(domandaSegnalata);
				
				request.setAttribute("segnalazione", segnalazione);
				
				request.getRequestDispatcher("/SegnalazioneDomanda.jsp").forward(request, response);;
				
			} else {
				out.print("La segnalazione con ID " + idSegnalazione + " non esiste.");
			}
				
		} else {
			out.print("ID segnalazione mancante.");
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
