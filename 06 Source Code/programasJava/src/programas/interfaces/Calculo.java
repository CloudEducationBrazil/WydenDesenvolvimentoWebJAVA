package programas.interfaces;

public interface Calculo {

	public default int soma(int a, int b) {
		return a + b;
	}

	public default int soma(int a, int b, int c) {
		return a + b + c;
	}
}
