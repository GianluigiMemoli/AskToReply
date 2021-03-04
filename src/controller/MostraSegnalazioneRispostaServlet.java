package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.log.Log;

import model.RispostaBean;
import model.RisposteManager;
import model.SegnalazioneRispostaBean;
import model.SegnalazioniManager;

/**
 * Servlet implementation class MostraSegnalazioneRispostaServlet
 */
@WebServlet("/MostraSegnalazioneRispostaServlet")
public class MostraSegnalazioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraSegnalazioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		
		String idSegnalazione = request.getParameter("idSegnalazione");
		
		PrintWriter out = response.getWriter();
		
		if(idSegnalazione == null) { out.print("Errore! idSegnalazione mancante."); return;}
			
			SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
			SegnalazioneRispostaBean segnalazione = managerSegnalazioni.getSegnalazioneRisposta(idSegnalazione);
			
			if(segnalazione == null) { out.print("Errore! idSegnalazione non trovato."); return;}
				
				RisposteManager managerRisposte = new RisposteManager();
				
				RispostaBean rispostaSegnalata = managerRisposte.getRispostaById(segnalazione.getRispostaSegnalata().getId());
				segnalazione.setRispostaSegnalata(rispostaSegnalata);
				
				request.setAttribute("segnalazione", segnalazione);
				
				request.getRequestDispatcher("WEB-INF/SegnalazioneRisposta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
