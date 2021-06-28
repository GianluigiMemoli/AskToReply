package gestioneDomanda;

import java.util.ArrayList;

public class CategorieManager {
	
	public ArrayList<CategoriaBean> getAll() {
		return CategoriaDAO.getAll();
	}
	
	
	
}
