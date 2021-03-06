package gestioneDomanda;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CustomServlet;

/**
 * Servlet implementation class FormPubblicazioneDomanda
 */
@WebServlet("/VisualizzaFormPubblicazioneDomandaServlet")
public class VisualizzaFormPubblicazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaFormPubblicazioneDomandaServlet() {
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
		CategorieManager manager = new CategorieManager();
		request.setAttribute("categorie", manager.getAll());
		request.getRequestDispatcher("FormPubblicazioneDomanda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
