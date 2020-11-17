package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PartecipanteBean;
import model.SegnalazioneRispostaBean;
import model.SegnalazioneRispostaDAO;
import model.VotazioneDAO;

/**
 * Servlet implementation class SegnalazioneRispostaServlet
 */
@WebServlet("/SegnalazioneRispostaServlet")
public class SegnalazioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		idRisposta = request.getParameter("idRisposta");
		idMotivazione = request.getParameter("idMotivazione");
		commento = request.getParameter("commento");
		stato = request.getParameter("stato");
		dataSegnalazione = new Date();
		
		SegnalazioneRispostaBean sr=new SegnalazioneRispostaBean(idRisposta, dataSegnalazione, idMotivazione, stato, commento);
		SegnalazioneRispostaDAO.addSegnalazioneRisposta(sr);		
	}
	
	
	private String idRisposta;
	private String idMotivazione;
	private String commento;
	private String stato;
	private Date dataSegnalazione;
	
}