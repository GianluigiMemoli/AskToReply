package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RoleRouterServlet
 */
@WebServlet("/RoleRouterServlet")
public class RoleRouterServlet extends CustomServlet {
	private static final long serialVersionUID = 1L;
	final static String MASTER_MODERATORE_HOME = "GestioneModeratori";
	final static String PARTECIPANTE_HOME = "VisualizzaHome";
	final static String MODERATORE_HOME = "stub";
	
    public RoleRouterServlet(){
        super();
         
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	HttpSession session = req.getSession();
    	if(!super.isUserLogged(session)){
    		resp.sendRedirect("/accesso");
    	} else {
    		if(super.isMasterModeratoreLogged(session)) {
    			req.getRequestDispatcher(MASTER_MODERATORE_HOME).forward(req, resp);
    		} else if(super.isPartecipanteLogged(session)) {
    			req.getRequestDispatcher(PARTECIPANTE_HOME).forward(req, resp);
    		} else if(super.isModeratoreLogged(session)) {
    			req.getRequestDispatcher(MODERATORE_HOME).forward(req, resp);
    		} else {
    			resp.sendRedirect("/accesso");
    		}
    	}
    	super.service(req, resp);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
