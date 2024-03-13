package academicoOO;

public class Principal {

	public static void main(String[] args) {
		Aluno julia = new Aluno();
		
		julia.bim1 = 70;
		julia.bim2 = 60;
		julia.bim3 = 90;
		julia.bim4 = 80;
		
		System.out.println(julia.media());
		System.out.println(julia.passouDeAno());
	}
}
