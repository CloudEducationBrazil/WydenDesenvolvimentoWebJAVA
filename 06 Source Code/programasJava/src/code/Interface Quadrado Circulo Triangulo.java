interface FiguraGeometrica {
     public String getNomeFigura();
     public int getArea();
     public int getPerimetro();
}

class Quadrado implements FiguraGeometrica {

    private int lado;
    
    public Quadrado (int l) {
      this.lado = l;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    @Override
    public int getArea() {
        int area;
        area = lado * lado;

        return area;
    }

    @Override
    public int getPerimetro() {
        int perimetro;

        perimetro = lado * 4;
        return perimetro;
    }

    @Override
    public String getNomeFigura() {
        return "quadrado";
    }
}

class Triangulo implements FiguraGeometrica {

    private int base;
    private int altura;
    private int ladoA;
    private int ladoB;
    private int ladoC;
    
    public Triangulo (int base, int altura, int ladoA, int ladoB, int ladoC) {
        this.base = base;
        this.altura = altura;
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getLadoA() {
        return ladoA;
    }

    public void setLadoA(int ladoA) {
        this.ladoA = ladoA;
    }

    public int getLadoB() {
        return ladoB;
    }

    public void setLadoB(int ladoB) {
        this.ladoB = ladoB;
    }

    public int getLadoC() {
        return ladoC;
    }

    public void setLadoC(int ladoC) {
        this.ladoC = ladoC;
    }
    
    @Override
    public int getArea() {
        int area;
        area = (int) (base * altura / 2);

        return area;
    }
    
    @Override
    public int getPerimetro() {
        int perimetro;

        perimetro = ladoA + ladoB + ladoC;
        return perimetro;
    }

    @Override
    public String getNomeFigura() {
        return "triângulo";
    }
}

class Circulo implements FiguraGeometrica {

    private int raio;
    
    public Circulo(int r) {
     raio = r;
    }
    
    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    @Override
    public int getArea() {
        int area;
        area = (int) (3.14 * raio * raio);

        return area;
    }

    @Override
    public int getPerimetro() {
        int perimetro;

        perimetro = (int) (3.14 * raio * 2);
        return perimetro;
    }

    @Override
    public String getNomeFigura() {
        return "círculo";
    }
}

public class Main {
    public static void main(String[] args){
        Quadrado quadrado = new Quadrado(10);
        Circulo  circulo  = new Circulo(15);
 	
	// Triangulo retangulo
        Triangulo  triangulo  = new Triangulo(6, 8, 6, 8, 10);

        System.out.println(quadrado.getArea());
        System.out.println(circulo.getArea());
        System.out.println(triangulo.getArea());
    }
    
}

