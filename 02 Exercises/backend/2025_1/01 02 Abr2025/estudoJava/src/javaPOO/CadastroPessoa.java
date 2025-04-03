package javaPOO;

import java.util.Scanner;

import entities.PessoaFisica;
import entities.PessoaJuridica;

public class CadastroPessoa {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
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