package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.DomandeManager;

/**
 * Servlet implementation class PubblicazioneDomandaServlet
 */
@WebServlet("/PubblicazioneDomandaServlet")
@MultipartConfig
public class PubblicazioneDomandaServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubblicazioneDomandaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	checkPartecipante(req.getSession());
    	
    	super.service(req, resp);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		idAutore = "codice_utente_1";
		titolo = request.getParameter("titolo");
		corpo = request.getParameter("corpo");
		dataPubblicazione = new Date();
		idCategorie = request.getParameterValues("categorie");
		
		allegati = request.getParts().stream().filter(part -> "allegati".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
			
		DomandeManager manager = new DomandeManager();
		
		try {
			manager.pubblicaDomanda(idAutore, titolo, corpo, dataPubblicazione, idCategorie, allegati);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	
	Logger logger = Logger.getLogger(PubblicazioneDomandaServlet.class.getName());
	
	private String idAutore;
	private String titolo;
	private String corpo;
	private Date dataPubblicazione;
	private String[] idCategorie;
	private List<Part> allegati;
}
