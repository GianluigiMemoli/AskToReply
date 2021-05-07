package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Exceptions.ErrorePubblicazioneRispostaException;
import model.DomandaDAO;
import model.PartecipanteBean;
import model.RisposteManager;
import model.SegnalazioneRispostaDAO;

/**
 * Servlet implementation class PubblicazioneRispostaServlet
 */
@WebServlet("/PubblicazioneRispostaServlet")
@MultipartConfig
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
			List <Part> allegati = null;
			if (idDomanda.isEmpty()){
				log.info("Errore: Id Domanda Null");
				request.setAttribute("errore", "Il corpo della risposta deve contenere almeno due caratteri.");
				response.setStatus(401);
				log.info("Etest");

				//request.getRequestDispatcher("VisualizzaHome").forward(request, response);
				request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);

			} 
			else {
		

			

				idAutoreDomanda=DomandaDAO.getDomandaById(idDomanda).getAutore().getId();
			
				allegati = request.getParts().stream().filter(part -> "allegati".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
			
				corpo = request.getParameter("corpo");
			
				autoreBean = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
				idAutore = autoreBean.getId();
	

				
			

		Date dataPubblicazione = new Date(System.currentTimeMillis());
boolean s=true;
			try {
			RisposteManager manager = new RisposteManager();
			manager.pubblicaRisposta(idDomanda, corpo, allegati, idAutore, idAutoreDomanda, dataPubblicazione);
			//request.getRequestDispatcher("VisualizzaHome").forward(request, response);


		} catch (Exception exc) {
			s=false;
			log.info("RISPOSTA NON PUBBLICATA!");
			// TODO Auto-generated catch block
			request.setAttribute("errore", exc.getMessage());
			response.setStatus(401);
			//request.getRequestDispatcher("VisualizzaHome").forward(request, response);
			request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);
		}
			if(s)request.getRequestDispatcher("VisualizzaDomandaServlet?id="+idDomanda).forward(request, response);


			}
	}

	private PartecipanteBean autoreBean;
	private String idAutoreDomanda;
	private String idDomanda;
	private String corpo;
	private String idAutore; //Autore della risposta
}
