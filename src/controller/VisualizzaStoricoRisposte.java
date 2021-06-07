package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.PartecipanteBean;
import model.RispostaBean;
import model.RispostaDAO;
import model.RisposteManager;

/**
 * Servlet implementation class VisualizzaStoricoRisposte
 */
@WebServlet("/VisualizzaStoricoRisposte")
public class VisualizzaStoricoRisposte extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaStoricoRisposte() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkPartecipante(req.getSession(), resp);
    	} catch(Exception exc) {    		    	
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	super.service(req, resp);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    final int OFFSET = 10;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PartecipanteBean currUser = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		RisposteManager risposteManager = new RisposteManager();
		
		int page = 1;
		if(request.getParameter("page") != null) {					
			page = Integer.parseInt(request.getParameter("page"));	
		}
		
		int numeroRisposte = risposteManager.getNumeroRisposteByUtente(currUser);
		int startIndex = (page - 1) * OFFSET;
		int endIndex = page * OFFSET;
		if(endIndex > numeroRisposte) {
			endIndex = numeroRisposte;
		}
		ArrayList<RispostaBean> storicoRisposte =  new ArrayList<RispostaBean>( 
				currUser.getRisposteUtente().subList(startIndex,endIndex
				));
		 		
		 		
						
		boolean hasNext = (page-1)*OFFSET + OFFSET < numeroRisposte; 
		request.setAttribute("page", page);
		request.setAttribute("hasNext", hasNext);
		request.setAttribute("storicoRisposte", storicoRisposte);
		request.getRequestDispatcher("StoricoRisposte.jsp").forward(request, response);
		
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
