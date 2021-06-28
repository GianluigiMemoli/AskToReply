package gestioneDomanda;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String[] archiviazione = request.getParameterValues("archiviazione");
		String[] categorieDomanda = request.getParameterValues("categorie");
		
		if(testo != null)
			if(testo.compareTo("") == 0)
				testo = null;
		
		Boolean isArchiviata = null;
		
		if(archiviazione != null) {
			
			if(archiviazione.length == 0 || archiviazione.length == 2)
				isArchiviata = null;
			else if(archiviazione.length == 1)
				if(archiviazione[0].compareTo("archiviate") == 0)
					isArchiviata = true;
				else if(archiviazione[0].compareTo("non archiviate") == 0)
					isArchiviata = false;					
		}
		
		DomandeManager manager = new DomandeManager();
		
		try {
			
			ArrayList<DomandaBean> domande = manager.ricerca(testo, categorieDomanda, isArchiviata);
			
			request.setAttribute("risultatoRicerca", domande);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
		}
		
		CategorieManager categorieManager = new CategorieManager();
		ArrayList<CategoriaBean> categorie = categorieManager.getAll();
		request.setAttribute("categorie", categorie);
		
		request.getRequestDispatcher("ElencoRisultatoRicerca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
