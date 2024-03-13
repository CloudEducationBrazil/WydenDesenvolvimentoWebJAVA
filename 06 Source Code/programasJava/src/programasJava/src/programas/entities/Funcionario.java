package programas.entities;

import programas.interfaces.Calculo;

public class Funcionario implements Calculo {
	private Integer id;
	private String nome;
	private Cargo cargo;
	
	public Funcionario(Integer id, String nome, Cargo cargo) {
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
/*	public int soma(int a, int b) {
		return a + b;
	}

	public int soma(int a, int b, int c) {
		return a + b + c;
	}
*/
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + "]";
	}
}
