package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.PartecipanteBean;

/**
 * Servlet implementation class VisualizzaHome
 */
@WebServlet("/VisualizzaHome")
public class VisualizzaHome extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaHome() {
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
    Logger log = Logger.getAnonymousLogger();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int offset = 10;
		if(request.getParameter("page") != null) {
			log.info("not null");		
			page = Integer.parseInt(request.getParameter("page"));	
		}
		DomandeManager managerDomande = new DomandeManager();
		PartecipanteBean utente = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		int start = (page - 1) * offset;
		int end = page * offset;
		System.out.println(start);
		System.out.println(end);
		ArrayList<DomandaBean> domande = managerDomande.getDomandePertinenti(utente, start, end);
		System.out.println(domande);
		request.setAttribute("domande", domande);		
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
