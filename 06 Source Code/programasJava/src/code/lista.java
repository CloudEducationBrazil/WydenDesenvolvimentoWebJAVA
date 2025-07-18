package aulaJava;

import java.util.ArrayList;
import java.util.Scanner;

public class Lista {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		//ArrayList<Integer> lista = new ArrayList();
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		int numero;
		
		//Inserir elementos na lista
		do {
			System.out.print("Digite um número: ");
			//numero = Integer.parseInt(entrada.nextLine());
			numero = entrada.nextInt();
			
			if (numero != 0) {
				lista.add(numero);
			}
	
		} 
		
		while (numero != 0);
			//Exibir todos os elementos da lista
			System.out.println("=== Os números inseridos foram ===");
			for (int i = 0; i < lista.size(); i++) {
				System.out.println(lista.get(i));
		}

		entrada.close();
	}
}