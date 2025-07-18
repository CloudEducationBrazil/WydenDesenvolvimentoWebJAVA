import java.util.Scanner;  // Import the Scanner class

public class Main
{
	public static void main(String[] args) {
	   Scanner sc = new Scanner(System.in);
	    
	   System.out.println("Entre com um numero 1: ");
	   int n1 = sc.nextInt();
	    
	   System.out.println("Entre com um numero 2: ");
	   int n2 = sc.nextInt();
	    
	   System.out.println("Resultado: " + (n1+n2) );

	   sc.close();
	}
}
