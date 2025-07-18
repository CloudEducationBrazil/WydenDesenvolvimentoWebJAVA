package diagramaClasse;

public class Principal {

	public static void main(String[] args) {
		Empregado prof = new Empregado(5, "Heleno");

		Empregado eng  = new Engenheiro(6, "Juju", 5555);
		Empregado sec  = new Secretario(5, "Josy", "Inglês");
		Empregado tec  = new Tecnico(5, "Paulo", 5);
		
		System.out.println(prof.toString());
		System.out.println(eng.toString());
		System.out.println(sec.toString());
		System.out.println(tec.toString());
	}
}
