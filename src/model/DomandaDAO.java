package model;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Types;
import java.util.ArrayList;

public class DomandaDAO {

	public static DomandaBean addDomanda(DomandaBean domanda) {
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("CreateDomanda", 5);
			
			procedure.setNString(1, domanda.getTitolo());
			procedure.setNString(2, domanda.getCorpo());
			procedure.setNString(3, domanda.getIdAutore());
			procedure.setDate(4, new java.sql.Date(domanda.getDataPubblicazione().getTime()));
			
			procedure.registerOutParameter(5, Types.VARCHAR);
			
			procedure.executeUpdate();
			
			domanda.setId(procedure.getNString(5));
			
			return domanda;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void addCategoriaDomanda(DomandaBean domanda, CategoriaBean categoria) {
		DBManager manager = DBManager.getInstance();
		
		try {
			
			// E se inserisce una categoria già presente?
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("AddCategoriadomanda", 2);
			
			procedure.setNString(1, domanda.getId());
			procedure.setNString(2, categoria.getId());
			
			procedure.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
}
