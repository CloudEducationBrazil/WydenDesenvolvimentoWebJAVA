package programas;

import programas.entities.Cargo;
//import programas.entities.Funcionario;
import programas.entities.Funcionario;

public class POOJava {

	public static void main(String[] args) {
		Cargo cargo = new Cargo(6, "Analista");
	
		Funcionario func = new Funcionario(5, "Julia", cargo);
		
		func.setNome("Josy");
		
		int totalx = func.soma(5, 6);
		System.out.println(totalx);
		
		int totaly = func.soma(5, 6, 5);
		System.out.println(totaly);
		
		System.out.println(func.getNome());
		System.out.println(func.toString());		
	}
}
