package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.PartecipanteBean;
import model.RispostaDAO;
import model.RisposteManager;

/**
 * Servlet implementation class RimozioneRispostaServlet
 */
@WebServlet("/RimozioneRispostaServlet")
public class RimozioneRispostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneRispostaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RisposteManager manager = new RisposteManager();
		idRisposta = request.getParameter("idRisposta");
		try {
			partecipanteBean = (PartecipanteBean) request.getSession().getAttribute("loggedUser");
			idPartecipanteSessione = partecipanteBean.getId();
			if(idPartecipanteSessione == request.getParameter("idPartecipante"))
			RispostaDAO.removeRisposta(idRisposta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Logger logger = Logger.getLogger(RimozioneRispostaServlet.class.getName());
	
	private String idRisposta;
	private PartecipanteBean partecipanteBean;
	private String idPartecipanteSessione;
}

//da testare