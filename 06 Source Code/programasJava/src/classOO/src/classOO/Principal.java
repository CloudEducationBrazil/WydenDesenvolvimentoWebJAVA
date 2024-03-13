package classOO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carro fusca = new Carro();
		Carro gol = new Carro();
		
		fusca.velocidade = 55;
		fusca.cor = "vermelho";
		gol.cor = "amarelo";
		
		System.out.println(fusca.getVelocidade());
		System.out.println(fusca.cor);
		System.out.println(gol.cor);
		System.out.println(Carro.cor);
	}
}
