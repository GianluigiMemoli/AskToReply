package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModeratoreBean;
import model.ModeratoriManager;

/**
 * Servlet implementation class VisualizzaListaModeratori
 */
@WebServlet("/VisualizzaListaModeratori")
public class VisualizzaListaModeratori extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaListaModeratori() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	if(!super.isMasterModeratoreLogged(req.getSession())){
    		resp.setStatus(403);
    	}
    	super.service(req, resp);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ModeratoriManager modManager = new ModeratoriManager();
		ArrayList<ModeratoreBean> moderatoriList;
		try {
			moderatoriList = modManager.getAllModeratori();
			request.setAttribute("moderatoriList", moderatoriList);
			request.getRequestDispatcher("WEB-INF\\ElencoModeratori.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
