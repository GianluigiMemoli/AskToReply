package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountManager;

/**
 * Servlet implementation class DisattivazioneModeratore
 */
@WebServlet("/DisattivazioneModeratore")
public class DisattivazioneModeratore extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Logger log = Logger.getAnonymousLogger();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	try {
    		super.checkMasterModeratore(req.getSession(), resp);
    	} catch(RuntimeException exc) {
    		log.info("NON SEI MASTER MODERATORE");
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	super.service(req, resp);    	
    }
	public DisattivazioneModeratore() {
        	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idModeratore; 
		idModeratore = request.getParameter("idModeratore");
		AccountManager accountManager = new AccountManager();
		accountManager.deleteModeratore(idModeratore);
		log.info("Mod disattivato");
		response.sendRedirect("GestioneModeratori");
		log.info("Dispatching");
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
 
}
