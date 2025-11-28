package tecnicajava;

import java.util.Scanner;

public class MediaCondicional6
{
	public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

		System.out.print("Informe número A: ");
		int a = ler.nextInt();

		System.out.print("Informe número B: ");
		int b = ler.nextInt();
		
		float media = (a + b) / 2F;

		if (media >= 6)
		  System.out.print("Aprovado: " + media);
		else
		  System.out.print("Reprovado: " + media);
		
		ler.close();
	}
}