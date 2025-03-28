package estudoPOO;
import java.util.Scanner;

import entities.PessoaFisica;
import entities.PessoaJuridica;
import entities.Triangulo;

public class SomarAreaTriangulo {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		Triangulo obj = new Triangulo();
		
		System.out.print("Informe o lado a: "); 
		obj.setA(ler.nextInt());
		
		System.out.print("Informe o lado b: ");
		obj.setB(ler.nextInt());
		
		System.out.print("Informe o lado c: ");
		obj.setC(ler.nextInt());
		
		int somaAb = obj.somar(obj.getA(), obj.getB());
		int somaAc = obj.somar(obj.getA(), obj.getC());
		int somaBc = obj.somar(obj.getB(), obj.getC());
		
		System.out.println("Resultado: " + obj.toString() + " : ");

		System.out.println("Soma AB: " + somaAb);
		System.out.println("Soma AC: " + somaAc);
		System.out.println("Soma BC: " + somaBc);
		
		PessoaFisica pesF = new PessoaFisica();
		
		System.out.print("Nome: ");	pesF.setNome(ler.next());
		System.out.print("CPF: ");	pesF.setCpf(ler.next());
		
		PessoaJuridica pesJ = new PessoaJuridica();
		
		System.out.print("Nome: ");	pesJ.setNome(ler.next());
		System.out.print("CNPJ: ");	pesJ.setCnpj(ler.next());
		
		System.out.println(pesF);
		System.out.println(pesJ);
		
		ler.close();
	}
}
