package entities;

public class Triangulo {
	private Integer a;
	private Integer b;
	private Integer c;
	
	public Triangulo() {}
	
	public double Area() {
		float p = (float) (a + b + c) / 2;
		
		return Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}
	
	public int somar(Integer a, Integer b) {
		return a + b;
	}

	public int somar(Integer b, Float c) {
		return b + c.intValue();
	}

	public int somar(float a, float c) {
		return (int) a + (int) c;
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
		return "Soma: [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
}