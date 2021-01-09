package controller;

import java.io.IOException;
import java.util.ArrayList;
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

		idRisposta = request.getParameter("idRisposta");
		
		idDomanda = request.getParameter("idDom");
		
		valore = Integer.parseInt(request.getParameter("value"));
		
		int n=-1;
		ArrayList<VotazioneBean> voti = VotazioneDAO.getVotazioniByIdRisposta(idRisposta);
		for(int i=0; i<voti.size();i++) {
			
			log.info("nella lista c'è: "+voti.get(i).getIdUtente());
			log.info("mentre l'utente loggato è: "+idUtente);
			if(voti.get(i).getIdUtente().contentEquals(idUtente))n=i;
			
		}
		
		VotazioneBean votazione=new VotazioneBean(idUtente, idRisposta, valore);

		if(n>=0) {
			
			if(voti.get(n).getValore()==1) {
				log.info("RIMUOVO IL [MI PIACE]");
				if(valore==1) {
					VotazioneDAO.removeVotazioneRisposta(votazione);
				}else {
					log.info("AGGIUNGO IL [NON MI PIACE]");
					VotazioneDAO.removeVotazioneRisposta(votazione);
					VotazioneDAO.addVotazioneRisposta(votazione);
				}
			}
			else{
				log.info("RIMUOVO IL [NON MI PIACE]");
				if(valore==-1) {
					VotazioneDAO.removeVotazioneRisposta(votazione);
				}else {
					log.info("AGGIUNGO IL [MI PIACE]");
					VotazioneDAO.removeVotazioneRisposta(votazione);
					VotazioneDAO.addVotazioneRisposta(votazione);
				}
			}

			
		}
		else {
			log.info("VOTO AGGIUNTO");
		//VotazioneBean votazione=new VotazioneBean(idUtente, idRisposta, valore);
		VotazioneDAO.addVotazioneRisposta(votazione);
		}
		request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);
	}

	private String idUtente;
	private String idRisposta;
	private String idDomanda;
	private int valore;
	
}
