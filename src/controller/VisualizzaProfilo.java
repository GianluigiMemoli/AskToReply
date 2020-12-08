package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaBean;
import model.CategoriaDAO;
import model.PartecipanteBean;

/**
 * Servlet implementation class VisualizzaProfilo
 */
@WebServlet("/VisualizzaProfilo")
public class VisualizzaProfilo extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaProfilo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Logger log = Logger.getAnonymousLogger();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkPartecipante(req.getSession(), resp);
    	} catch(Exception exc) {
    		log.info("exc thrown");  
    		req.getRequestDispatcher("/accesso").forward(req, resp);    	
    	}
    	super.service(req, resp);

    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PartecipanteBean currUser = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		ArrayList<CategoriaBean> interessi = CategoriaDAO.getAll();
		ArrayList<CategoriaBean> interessiUtente = CategoriaDAO.getCategorieByUtente(currUser.getId());			
		request.setAttribute("interessi", interessi);						
		request.setAttribute("interessiUtente", interessiUtente);
		log.info(interessi.toString());
		log.info(interessiUtente.toString());
		request.getRequestDispatcher("Profilo.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
