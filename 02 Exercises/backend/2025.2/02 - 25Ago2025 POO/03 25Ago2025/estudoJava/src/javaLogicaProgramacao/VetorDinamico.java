package javaLogicaProgramacao;

import java.util.ArrayList;

public class VetorDinamico
{
	public static void main(String[] args) {
	    ArrayList<Integer> vetDinamico = new ArrayList<Integer>();
	    
	    vetDinamico.add(3);
	    vetDinamico.add(5);
	    vetDinamico.add(5);
	    vetDinamico.add(8);
	    vetDinamico.add(1);
	    vetDinamico.add(6);
	    vetDinamico.add(21);
	    vetDinamico.add(-1);
	    //vetDinamico.remove(5);

	System.out.println(String.format("Número %d %d %s é positivo", vetDinamico.get(3), 5, "juju"));
        System.out.printf("Número %d é positivo\n", vetDinamico.get(3));
		
        int numero = 5;
        int index = vetDinamico.indexOf(numero);
        
        if (index != -1) {
            System.out.println(vetDinamico.get(index) + " posição: " + index);
        } else {
            System.out.println(String.format("Elemento %d não encontrado", numero));
        }  

        for (int num : vetDinamico) {
            System.out.print(num + " ");
        }		
	}
}