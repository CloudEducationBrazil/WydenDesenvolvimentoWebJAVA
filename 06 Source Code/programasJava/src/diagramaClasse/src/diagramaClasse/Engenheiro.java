package diagramaClasse;

public class Engenheiro extends Empregado {
	private int crea;
	
	public Engenheiro(int cpf, String nome) {
		super(cpf, nome);
	}

	public Engenheiro(int cpf, String nome, int crea) {
		super(cpf, nome);
		this.crea = crea;
	}

	public int getCrea() {
		return crea;
	}

	public void setCrea(int crea) {
		this.crea = crea;
	}

	@Override
	public String toString() {
		return "Engenheiro [crea=" + crea + "]"+" nome="+ nome;
	}


}
