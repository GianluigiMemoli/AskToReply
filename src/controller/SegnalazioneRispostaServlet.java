package controller;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MotivazioneBean;
import model.RispostaBean;
import model.SegnalazioneRispostaBean;
import model.SegnalazioneRispostaDAO;

/**
 * Servlet implementation class SegnalazioneRispostaServlet
 */
@WebServlet("/SegnalazioneRispostaServlet")
public class SegnalazioneRispostaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		checkPartecipante(req.getSession(), resp);
		
		super.service(req, resp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		idRisposta = request.getParameter("idRisp");
		if(idRisposta!=null) {
			if(request.getParameter("idMotivazione")!=null) {
				idMotivazione = Integer.parseInt(request.getParameter("idMotivazione"));
				commento = request.getParameter("commento");
				
		//stato = Integer.parseInt(request.getParameter("stato"));
		stato=1;
		dataSegnalazione = new Date();
					
		SegnalazioneRispostaBean sr=new SegnalazioneRispostaBean();
		RispostaBean rb = new RispostaBean();
		rb.setId(idRisposta);
		sr.setRispostaSegnalata(rb);
		sr.setDataSegnalazione(dataSegnalazione);
		MotivazioneBean motivazione = new MotivazioneBean();
		motivazione.setId(idMotivazione);
		sr.setMotivazione(motivazione);
		
		sr.setStato(stato);
		sr.setCommento(commento);
		SegnalazioneRispostaDAO.addSegnalazioneRisposta(sr);		
		

				} else log.info("Errore: Id motivazione Null");
					} else log.info("Errore: Id risposta Null");

		
		request.getRequestDispatcher("VisualizzaDomandaServlet?id="+request.getParameter("idDomanda")).forward(request, response);


	}
	
	private String idRisposta;
	private int idMotivazione;
	private String commento;
	private int stato;
	private Date dataSegnalazione;
	
}
