package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountManager;
import model.UtenteBean;



/**
 * Servlet implementation class VisualizzaListaModeratori
 */
@WebServlet("/GestioneModeratori")
public class GestioneModeratoriServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneModeratoriServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Logger log = Logger.getAnonymousLogger();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub    
    	if(!super.isMasterModeratoreLogged(req.getSession())){    	
    		log.info("Non sei master mod");
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	super.service(req, resp);
    }
          
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountManager accountManager = new AccountManager();
		ArrayList<UtenteBean> moderatoriList;		
		moderatoriList = accountManager.getAllModeratori();
		request.setAttribute("moderatoriList", moderatoriList);
		request.getRequestDispatcher("GestioneModeratori.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
