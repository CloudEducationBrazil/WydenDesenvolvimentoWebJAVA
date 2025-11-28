package javaPOO;

import java.util.Locale;
import java.util.Scanner;

import entities.PessoaAbstract;
import entities.PessoaFisica;
import entities.PessoaJuridica;
import entities.Utils;

public class CadastroPessoa {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		PessoaFisica   pesF1 = new PessoaFisica("99999", "Juju Cardoso Filho");
		PessoaFisica   pesF2 = new PessoaFisica();

		PessoaJuridica pesJ = new PessoaJuridica();
		
		System.out.print("Nome Pessoa Física: ");	pesF2.setNome(ler.nextLine());
		System.out.print("CPF: ");	pesF2.setCpf(ler.next());
		
		ler.nextLine();
		
		System.out.print("Nome Pessoa Jurídica: ");	pesJ.setNome(ler.nextLine());
		System.out.print("CNPJ: ");	pesJ.setCnpj(ler.next());
		
		System.out.println(pesF1);
		System.out.println(pesF2);
		
		System.out.println(pesJ);
		
		// Utils - Atributos e Métodos Estáticos
		Utils.nome = "Juju Utils Estático";
		System.out.println(Utils.nome + " " + Utils.imprimeNome());
		
		// UpCasting
		PessoaAbstract pFUpCasting = new PessoaFisica();
		pFUpCasting.setNome("Juju Silva");
		System.out.println(pFUpCasting);

		ler.close();
	}
}