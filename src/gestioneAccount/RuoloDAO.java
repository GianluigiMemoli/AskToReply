package gestioneAccount;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.SQLError;

import util.DBManager;

public class RuoloDAO {
	
	/**Restituisce un ruolo secondo l'id
	 * @param id
	 * @return RuoloBean
	 */
	public static RuoloBean getRuoloById(int id) {
		DBManager dbManager = DBManager.getInstance();
		RuoloBean ruolo = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRuoloById", 1);
			callProcedure.setInt(1, id);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				ruolo = new RuoloBean();
				ruolo.setId(rs.getInt("id"));
				ruolo.setNome(rs.getNString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruolo;
	}
	
	/** Restituisce un ruolo secondo il nome
	 * @param name
	 * @return RuoloBean
	 */
	public static RuoloBean getRuoloByName(String name) {
		DBManager dbManager = DBManager.getInstance();
		RuoloBean ruolo = null;
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetRuoloByName", 1);
			callProcedure.setString(1, name);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				ruolo = new RuoloBean();
				ruolo.setId(rs.getInt("id"));
				ruolo.setNome(rs.getNString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruolo;
	}
}