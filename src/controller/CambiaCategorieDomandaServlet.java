package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DomandaBean;
import model.DomandeManager;
import model.MotivazioneBean;
import model.SegnalazioneDomandaBean;
import model.SegnalazioniManager;
import model.CategoriaBean;

/**
 * Servlet implementation class CambiaCategorieDomandaServlet
 */
@WebServlet("/CambiaCategorieDomandaServlet")
public class CambiaCategorieDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaCategorieDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	checkModeratore(req.getSession());
    	
    	super.service(req, resp);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idSegnalazione = request.getParameter("idSegnalazione");
		String[] categorieDomanda = request.getParameterValues("categorieDomanda");
		
		PrintWriter writer = response.getWriter();
		
		if(idSegnalazione != null && categorieDomanda.length != 0) {
			
			SegnalazioniManager managerSegnalazioni = new SegnalazioniManager();
			DomandeManager managerDomande = new DomandeManager();
			
			SegnalazioneDomandaBean segnalazione = managerSegnalazioni.getSegnalazioneDomanda(idSegnalazione);
			DomandaBean domanda = managerDomande.getDomandaById(segnalazione.getDomandaSegnalata().getId());
			
			if(segnalazione != null && domanda != null) {
				
				if(segnalazione.getMotivazione().getId() == MotivazioneBean.OFFTOPIC) {
				
					ArrayList<CategoriaBean> nuoveCategorie = new ArrayList<CategoriaBean>();
					
					for(int i = 0; i < categorieDomanda.length; i++) {
						CategoriaBean categoria = new CategoriaBean();
						categoria.setId(categorieDomanda[i]);
						nuoveCategorie.add(categoria);
					}
					
					domanda.setCategorie(nuoveCategorie);
					
					managerDomande.updateCategorieDomanda(domanda);
					managerSegnalazioni.risolviSegnalazioneDomanda(segnalazione);
					
				} else {
					writer.print("La motivazione della segnalazione NON è 'Offtopic'.");
				}
				
			} else {
				writer.print("Segnalazione con ID '" + idSegnalazione + "' e/o Domanda con ID '" + segnalazione.getDomandaSegnalata().getId() + "' inesistenti.");
			}
			
		} else {
			writer.print("Sono stati compiuti errori durante l'invio dei parametri.");
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
