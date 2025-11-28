package entities;

public abstract class PessoaAbstract {
	private String nome;
	
	public PessoaAbstract() {
	}
	
	public PessoaAbstract(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
