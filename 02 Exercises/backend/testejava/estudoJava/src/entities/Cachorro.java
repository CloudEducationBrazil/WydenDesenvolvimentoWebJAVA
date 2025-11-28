package entities;

public class Cachorro extends Animal {
    @Override
    public void fazerSom() {
        System.out.println("O cachorro late: Au Au!");
    }
    
    public void abanarRabo() {
        System.out.println("O cachorro abana o rabo...");
    }
}