package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
	
	public static void addCategoria(CategoriaBean newCategoria) {
		DBManager dbManager = DBManager.getInstance();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("CreateCategoria", 1);
			callProcedure.setNString(1, newCategoria.getNome());
			callProcedure.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static CategoriaBean getCategoriaByNome(String nome) {
		DBManager dbManager = DBManager.getInstance();
		CategoriaBean searchedCategoria = null; 
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetCategoriaByName", 1);
			callProcedure.setNString(1, nome);
			ResultSet rs = callProcedure.executeQuery();
			if(rs.next()) {
				searchedCategoria = new CategoriaBean();
				searchedCategoria.setId(rs.getNString("id"));
				searchedCategoria.setNome(rs.getNString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchedCategoria;
	}
	
	public static ArrayList<CategoriaBean> getAll() {
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		DBManager dbManager = DBManager.getInstance();
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetAllCategorie", 0);
			
			ResultSet rs = callProcedure.executeQuery();
			
			while(rs.next()) {
				CategoriaBean categoria = new CategoriaBean();
				
				categoria.setId(rs.getString("id"));
				categoria.setNome(rs.getString("nome"));
				
				categorie.add(categoria);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categorie;
	}
	
	public static ArrayList<CategoriaBean> getCategorieDomandaByIdDomanda(String idDomanda) {
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		DBManager dbManager = DBManager.getInstance();
		
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetACategorieDomandaByIdDomanda", 1);
			
			ResultSet rs = callProcedure.executeQuery();
			
			while(rs.next()) {
				CategoriaBean categoria = new CategoriaBean();
				
				categoria.setId(rs.getString("id"));
				categoria.setNome(rs.getString("nome"));
				
				categorie.add(categoria);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categorie;
	}
	
	public static ArrayList<CategoriaBean> getCategorieByUtente(String idUtente){
		DBManager dbManager = DBManager.getInstance();
		ArrayList<CategoriaBean> categorie = new ArrayList<CategoriaBean>();
		try {
			CallableStatement callProcedure = dbManager.prepareStoredProcedureCall("GetInteressiByUtente", 1);
			callProcedure.setString(1, idUtente);
			ResultSet records = callProcedure.executeQuery();
			while(records.next()) {
				CategoriaBean categoria = new CategoriaBean();
				categoria.setId(records.getNString("idCategoria"));
				categoria.setNome(records.getNString("nome"));
				categorie.add(categoria);
			}
			return categorie;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
