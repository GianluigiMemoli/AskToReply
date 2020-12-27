package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PartecipanteBean;
import model.VotazioneBean;
import model.VotazioneDAO;

/**
 * Servlet implementation class VotazioneRispostaServlet
 */
@WebServlet("/VotazioneRispostaServlet")
public class VotazioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(VotazioneDAO.class.getName()); // test

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
		PartecipanteBean autoreBean = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		idUtente = autoreBean.getId();
		
		log.info("#######################  idUtenteee:  #########################");
		log.info(idUtente);

		idRisposta = request.getParameter("idRisposta");
		
		log.info("#######################  idRispostaaa:  #########################");
		log.info(idRisposta);
		
		idDomanda = request.getParameter("idDom");
		
		log.info("#######################  idDomanda:  #########################");
		log.info(idDomanda);
		
		valore = Integer.parseInt(request.getParameter("value"));
		
		log.info("#######################  valoreee:  #########################");
		log.info(String.valueOf(valore));
		
		VotazioneBean votazione=new VotazioneBean(idUtente, idRisposta, valore);
		VotazioneDAO.addVotazioneRisposta(votazione);
		
		request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);
	}

	private String idUtente;
	private String idRisposta;
	private String idDomanda;
	private int valore;
	
}
