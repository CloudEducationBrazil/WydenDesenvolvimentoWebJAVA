package diagramaClasse;

public class Secretario extends Empregado {

	private String idioma;

	public Secretario(int cpf, String nome, String idioma) {
		super(cpf, nome);
		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Override
	public String toString() {
		return "Secretario [idioma=" + idioma + "]"+" nome="+ nome;
	}
}
