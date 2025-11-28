package entities;

public class Pessoa {
	public String cpf;
	public String nome;
	public int idade;
	public double altura;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public double getAltura() {
		return altura;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	@Override
	public String toString() {
		return "Pessoa [Cpf=" + cpf + ", Nome=" + nome + ", Idade=" + idade + ", Altura=" + altura + "]";
	}

	public String toStringReduzido() {
		return "Pessoa [Nome=" + nome + ", Idade=" + idade + "]";
	}
}
