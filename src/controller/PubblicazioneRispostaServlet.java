package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.PartecipanteBean;
import model.RisposteManager;
import model.SegnalazioneRispostaDAO;

/**
 * Servlet implementation class PubblicazioneRispostaServlet
 */
@WebServlet("/PubblicazioneRispostaServlet")
public class PubblicazioneRispostaServlet extends CustomServlet {
	
	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); //test

	
	private static final long serialVersionUID = 1L;

	public PubblicazioneRispostaServlet() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		idDomanda = request.getParameter("idDom");
		
		corpo = request.getParameter("corpo");
		//allegati = request.getParts().stream().filter(part -> "allegati".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
		allegati=null;
		
		PartecipanteBean autoreBean = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
		idAutore = autoreBean.getId();
		
		Date dataPubblicazione = new Date(System.currentTimeMillis());
		RisposteManager manager = new RisposteManager();

		try {
			manager.pubblicaRisposta(idDomanda, corpo, allegati, idAutore, dataPubblicazione);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("VisualizzaHome").forward(request, response);


	}

	Logger logger = Logger.getLogger(PubblicazioneRispostaServlet.class.getName());

	private PartecipanteBean autoreBean;

	private String idDomanda;
	private String corpo;
	private List<Part> allegati;
	private String idAutore; //Autore della risposta
	//private Date dataPubblicazione;
}
