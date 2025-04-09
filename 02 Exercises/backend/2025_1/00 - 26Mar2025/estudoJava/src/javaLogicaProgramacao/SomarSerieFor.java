package javaLogicaProgramacao;

public class SomarSerieFor {

	public static void main(String[] args) {

		float soma = 0;
		int den = 3;
		for (int num = 2; num <= 50; num +=2) {
			soma += (1.0 * num / den);
			den += 2;
		}
		System.out.println("Total For: " + soma);
	}
}
