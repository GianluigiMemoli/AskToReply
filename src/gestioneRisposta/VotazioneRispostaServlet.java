package gestioneRisposta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneAccount.PartecipanteBean;
import util.CustomServlet;

/**
 * Servlet implementation class VotazioneRispostaServlet
 */
@WebServlet("/VotazioneRispostaServlet")
public class VotazioneRispostaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(VotazioneDAO.class.getName()); // test

    /**
     * @see HttpServlet#HttpServlet()
     */

	public VotazioneRispostaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			checkPartecipante(req.getSession(), resp);
		} catch(RuntimeException exc) {
			req.getRequestDispatcher("/accesso").forward(req, resp);
		}
		super.service(req, resp);  
		
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		
		PartecipanteBean autoreBean = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		idRisposta = request.getParameter("idRisposta");
		RispostaBean risposta = new RispostaBean();
		risposta.setId(idRisposta);
		idDomanda = request.getParameter("idDom");
		
		if (autoreBean==null) {throw new Exception("Errore. Il sistema non è riuscito ad autenticare l'utente.");}
		if (idRisposta==null) throw new Exception("Errore. Il sistema non è riuscito a completare l'operazione.");
		if (idDomanda==null) throw new Exception("Errore. Il sistema non è riuscito a completare l'operazione.");

		
		if(request.getParameter("value").equals("")) valore=0; else
		valore = Integer.parseInt(request.getParameter("value"));
		
		int n=-1;
		ArrayList<VotazioneBean> voti = VotazioneDAO.getVotazioniByIdRisposta(idRisposta);
		for(int i=0; i<voti.size();i++) {
			
			log.info("nella lista c'è: "+voti.get(i).getUtente().getId());
			log.info("mentre l'utente loggato è: "+autoreBean.getId());
			if(voti.get(i).getUtente().getId().contentEquals(autoreBean.getId()))n=i;
			
		}
		
		VotazioneBean votazione=new VotazioneBean(autoreBean, risposta, valore);

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
		VotazioneDAO.addVotazioneRisposta(votazione);
		}
		request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String idRisposta;
	private String idDomanda;
	private int valore;
	
}
