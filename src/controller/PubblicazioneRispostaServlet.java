package controller;

import java.io.IOException;
import java.util.Date;
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

/**
 * Servlet implementation class PubblicazioneRispostaServlet
 */
@WebServlet("/PubblicazioneRispostaServlet")
public class PubblicazioneRispostaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;

	public PubblicazioneRispostaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		checkPartecipante(req.getSession(), resp);
		
		super.service(req, resp);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		idDomanda = request.getParameter("idDomanda");
		corpo = request.getParameter("corpo");
		allegati = request.getParts().stream().filter(part -> "allegati".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
		autoreBean = (PartecipanteBean) request.getSession().getAttribute("loggedUser");
		idAutore = autoreBean.getId();
		//dataPubblicazione = new Date();

		RisposteManager manager = new RisposteManager();

		try {
			manager.pubblicaRisposta(idDomanda, corpo, allegati, idAutore/*, dataPubblicazione*/);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Logger logger = Logger.getLogger(PubblicazioneRispostaServlet.class.getName());

	private PartecipanteBean autoreBean;

	private String idDomanda;
	private String corpo;
	private List<Part> allegati;
	private String idAutore; //Autore della risposta
	//private Date dataPubblicazione;
}
