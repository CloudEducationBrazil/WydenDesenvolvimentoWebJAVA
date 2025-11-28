package Thread;

public class ExemploThread1 {
    public static void main(String[] args) {
        MinhaThread t1 = new MinhaThread();
        MinhaThread t2 = new MinhaThread();
        
        t1.setName("Thread A");
        t2.setName("Thread B");
        
        t1.start(); // inicia a execução da thread A
        t2.start(); // inicia a execução da thread B
    }
}