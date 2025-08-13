import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

		System.out.print("Informe número A: ");
		int a = ler.nextInt();

		System.out.print("Informe número B: ");
		int b = ler.nextInt();
		
		float media = (a + b) / 2F;

		if (media >= 6) {
		  System.out.println("Aprovado: " + media);
		  
		  for (int i = 1; i <= 10; i++)
		    System.out.println("Parabéns: " + i);
		}
		else {
		  System.out.print("Reprovado: " + media);
		}
	}
}