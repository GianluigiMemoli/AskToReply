package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.MotivazioneBean;
import model.MotivazioneDAO;
import model.RispostaBean;
import model.RispostaDAO;
import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class VisualizzaDomandaServlet
 */
@WebServlet("/VisualizzaDomandaServlet")
public class VisualizzaDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getAnonymousLogger();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDomanda = request.getParameter("id");
		
		PrintWriter writer = response.getWriter();
		
		if(idDomanda != null) {
			
			try {
				
				DomandeManager manager = new DomandeManager();
				
				DomandaBean domandaVisualizzata = manager.getDomandaById(idDomanda);
				
				if(domandaVisualizzata != null) {
					
					request.setAttribute("domanda", domandaVisualizzata);
					
					// allegati
					/* 
					 * Li metto all'interno di un ArrayList perch� con un array normale ci sono problemi nella JSP.
					 * Il problema consiste in ${allegati.length > 0} che da errore. Con ${allegati.size() > 0} funziona.
					 */
					
					ArrayList<File> allegati = new ArrayList<File>();
					//
				
					ArrayList<RispostaBean> risposte = new ArrayList<RispostaBean>();
					ArrayList<MotivazioneBean> motivazioni = new ArrayList<MotivazioneBean>();
					
					motivazioni=MotivazioneDAO.getAll();
					request.setAttribute("motivazioni", motivazioni);
											
					int page = 0;
					if(request.getParameter("pageRi") != null) {
						log.info("Pagina numero: "+request.getParameter("pageRi"));		
						page = Integer.parseInt(request.getParameter("pageRi"));	
					}
					
					risposte=RispostaDAO.getRisposteByIdDomanda(idDomanda, page);//aggiunta
					boolean b = RispostaDAO.getRisposteByIdDomanda(idDomanda, page+1).isEmpty();
					if(b) {
						//log.info("La prossima scheda � vuota");
						request.setAttribute("next", 0);
					}else {
						//log.info("La prossima scheda � piena");
						request.setAttribute("next", 1);
					}
					
					request.setAttribute("risposte", risposte);
					
					
					//
					File[] a = manager.getAllegati(domandaVisualizzata);
					
					if(a!=null) {
						for(int i = 0; i < a.length; i++) {
							allegati.add(a[i]);
						}
					}
					
					request.setAttribute("allegati", allegati);
					
					/* Per sapere se l'utente � loggato E non � l'autore della domanda e quindi se pu� apparirgli o meno il form per pubblicare la risposta. */
					UtenteBean utente = getLoggedUser(request.getSession());
					request.setAttribute("utenteLoggato", utente);
										
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Domanda.jsp");
					dispatcher.forward(request, response);
					
				} else {
					writer.print("Domanda con id = '" + idDomanda + "' non trovata.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				writer.print(e.getMessage());
			}
			
		} else {
			writer.print("L'ID della domanda non pu� essere nullo.");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
