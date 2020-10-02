package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.CredenzialiNonValideException;
import model.AccountManager;
import model.PartecipanteBean;
import model.UtenteBean;

/**
 * Servlet implementation class AccessoServlet
 */
@WebServlet("/AccessoServlet")
public class AccessoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	Logger log = Logger.getLogger(AccessoServlet.class.getName());
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email").trim(); 
		String password = request.getParameter("password").trim();
		
		AccountManager accountManager = new AccountManager();	
		try {
			UtenteBean loggedIn = accountManager.autenticaUtente(email, password);
			if (loggedIn instanceof PartecipanteBean) {
				PartecipanteBean partecipante = (PartecipanteBean) loggedIn;
				log.info("Partecipante loggato");
				request.getSession().setAttribute("loggedUser", partecipante);
				
			}
			
		} catch(CredenzialiNonValideException exc) {
			//todo gestire errore 
			exc.printStackTrace();
		}
		
	}

}
