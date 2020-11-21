package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RuoloBean;
import model.RuoloDAO;
import model.UtenteBean;

public class CustomServlet extends HttpServlet {
	
	public UtenteBean getLoggedUser(HttpSession session) {
		return (UtenteBean) session.getAttribute("utenteLoggato");
	}
	
	public boolean isUserLogged(HttpSession session) {
		return (getLoggedUser(session) != null);
	}
	
	private boolean isUserRoleLogged(HttpSession session, String role) {
		
		if(getLoggedUser(session) == null)
			return false;
		
		RuoloBean ruolo = RuoloDAO.getRuoloByName(role);
		
		if((getLoggedUser(session).getRuoloID() != ruolo.getId()))
			return false;
		
		return true;
		
	}
	
	public boolean isPartecipanteLogged(HttpSession session) {
		return isUserRoleLogged(session, RuoloBean.ROLE_PARTECIPANTE);	
	}
	
	public boolean isModeratoreLogged(HttpSession session) {
		return isUserRoleLogged(session, RuoloBean.ROLE_MODERATORE);
	}
	
	public boolean isMasterModeratoreLogged(HttpSession session) {
		return isUserRoleLogged(session, RuoloBean.ROLE_MASTER_MODERATORE);
	}
	
	public void checkPartecipante(HttpSession session, HttpServletResponse resp) {
		if(!isPartecipanteLogged(session)) {
			// resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accesso negato. Non sei un Partecipante.");
			throw new RuntimeException("Non sei un Patecipante");
		}
	}
	
	public void checkModeratore(HttpSession session, HttpServletResponse resp) {
		if(!isModeratoreLogged(session)) {
			// resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accesso negato. Non sei un Moderatore.");
			throw new RuntimeException("Non sei un Moderatore");
		}
	}

	public void checkMasterModeratore(HttpSession session, HttpServletResponse resp) {	
		if(!isMasterModeratoreLogged(session)) {
			// resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accesso negato. Non sei un Master Moderatore.");
			throw new RuntimeException("Non sei un Master Moderatore");
		}
	}
	
	//
	
	private static final long serialVersionUID = -4247750814791191423L;
}