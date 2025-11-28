package javaLogicaProgramacao;

import java.util.Scanner;

public class MediaAluno {

	public static void main(String[] args) {

		Scanner ler = new Scanner(System.in);
		boolean entradaValida;
		float av1=0, av2=0, media;
		
		entradaValida = false;	
		while (!entradaValida) {
			try {
				System.out.println("Informe nota av1: "); av1 = Float.parseFloat(ler.next());
				entradaValida = true;
			} catch (Exception e) {
				System.out.println("AV1 inválida");
			}	
		}

		entradaValida = false;
		while (!entradaValida) {
			try {
				System.out.println("Informe nota av2: "); av2 = Float.parseFloat(ler.next());
				entradaValida = true;
			} catch (Exception e) {
				System.out.println("AVA2 inválida");
			}	
		}

		media = (av1 + av2) / 2;
				
		if (media >= 6)
			System.out.println("Aprovado: " + media);
		else
			System.out.println("Reprovado: " + media);
	  
		ler.close();
	}
}