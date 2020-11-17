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

/**
 * Servlet implementation class RicercaServlet
 */
@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO
		
		String testo = request.getParameter("testo");
		String archiviazione = request.getParameter("archiviata");
		String[] categorie = request.getParameterValues("categorie");
		
		Boolean isArchiviata;
		
		if(archiviazione.compareTo("archiviate") == 0)
			isArchiviata = true;
		else if(archiviazione.compareTo("non archiviate") == 0)
			isArchiviata = false;
		else 
			isArchiviata = null;
		
		DomandeManager manager = new DomandeManager();
		
		try {
			
			ArrayList<DomandaBean> domande = manager.ricerca(testo, categorie, isArchiviata);
			
			request.setAttribute("domandeCercate", domande);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
		
		request.getRequestDispatcher("WEB-INF/ElencoRisultatoRicerca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
