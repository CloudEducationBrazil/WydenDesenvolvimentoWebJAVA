package javaLogicaProgramacao;

public class SomarSerieWhile {

	public static void main(String[] args) {

		float soma = 0;
		int den = 3;
		int num = 2;
		while (num <= 50) {
			soma += (1.0 * num / den);
			den += 2;
			num += 2;
		}
		System.out.println("Total While: " + soma);
	}
}
