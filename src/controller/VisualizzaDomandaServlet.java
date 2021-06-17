package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
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
import model.MotivazioniManager;
import model.PartecipanteBean;
import model.RispostaBean;
import model.RisposteManager;
import model.UtenteBean;

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
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
    		checkPartecipante(req.getSession(), resp);
    	} catch(RuntimeException e) {
    		req.getRequestDispatcher("/accesso").forward(req, resp);
    	}
    	
    	super.service(req, resp);
    	
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
				PartecipanteBean user = (PartecipanteBean) request.getSession().getAttribute("utenteLoggato");
				DomandaBean domandaVisualizzata = manager.getDomandaById(idDomanda);
				
				if(domandaVisualizzata != null) {
					
					request.setAttribute("domanda", domandaVisualizzata);
					request.setAttribute("domandeRisposte", manager.getDomandeRisposte(user));

					// allegati
					/* 
					 * Li metto all'interno di un ArrayList perché con un array normale ci sono problemi nella JSP.
					 * Il problema consiste in ${allegati.length > 0} che da errore. Con ${allegati.size() > 0} funziona.
					 */
					
					ArrayList<RispostaBean> risposte = new ArrayList<RispostaBean>();
					ArrayList<MotivazioneBean> motivazioni = new ArrayList<MotivazioneBean>();
					
					MotivazioniManager managerMotivazioni = new MotivazioniManager();
					motivazioni = managerMotivazioni.getAll();
					request.setAttribute("motivazioni", motivazioni);
											
					int page = 0;
					if(request.getParameter("pageRi") != null) {
						log.info("Pagina numero: "+request.getParameter("pageRi"));		
						page = Integer.parseInt(request.getParameter("pageRi"));	
					}
					
					
					//risposte=RispostaDAO.getRisposteByIdDomanda(idDomanda, page);//aggiunta
					risposte=RisposteManager.getRisposteByIdDomanda(idDomanda, page);
					

					RisposteManager managerRisposte = new RisposteManager();
					UtenteBean utenteloggato = getLoggedUser(request.getSession());
					//request.setAttribute("risposteApprezzate", managerRisposte.getRisposteApprezzate(utenteloggato));
					HashSet<RispostaBean> rsrb =  managerRisposte.getRisposteApprezzate(utenteloggato);
					HashSet<RispostaBean> rsrb2 =  managerRisposte.getRisposteNonApprezzate(utenteloggato);

					log.info("LA SIZE DELL'HASHSET E' "+ String.valueOf(rsrb.size()));
					 
					 HashSet<String> risposteApprezzate = new HashSet<String>();
					 HashSet<String> risposteNonApprezzate = new HashSet<String>();

					 
						for (RispostaBean x : rsrb) {
							 risposteApprezzate.add(x.getId());
							 log.info(x.getId());
						}
						
						for (RispostaBean y : rsrb2) {
							 risposteNonApprezzate.add(y.getId());
							 log.info(y.getId());
						}
						
						
						request.setAttribute("risposteApprezzate", risposteApprezzate);
						request.setAttribute("risposteNonApprezzate", risposteNonApprezzate);

						
						/*rsrb.forEach((k) -> {
							log.info(k.getId());
						});*/
					 

					//boolean b = RispostaDAO.getRisposteByIdDomanda(idDomanda, page+1).isEmpty();
					 boolean b = RisposteManager.getRisposteByIdDomanda(idDomanda, page+1).isEmpty();
					
					if(b) {
						//log.info("La prossima scheda è vuota");
						request.setAttribute("next", 0);
					}else {
						//log.info("La prossima scheda è piena");
						request.setAttribute("next", 1);
					}
					
					request.setAttribute("risposte", risposte);
					
					/* Per sapere se l'utente è loggato E non è l'autore della domanda e quindi se può apparirgli o meno il form per pubblicare la risposta. */
					UtenteBean utente = getLoggedUser(request.getSession());
					request.setAttribute("utenteLoggato", utente);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Domanda.jsp");
					dispatcher.forward(request, response);
					
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VisualizzaHome");
					dispatcher.forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				writer.print(e.getMessage());
			}
			
		} else {
			writer.print("L'ID della domanda non può essere nullo.");
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
