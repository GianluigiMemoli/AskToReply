package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.VotazioneBean;
import model.VotazioneDAO;

/**
 * Servlet implementation class VotazioneRispostaServlet
 */
@WebServlet("/VotazioneRispostaServlet")
public class VotazioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	public VotazioneRispostaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		idUtente = request.getParameter("idUtente");
		idRisposta = request.getParameter("idRisposta");
		valore = Integer.parseInt(request.getParameter("valore"));
		VotazioneBean votazione=new VotazioneBean(idUtente, idRisposta, valore);
		VotazioneDAO.addVotazioneRisposta(votazione);	
	}

	private String idUtente;
	private String idRisposta;
	private int valore;
	
}
