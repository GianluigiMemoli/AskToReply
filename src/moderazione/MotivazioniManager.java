package moderazione;

import java.util.ArrayList;

public class MotivazioniManager {
	public ArrayList<MotivazioneBean> getAll() {
		return MotivazioneDAO.getAll();
	}
}
