package programas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Soma {

	public Soma(int a, int b) {
		if ( a < 0) {
			throw new RuntimeException("Valor A, não pode ser negativo");
		}

		if ( b < 0) {
			throw new RuntimeException("Valor B, não pode ser negativo");
		}
		
	}
	
	public int somar(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		  Scanner ler = new Scanner(System.in); // 2. instanciando e criando um objeto Scanner
		  int a, b;
		  
		  try {
			  System.out.println("Informe o primeiro valor de a: ");
			  a = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)

			  System.out.println("Informe o primeiro valor de b: ");
			  b = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)
			  
			  Soma soma = new Soma(a,b);
			  
			  System.out.printf("\nResultados:\n");
			  System.out.printf("%d + %d = %3d\n", a, b, soma.somar(a, b));

		  } catch (InputMismatchException erro1) {
		        System.err.println("Não é permitido inserir letras, informe apenas números inteiros!");
		    }
		    catch(ArithmeticException erro2){
		        System.err.println("O número do divisor deve ser diferente de 0!");
		    }
		    catch(RuntimeException e){
		        System.err.println(e.getMessage());
		    }
		  
		 finally{
		       System.out.println("Execução do Finally!");
			   ler.close();
		 }
	}
}
