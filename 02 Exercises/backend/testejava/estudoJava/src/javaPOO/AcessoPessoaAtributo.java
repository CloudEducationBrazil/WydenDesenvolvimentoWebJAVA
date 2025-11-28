package javaPOO;

import entities.Pessoa;

public class AcessoPessoaAtributo {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(); // Novo objeto
		
		p1.cpf = "555555";
 		p1.nome = "Juju";
		p1.idade = 20;
		p1.altura = 1.45;		
		
		Pessoa p2 = new Pessoa() {
			{cpf = "444444"; nome = "Maria"; idade = 74; altura = 1.57;}
		}; // Novo objeto

		Pessoa p3 = p2; // Acesso por referência p3 = p2
		
		p3.nome = "Josy";		
		
		System.out.println(String.format("CPF: %s Nome: %s Idade: %d Altura: %.2f",
				                          p1.cpf, p1.nome, p1.idade, p1.altura));

		System.out.println("CPF: " + p2.cpf + " Nome: " + p2.nome + " Idade: "+ p2.idade);
		
		System.out.println(p1);
		System.out.println(p1.toStringReduzido());
	}
}