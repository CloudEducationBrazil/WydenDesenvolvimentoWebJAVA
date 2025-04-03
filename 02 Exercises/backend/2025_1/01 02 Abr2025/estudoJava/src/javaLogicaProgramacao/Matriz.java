package javaLogicaProgramacao;
import java.util.Scanner;

public class Matriz {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		int[][] mat = new int[2][3];

		int somaMat = 0;
		for (int l = 0; l < 2; l++) {
			for (int c = 0; c < 3; c++) {
				System.out.print("Informe elemento [" + l + c + "] : "); 
				mat[l][c] = ler.nextInt();

				somaMat += mat[l][c];
			}
		}
		System.out.println("Total Matriz: " + somaMat);
		
		ler.close();
	}
}
