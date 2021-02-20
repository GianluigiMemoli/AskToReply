package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.UtenteBean;

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
    	
    	checkPartecipante(req.getSession(), resp);
    	
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
		
		UtenteBean utente = getLoggedUser(request.getSession());
		
		ArrayList<DomandaBean> domande = 
				manager.getDomandeByAutore(
					utente.getId(), 
					(currentPage - 1) * domandePerPagina,
					currentPage * domandePerPagina
				);
		
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
