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
	
	
}
