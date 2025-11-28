package javaLogicaProgramacao;

import java.util.*;

public class TratamentoErrorTryCatch
{
	public static void main(String[] args) {
	   Scanner ler = new Scanner(System.in);
	    
       int a = 5;
       int b;
       int c;
    
        try {
        	System.out.println("Valor de b: ");
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