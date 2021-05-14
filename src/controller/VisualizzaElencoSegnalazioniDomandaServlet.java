package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategorieManager;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class ElencoSegnalazioniDomandaServlet
 */
@WebServlet("/VisualizzaElencoSegnalazioniDomanda")
public class VisualizzaElencoSegnalazioniDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaElencoSegnalazioniDomandaServlet() {
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
		
		int segnalazioniPerPagina = 5;
		int paginaCorrente = 1;
		
		String pagina = request.getParameter("p");
		
		if(pagina != null) {
			try {
				paginaCorrente = Integer.parseInt(pagina);
			} catch (NumberFormatException e) {
				paginaCorrente = 1;
			}
		}
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		int numeroSegnalazioniDomanda = managerSegnalazioni.getNumeroSegnalazioniDomanda();
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = 
				managerSegnalazioni.getSegnalazioniDomanda(
						(paginaCorrente - 1) * segnalazioniPerPagina, 
						paginaCorrente * segnalazioniPerPagina
					);
		
		System.out.println(segnalazioni.size());
		
		int pagineTotali = (int) Math.ceil((double) numeroSegnalazioniDomanda/segnalazioniPerPagina);
		
		CategorieManager categorieManager = new CategorieManager();
		
		request.setAttribute("segnalazioniDomanda", segnalazioni);
		request.setAttribute("categorie", categorieManager.getAll());
		request.setAttribute("paginaCorrente", paginaCorrente);
		request.setAttribute("pagineTotali", pagineTotali);
		
		request.getRequestDispatcher("/ElencoSegnalazioniDomanda.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
