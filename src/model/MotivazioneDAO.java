package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MotivazioneDAO {

	static Logger log = Logger.getLogger(SegnalazioneRispostaDAO.class.getName()); // test
	
	public static ArrayList<MotivazioneBean> getAll() {

		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetAllMotivazioni", 0);
			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<MotivazioneBean> motivazioni = new ArrayList<MotivazioneBean>();
			
			while(rs.next()) {
				
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setId(rs.getInt("id"));
				motivazione.setNome(rs.getNString("nome"));
				
				
				log.info("***********************************");
				log.info(motivazione.getNome());
				log.info("***********************************");

				
				motivazioni.add(motivazione);
				
			}
			
			return motivazioni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static MotivazioneBean getMotivazioneById(int id) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetMotivazioneById", 1);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				MotivazioneBean motivazione = new MotivazioneBean();
				motivazione.setNome(rs.getString("nome"));
		        return motivazione;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
