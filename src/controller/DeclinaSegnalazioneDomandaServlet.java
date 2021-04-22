package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SegnalazioneBean;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class DeclinaSegnalazioneServlet
 */
@WebServlet("/DeclinaSegnalazioneDomandaServlet")
public class DeclinaSegnalazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeclinaSegnalazioneDomandaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idSegnalazione = request.getParameter("idSegnalazione");
		
		PrintWriter out = response.getWriter();
		
		if(idSegnalazione != null) {
			
			SegnalazioniManager manager = new SegnalazioniManager();
			
			SegnalazioneDomandaBean segnalazione = manager.getSegnalazioneDomanda(idSegnalazione);
			
			if(segnalazione != null) {
				
				manager.declinaSegnalazioneDomanda(segnalazione);
				
				out.print("Segnalazone domanda con ID '" + idSegnalazione + "' declinata.");
				
			} else {
				out.print("Non esiste una segnalazione per una domanda con ID '" + idSegnalazione + "'.");
			}
			
		} else {
			out.print("ID segnalazione mancante.");
		}
		
	}

}
