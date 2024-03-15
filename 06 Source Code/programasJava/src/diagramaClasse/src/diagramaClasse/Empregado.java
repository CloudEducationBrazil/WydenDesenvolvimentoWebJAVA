package diagramaClasse;

public class Empregado {
	private int cpf;
	protected String nome;
	
	public Empregado(int cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Empregado [cpf=" + cpf + ", nome=" + nome + "]";
	}
}
