package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import model.PartecipanteBean;
import model.UtenteBean;

public class CustomServlet extends HttpServlet {
	
	public UtenteBean getLoggedUser(HttpSession session) {
		return (UtenteBean) session.getAttribute("utenteLoggato");
	}
	
	public boolean isUserLogged(HttpSession session) {
		return (getLoggedUser(session) != null);
	}
	
	public boolean isPartecipanteLogged(HttpSession session) {	
		if(getLoggedUser(session) == null)
			return false;
		
		// TODO Ci vorrebbe un metodo isPartecipante() perché piazzare getRuoloID() == 1 fa davvero schifo
		if(getLoggedUser(session).getRuoloID() != 1)
			return false;
				
		return true;
	}
	
	public boolean isModeratoreLogged(HttpSession session) {
		
		if(getLoggedUser(session) == null)
			return false;
		
		if(getLoggedUser(session).getRuoloID() != 2)
			return false;
		
		return true;
	}
		
	public void checkPartecipante(HttpSession session) {
		if(!isPartecipanteLogged(session))
			throw new RuntimeException("Un utente deve essere autenticato e questo utente deve essere un partecipante.");
	}
	
	public void checkModeratore(HttpSession session) {
		if(!isModeratoreLogged(session))
			throw new RuntimeException("Un moderatore deve essere autenticato.");
	}
	
	//
	
	private static final long serialVersionUID = -4247750814791191423L;
}