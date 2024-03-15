package diagramaClasse;

public class Tecnico extends Empregado {
	private int grau;

	public Tecnico(int cpf, String nome) {
		super(cpf, nome);
	}

	public Tecnico(int cpf, String nome, int grau) {
		super(cpf, nome);
		this.grau = grau;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	@Override
	public String toString() {
		return "Tecnico [grau=" + grau + "]"+" nome="+ nome;
	}
}
