package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import model.AccountManager;

/**
 * Servlet implementation class CrazioneModeratoreServlet
 */
@WebServlet("/CreazioneModeratoreServlet")
public class CreazioneModeratoreServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreazioneModeratoreServlet() {
        super();
        // TODO Auto-generated constructor stub        
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub    	
    	super.checkMasterModeratore(req.getSession(), resp);
    	 
    	     	
    	super.service(req, resp);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
			String nome, cognome, email, username, password;
			logger.info("ujeee");
			Enumeration<String> paramsNames = request.getParameterNames();
			logger.info("" + paramsNames.hasMoreElements());
			while (paramsNames.hasMoreElements()) {
				String paramName = paramsNames.nextElement();
				logger.info(paramName);
			}
			nome = request.getParameter("nome").trim();
			cognome = request.getParameter("cognome").trim();
			email = request.getParameter("email").trim();
			username = request.getParameter("username").trim();
			password = request.getParameter("password").trim();
			AccountManager accountManager = new AccountManager();
			
			try {
				accountManager.RegistraModeratore(email, password, username, nome, cognome);
			} catch (Exception exc) {
				//todo gestisci l'eccezione
				logger.info("ERRORE");
				exc.printStackTrace();
			}
		
		
	}

}
