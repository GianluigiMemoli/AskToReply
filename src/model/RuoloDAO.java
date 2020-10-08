package model;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.SQLError;

public class RuoloDAO {
	
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
}