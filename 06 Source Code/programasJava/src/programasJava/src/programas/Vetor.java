package programas;

public class Vetor {

	public static void main(String[] args) {
		int n = 10; // tamanho do vetor
		int v[] = new int[n]; // declaração e alocação de espaço para o vetor "v"
		int i; // índice ou posição
		// processando os "n" elementos do vetor "v"
		for (i=0; i<n; i++) {
	  		v[i] = i+5; // na i-ésima posição do vetor "v" armazena o valor da viável "i"
		}
		
		for (i=0; i<n; i++)
			System.out.println(v[i]);
	}
}
