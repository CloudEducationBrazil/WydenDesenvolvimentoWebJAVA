package entities;

public class PessoaFisica extends PessoaAbstract {
	private String cpf;
	
	public PessoaFisica() {
	}
	
	public PessoaFisica(String cpf, String nome) {
		super(nome);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", nome=" + getNome() + "]";
	}
}
