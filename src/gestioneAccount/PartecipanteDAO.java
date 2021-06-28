package gestioneAccount;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gestioneDomanda.CategoriaBean;
import util.DBManager;

public class PartecipanteDAO {
	/**Restituisce un'istanza di PartecipanteBean con partecipante.email == email se presente nel database
	 * @param email
	 * @return PartecipanteBean
	 */
	public static PartecipanteBean getPartecipanteByEmail(String email) {
		DBManager dbManager = DBManager.getInstance();
		PartecipanteBean searchedPartecipante = null;
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetPartecipanteByEmail", 1);		
		callProcedure.setString(1, email);
		ResultSet rs = callProcedure.executeQuery();
				
		if(rs.next()) {
			searchedPartecipante = new PartecipanteBean(
					rs.getString("email"),
					rs.getString("passwordHash"), 
					rs.getString("username"), 
					rs.getString("nome"),
					rs.getString("cognome"), 
					rs.getInt("ruoloId"), 
					rs.getBoolean("isDisattivato"), 
					rs.getString("id"),
					rs.getInt("numeroSegnalazioni"),
					null
					);
		}		
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
		return searchedPartecipante;
	}
	
	/** Aggiugne record di tipo interesse relativo al partecipante
	 * @param partecipante
	 * @param categoria
	 */
	public static void addInteresse(PartecipanteBean partecipante, CategoriaBean categoria) {
		DBManager dbManager = DBManager.getInstance();		
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("AddInteresseUtente", 2);		
		callProcedure.setString(1, partecipante.getEmail());
		callProcedure.setString(2, categoria.getNome());
		callProcedure.executeUpdate();
				
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	/** Rimuove la categoria dal record partecipante
	 * @param partecipante
	 * @param categoria
	 */
	public static void removeInteresse(PartecipanteBean partecipante, CategoriaBean categoria) {
		DBManager dbManager = DBManager.getInstance();		
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemoveInteresseUtente", 2);		
		callProcedure.setString(1, partecipante.getEmail());
		callProcedure.setString(2, categoria.getNome());
		callProcedure.executeUpdate();
				
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	/** Disattiva il partecipante
	 * @param partecipante
	 */
	public static void removePartecipanteById(PartecipanteBean partecipante) {
		DBManager dbManager = DBManager.getInstance();		
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("RemovePartecipante", 1);		
		callProcedure.setString(1, partecipante.getId());		
		callProcedure.executeUpdate();
				
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
	

	/** Aggiorna il profilo di partecipante
	 * @param partecipante
	 */
	public static void updateUtente(PartecipanteBean partecipante) {
		DBManager dbManager = DBManager.getInstance();		
		try {
		CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("UpdateUtente", 6);		
		callProcedure.setString(1, partecipante.getEmail());
		callProcedure.setString(2, partecipante.getUsername());
		callProcedure.setString(3, partecipante.getCognome());
		callProcedure.setString(4, partecipante.getNome());
		callProcedure.setString(5, partecipante.getId());
		callProcedure.setString(6, partecipante.getPasswordHash());
		callProcedure.executeUpdate();
				
		} catch (SQLException exc) {
			exc.printStackTrace();
		}
	}
}
