package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MotivazioneDAO;

/**
 * Servlet implementation class VisualizzaFormSegnalazioneDomandaServlet
 */
@WebServlet("/VisualizzaFormSegnalazioneDomandaServlet")
public class VisualizzaFormSegnalazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaFormSegnalazioneDomandaServlet() {
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
		
		String idDomanda = request.getParameter("idDomanda");
		
		if(idDomanda != null) {
			
			request.setAttribute("idDomanda", idDomanda);
			request.setAttribute("motivazioni", MotivazioneDAO.getAll());
			
			request.getRequestDispatcher("WEB-INF/FormSegnalazioneDomanda.jsp").forward(request, response);
		
		} else {
			response.getWriter().print("L'ID della domanda non può essere nullo.");
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
