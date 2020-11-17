package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotivazioneDAO {

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
				
				motivazioni.add(motivazione);
				
			}
			
			return motivazioni;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
