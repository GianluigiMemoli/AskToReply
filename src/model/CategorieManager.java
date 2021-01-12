package model;

import java.util.ArrayList;

public class CategorieManager {
	
	public ArrayList<CategoriaBean> getAll() {
		return CategoriaDAO.getAll();
	}
	
	// TODO Non va passato un UtenteBean invece di String?
	public ArrayList<CategoriaBean> getCategorieByIdUtente(String idUtente) {
		return CategoriaDAO.getCategorieByUtente(idUtente);
	}
	
}
