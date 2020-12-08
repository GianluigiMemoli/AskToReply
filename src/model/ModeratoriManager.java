package model;

import java.util.ArrayList;

public class ModeratoriManager {
	
	public ArrayList<ModeratoreBean> getAllModeratori(){
		return ModeratoreDAO.doGetAllModeratori();
	}
	
	public void deleteModeratore(String id){
		ModeratoreDAO.doDeactivateModeratore(id);
	}
}
