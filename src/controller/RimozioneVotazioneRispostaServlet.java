package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PartecipanteBean;
import model.RispostaBean;
import model.VotazioneBean;
import model.VotazioneDAO;

/**
 * Servlet implementation class RimozioneVotazioneRispostaServlet
 */
@WebServlet("/RimozioneVotazioneRispostaServlet")
public class RimozioneVotazioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneVotazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		utente.setId(request.getParameter("idUtente"));
		risposta.setId(request.getParameter("idRisposta"));
		valore = Integer.parseInt(request.getParameter("valore"));
		VotazioneBean votazione=new VotazioneBean(utente, risposta, valore);
		VotazioneDAO.removeVotazioneRisposta(votazione);	
		
	}
	
	private PartecipanteBean utente;
	private RispostaBean risposta;
	private int valore;
	
}
