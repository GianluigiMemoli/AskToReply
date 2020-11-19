package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RicercaDomandeServlet
 */
@WebServlet("/RicercaDomandeServlet")
public class RicercaDomandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaDomandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
     * Request interface: 
     * {
     * 	"testoRicerca": "xxx", 
     * 	"categorie": [zzz, www, ...]
     * }
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoRicerca = request.getParameter("testoRicerca");
		String categoire = request.getParameterValues("categorie");
		
	}

}
