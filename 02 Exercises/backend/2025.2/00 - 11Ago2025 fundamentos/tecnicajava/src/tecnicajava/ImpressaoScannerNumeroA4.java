package tecnicajava;

import java.util.Scanner;

public class ImpressaoScannerNumeroA4
{
	public static void main(String[] args) {
	    Scanner ler = new Scanner(System.in);

		System.out.print("Informe número A: ");
		int a = ler.nextInt();

		System.out.print("Número: " + a);
		
		ler.close();
	}
}