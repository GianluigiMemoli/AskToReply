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

import model.DomandaBean;
import model.DomandeManager;
import model.PartecipanteBean;

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
    	
    	checkPartecipante(req.getSession(), resp);
    	
    	super.service(req, resp);
    	
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		PartecipanteBean autore = (PartecipanteBean) getLoggedUser(request.getSession());
		
		String titolo = request.getParameter("titolo");
		String corpo = request.getParameter("corpo");
		Date dataPubblicazione = new Date();
		String[] idCategorie = request.getParameterValues("categorie");		
		List<Part> allegati = request.getParts().stream().filter(part -> "allegati".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
		
		DomandeManager manager = new DomandeManager();
		
		DomandaBean domanda = null;
		
		try {
			domanda = manager.pubblicaDomanda(autore, titolo, corpo, dataPubblicazione, idCategorie, allegati);
			response.sendRedirect(request.getContextPath() + "/VisualizzaDomandaServlet?id=" + domanda.getId());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
			//response.getWriter().print(e.getMessage());
			// TODO Eliminare questa riga di codice
		} finally {
			// response.sendRedirect(request.getContextPath() + "/VisualizzaDomandaServlet?id=" + domanda.getId());
		}
		
				
	}
	
	Logger logger = Logger.getLogger(PubblicazioneDomandaServlet.class.getName());
}
