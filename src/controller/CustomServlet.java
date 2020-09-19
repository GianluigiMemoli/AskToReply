package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

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
		
		// TODO Ci vorrebbe un metodo isPartecipante() perché piazzare ugetRuoloID() == 1 fa davvero schifo
		if(getLoggedUser(session).getRuoloID() != 1)
			return false;
				
		return true;
	}
		
	public void checkPartecipante(HttpSession session) {
		UtenteBean utente = new UtenteBean();
		utente.setId("codice_utente_1");
		utente.setRuoloID(1);
		
		session.setAttribute("utenteLoggato", utente);
		
		if(!isPartecipanteLogged(session))
			throw new RuntimeException("Un utente deve essere autenticato e questo utente deve essere un partecipante.");
	}
	
	//
	
	private static final long serialVersionUID = -4247750814791191423L;
}
