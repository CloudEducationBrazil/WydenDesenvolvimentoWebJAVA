package estudoJava;
import java.util.Scanner;

public class Vetor {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		//int[] vet = new int[] {6, 5, 8, 3, 5};
		int[] vet = new int[5];

		int somaVet = 0;
		for (int x = 0; x < 5; x++) {
			System.out.print("Informe elemento " + x + ": "); 
			vet[x] = ler.nextInt();

			somaVet += vet[x];
		}
		System.out.println("Total: " + somaVet);
		
		ler.close();
	}
}
