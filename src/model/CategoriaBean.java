package model;

public class CategoriaBean {
	private String nome, id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CategoriaBean [nome=" + nome + ", id=" + id + "]";
	} 
	
	@Override 
	public boolean equals(Object compared) {
		CategoriaBean categoriaCompared  = (CategoriaBean) compared;
		return categoriaCompared.getId().equals(this.getId());
	}
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + Integer.parseInt(id);  
	        return result;
	    }
	
}
