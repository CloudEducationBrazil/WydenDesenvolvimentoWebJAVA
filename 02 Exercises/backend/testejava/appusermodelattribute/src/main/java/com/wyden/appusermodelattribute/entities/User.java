package com.wyden.appusermodelattribute.entities;

public class User {
	
	private String nome;
	private Integer idade;
	
	public User() {
	}

	public User(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "User [user=" + nome + ", idade=" + idade + "]";
	}

	public String addAttribute(String mensagem) {
		return mensagem;
	}
}