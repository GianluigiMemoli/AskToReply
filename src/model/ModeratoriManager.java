package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeratoriManager {
	
	public ArrayList<ModeratoreBean> getAllModeratori() throws SQLException{
		return ModeratoreDAO.doGetModeratori();
	}
	
	public void deleteModeratore(String id) throws SQLException{
		ModeratoreDAO.doDeactivateModeratore(id);
	}
}
