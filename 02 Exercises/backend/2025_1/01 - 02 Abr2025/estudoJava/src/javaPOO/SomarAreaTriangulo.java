package javaPOO;
import java.util.Scanner;

import entities.Triangulo;

public class SomarAreaTriangulo {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		Triangulo triangulo = new Triangulo();
		
		System.out.print("Informe o lado a: "); 
		triangulo.setA(ler.nextInt());
		
		System.out.print("Informe o lado b: ");
		triangulo.setB(ler.nextInt());
		
		System.out.print("Informe o lado c: ");
		triangulo.setC(ler.nextInt());
		
		int somaAb = triangulo.somar(triangulo.getA(), triangulo.getB());
		int somaAc = triangulo.somar(triangulo.getA(), triangulo.getC());
		int somaBc = triangulo.somar(triangulo.getB(), triangulo.getC());
		
		System.out.println("Resultado: " + triangulo.toString());

		System.out.println("Soma AB: " + somaAb);
		System.out.println("Soma AC: " + somaAc);
		System.out.println("Soma BC: " + somaBc);

		System.out.println("Area: " + triangulo.Area());
		
		ler.close();
	}
}
