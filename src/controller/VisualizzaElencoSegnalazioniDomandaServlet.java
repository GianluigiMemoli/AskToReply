package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class ElencoSegnalazioniDomandaServlet
 */
@WebServlet("/ElencoSegnalazioniDomandaServlet")
public class ElencoSegnalazioniDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElencoSegnalazioniDomandaServlet() {
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
		
		SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
		
		ArrayList<SegnalazioneDomandaBean> segnalazioni = managerSegnalazioni.getAllSegnalazioniDomanda();
		
		request.setAttribute("segnalazioniDomanda", segnalazioni);
		
		request.getRequestDispatcher("WEB-INF\\ElencoSegnalazioniDomanda.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
