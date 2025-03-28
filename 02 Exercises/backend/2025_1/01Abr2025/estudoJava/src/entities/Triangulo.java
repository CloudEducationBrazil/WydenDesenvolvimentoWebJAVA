package entities;

public class Triangulo {
	private int a;
	private int b;
	private int c;
	
	public int somar(int a, int b) {
		return a + b;
	}

	public int somar(int b, float c) {
		return b + (int) this.c;
	}

	public int somar(float a, float c) {
		return (int) this.a + (int) this.c;
	}

	public int somar(int a, int b, int c) {
		return a + b + c;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Soma: [a=" + a + ", b=" + b + "]";
	}
}