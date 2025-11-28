package javaPOO;

import entities.Animal;
import entities.Cachorro;
import entities.Gato;

public class DowncastingSeguro {
    public static void main(String[] args) {
        // DownCasting: animal é Animal, mas podemos converter para Cachorro
    	// Compila, mas gera ClassCastException em tempo de execução
    	// vai dar erro em tempo de execução (ClassCastException), 
    	// porque um Animal puro não é um Cachorro
    	
    	// Maneira Errada, compila, mas dar exceção
        //Animal animal =  new Animal();
        //Cachorro dog = (Cachorro) animal;
        //dog.abanarRabo(); // só executa se realmente for um Cachorro   	

        Animal animal1 = new Cachorro(); // aponta para um Cachorro
        Animal animal2 = new Gato();     // aponta para um Gato

        // ---------- Downcasting seguro ----------
        if (animal1 instanceof Cachorro) {
            Cachorro dog = (Cachorro) animal1;
            dog.abanarRabo(); // só executa se realmente for um Cachorro
        }

        if (animal2 instanceof Cachorro) {
            // este bloco NÃO vai executar, pois animal2 é um Gato
            Cachorro dog = (Cachorro) animal2;
            dog.abanarRabo();
        } else {
            System.out.println("animal2 não é um Cachorro!");
        }
    }
}