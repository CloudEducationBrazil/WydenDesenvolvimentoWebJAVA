package tecnicajava;

import java.util.Scanner;

public class Media5
{
	public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

		System.out.print("Informe número A: ");
		int a = ler.nextInt();

		System.out.print("Informe número B: ");
		int b = ler.nextInt();
		
		float media = (a + b) / 2F;
		
		System.out.print("Resultado: " + media);
		
		ler.close();
	}
}
