package javaLogicaProgramacao;

import java.util.Scanner;

public class ImprimeNome10Vezes {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		String nome;
		
		System.out.print("Informe um nome: "); nome = ler.next(); 
		for (int x = 0; x <= 9; x++)
			System.out.println(nome + " - " + x);
		
		ler.close();
	}

}
