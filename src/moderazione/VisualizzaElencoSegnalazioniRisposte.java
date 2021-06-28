package moderazione;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CustomServlet;

/**
 * Servlet implementation class VisualizzaElencoSegnalazioniRisposte
 */
@WebServlet("/VisualizzaElencoSegnalazioniRisposte")
public class VisualizzaElencoSegnalazioniRisposte extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaElencoSegnalazioniRisposte() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkModeratore(req.getSession(), resp);
    	} catch(Exception exc) {
    		req.getRequestDispatcher("/accesso").forward(req, resp);    	
    	}
    	super.service(req, resp);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SegnalazioniManager manager = new SegnalazioniManager();
		
		ArrayList<SegnalazioneRispostaBean> segnalazioniRisposte = manager.getAllSegnalazioniRisposta();
		
		request.setAttribute("segnalazionirisposte", segnalazioniRisposte);
		
		request.getRequestDispatcher("ElencoSegnalazioniRisposte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}