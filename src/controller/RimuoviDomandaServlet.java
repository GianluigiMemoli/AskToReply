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
import model.UtenteBean;

/**
 * Servlet implementation class RimuoviDomandaServlet
 */
@WebServlet("/RimuoviDomandaServlet")
public class RimuoviDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviDomandaServlet() {
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
		
		String idDomanda = request.getParameter("idDomanda");
		
		PrintWriter writer = response.getWriter();
		
		if(idDomanda != null) {
						
			DomandeManager domandeManager = new DomandeManager();
			
			DomandaBean domanda = domandeManager.getDomandaById(idDomanda);
			
			if(domanda != null) {
				
				UtenteBean utenteLoggato = getLoggedUser(request.getSession());
				
				if(domanda.getAutore().getId().compareTo(utenteLoggato.getId()) == 0) {
					domandeManager.removeDomanda(domanda.getId());
				} else {
					writer.print("Rimozione fallita. Non sei l'autore della domanda");
				}
				
			} else {
				writer.print("Domanda con id = '" + idDomanda + "' non trovata.");
			}
			
		} else {
			writer.print("E' necessario inserire l'ID di una domanda.");
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
