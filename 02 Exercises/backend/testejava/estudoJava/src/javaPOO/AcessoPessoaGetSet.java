package javaPOO;

import entities.Pessoa;

public class AcessoPessoaGetSet {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(); // Novo objeto
		Pessoa p2 = new Pessoa(); // Novo objeto
		Pessoa p3 = p2; // Acesso por referência p3 = p2
		
		p1.setNome("Juju");
		p1.setCpf("555555");
		p1.setIdade(20);
		p1.setAltura(1.45);
		
		p2.setNome("Maria");
		p2.setCpf("444444");
		p2.setIdade(74);
		
		p3.setNome("Josy");
		
		System.out.println(String.format("CPF: %s Nome: %s Idade: %d Altura: %.2f",
				           p1.getCpf(), p1.getNome(), p1.getIdade(), p1.getAltura()));

		System.out.println("CPF: " + p2.getCpf() + " Nome: " + p2.getNome());
	}
}