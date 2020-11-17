package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.UtenteBean;

/**
 * Servlet implementation class VisualizzaDomandaServlet
 */
@WebServlet("/VisualizzaDomandaServlet")
public class VisualizzaDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDomanda = request.getParameter("id");
		
		PrintWriter writer = response.getWriter();
		
		if(idDomanda != null) {
			
			try {
				
				DomandeManager manager = new DomandeManager();
				DomandaBean domandaVisualizzata = manager.getDomandaById(idDomanda);
				
				if(domandaVisualizzata != null) {
					
					request.setAttribute("domanda", domandaVisualizzata);
					
					File[] allegati = manager.getAllegati(domandaVisualizzata);
					request.setAttribute("allegati", allegati);
					
					// TODO Prelevare le risposte con votazioni comprese
					
					UtenteBean utente = getLoggedUser(request.getSession());
					request.setAttribute("utenteLoggato", utente);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Domanda.jsp");
					dispatcher.forward(request, response);
					
				} else {
					writer.print("Domanda con id = '" + idDomanda + "' non trovata.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				writer.print(e.getMessage());
			}
			
		} else {
			writer.print("L'ID della domanda non può essere nullo.");
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
