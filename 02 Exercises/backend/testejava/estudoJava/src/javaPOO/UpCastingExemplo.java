package javaPOO;

import entities.Animal;
import entities.Cachorro;

public class UpCastingExemplo {
    public static void main(String[] args) {
        // UpCasting: cachorro é tratado como Animal
        Animal animalUp = new Cachorro();  // conversão implícita
        
        // Chama o método sobrescrito da classe Cachorro
        // No upcasting o método chamado sempre será o do objeto real, não da referência.
        animalUp.fazerSom(); // polimorfismo dinâmico:o obj real é um Cachorro
        
        // não consegue acessar método exclusivo de Cachorro
        // animal.abanarRabo(); // ERRO de compilação!
    }
}