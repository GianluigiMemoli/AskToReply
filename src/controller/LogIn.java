package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RuoloBean;
import model.RuoloDAO;
import model.UtenteBean;

/**
 * Servlet implementation class LogInPartecipante
 */
@WebServlet("/LogInPartecipante")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		int p = Integer.parseInt(request.getParameter("ruolo"));
		
		UtenteBean utente = new UtenteBean();
		
		PrintWriter out = response.getWriter();
		
		if(p == 1) {
			utente.setRuoloID(RuoloDAO.getRuoloByName(RuoloBean.ROLE_PARTECIPANTE).getId());
		}			
		else if(p == 2) {
			utente.setRuoloID(RuoloDAO.getRuoloByName(RuoloBean.ROLE_MODERATORE).getId());
		}
		else {
			utente.setRuoloID(RuoloDAO.getRuoloByName(RuoloBean.ROLE_MASTER_MODERATORE).getId());
		}
		
		request.getSession().setAttribute("utenteLoggato", utente);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
