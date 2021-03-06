package gestioneAccount;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.CampiNonConformiException;
import Exceptions.EmailPresenteException;
import Exceptions.UsernamePresenteException;
import util.CustomServlet;

/**
 * Servlet implementation class UpdateProfilo
 */
@WebServlet("/UpdateProfilo")
public class UpdateProfilo extends CustomServlet {
	Logger log = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfilo() {
        super();
        // TODO Auto-generated constructor stub
    }
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome,cognome,username,email;
		log.info(request.getParameterMap().toString());
		nome = request.getParameter("nome").trim(); 
		cognome = request.getParameter("cognome").trim();
		username = request.getParameter("username").trim();		
		email = request.getParameter("email").trim();
		String[] interessi = request.getParameterValues("interessi");
		String password = null;
		if(request.getParameter("password") != null && request.getParameter("password").trim().length() != 0) {
			password = request.getParameter("password");
		}
		AccountManager accountManager = new AccountManager();
		PartecipanteBean loggedUser =(PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		try {
			accountManager.updateUtente(loggedUser, nome, cognome, username, email, interessi, password);
			

		} catch(CampiNonConformiException exc) {			
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			request.setAttribute("errore", exc.getMessage());
		} catch (EmailPresenteException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", e.getMessage());
		} catch (UsernamePresenteException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errore", e.getMessage());
		}
		catch(Exception exc) {
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			request.setAttribute("errore", "Unhandled error"); 
		}		
		request.getRequestDispatcher("VisualizzaProfilo").forward(request, response);
	}

}
