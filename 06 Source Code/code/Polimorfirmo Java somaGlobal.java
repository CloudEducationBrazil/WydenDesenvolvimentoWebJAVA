import java.util.Scanner;  // Import the Scanner class

class Super {
    public double soma(double n1, double n2) {
        return n1 + n2;
    }
}

class Sub extends Super {
    public double soma(double n1, double n2, double n3) {
        return n1 + n2 + n3;
    }
}
        
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Informe um número 1? ");
        double n1 = sc.nextDouble();
        
        System.out.print("Informe um número 2? ");
        double n2 = sc.nextDouble();
        
        System.out.print("Informe um número 3? ");
        double n3 = sc.nextDouble();
        
        Sub calc = new Sub();
        
        System.out.println("resultado: " + calc.soma(n1, n2));
    }
}