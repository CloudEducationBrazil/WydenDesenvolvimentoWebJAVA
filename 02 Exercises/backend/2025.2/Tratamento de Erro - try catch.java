import java.util.*;

public class Main
{
	public static void main(String[] args) {
	   Scanner ler = new Scanner(System.in);
	    
       int a = 5;
       int b;
       int c;
    
        try {
            b = ler.nextInt();
            c = a / b;
		    System.out.println(c);
            
        } catch(ArithmeticException e) {
		    System.out.println("Erro divisão por zero. ");
        }

          catch(InputMismatchException e) {
		    System.out.println("Tipo incompatível. ");
        }

          catch(Exception e) {
		    System.out.println("Error. ");
        }
        ler.close();
    }
}