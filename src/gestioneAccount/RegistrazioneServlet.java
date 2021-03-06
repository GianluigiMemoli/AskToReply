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

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	Logger log = Logger.getLogger(RegistrazioneServlet.class.getName());
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String nome,cognome,username,password,email;
		log.info(request.getParameterMap().toString());
		nome = request.getParameter("nome").trim(); 
		cognome = request.getParameter("cognome").trim();
		username = request.getParameter("username").trim();
		password = request.getParameter("password").trim();
		email = request.getParameter("email").trim();
		String[] interessi = request.getParameterValues("interessi"); 
		
		AccountManager accountManager = new AccountManager();
		try {
			accountManager.RegisterUser(nome, cognome, username, email, password, interessi);			
		} catch(CampiNonConformiException exc) {			
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			request.setAttribute("errore", exc.getMessage());
		} catch(UsernamePresenteException exc) {
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			request.setAttribute("errore", exc.getMessage());
		} catch(EmailPresenteException exc) {
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			request.setAttribute("errore", exc.getMessage());  
		} catch(Exception exc) {
			log.info("Eccezione:" + exc.getMessage() + " gestita");
			exc.printStackTrace();
			request.setAttribute("errore", "Unhandled error"); 
		}		
		request.getRequestDispatcher("registrazione").forward(request, response);
	}

}
