package javaLogicaProgramacao;
import java.util.Arrays;
import java.util.Scanner;

public class VetorEstatico {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		//int[] vetEstatico = new int[] {6, 5, 8, 3, 5};
		//int[] vetEstatico = {6, 5, 8, 3, 5};
		
		int[] vetEstatico = new int[5];

		int somaVet = 0;
		for (int x = 0; x < 5; x++) {
			System.out.print("Informe elemento " + x + ": "); 
			vetEstatico[x] = ler.nextInt();

			somaVet += vetEstatico[x];
		}

		System.out.println(Arrays.toString(vetEstatico));
		System.out.println("Total: " + somaVet);
		
		ler.close();
	}
}
