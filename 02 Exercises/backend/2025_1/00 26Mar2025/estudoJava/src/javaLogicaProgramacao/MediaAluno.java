package javaLogicaProgramacao;

import java.util.Scanner;

public class MediaAluno {

	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		
		float av1, av2, media;
		
		System.out.print("Informe nota av1: "); av1 = ler.nextFloat();
		System.out.print("Informe nota av2: "); av2 = ler.nextFloat();
		
		media = (av1 + av2) / 2;
		
		if (media >= 6)
		     System.out.println("Aprovado: " + media);
		else
		     System.out.println("Reprovado: " + media);
		
		ler.close();
	}
}
