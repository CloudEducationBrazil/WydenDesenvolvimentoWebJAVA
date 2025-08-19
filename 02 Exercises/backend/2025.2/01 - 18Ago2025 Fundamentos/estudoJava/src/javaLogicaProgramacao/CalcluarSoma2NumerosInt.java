package javaLogicaProgramacao;

import java.util.Scanner;

public class CalcluarSoma2NumerosInt {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		
		int x, y, soma;
		
		System.out.print("Informe primeiro número: "); x = ler.nextInt();
		System.out.print("Informe primeiro número: "); y = ler.nextInt();
		
		soma = x + y;
		System.out.println("Resultado: " + soma);
		
		ler.close();
	}

}
