
public class Compra {
	double valorTotal;
	int numeroParcelas;
	
	// a vista
	Compra(double valor){
		valorTotal = valor;
		numeroParcelas = 1;
	}
	
	// a prazo
	Compra(int qtdParcelas, double valorParcela){
		numeroParcelas = qtdParcelas;
		valorTotal = numeroParcelas * valorParcela;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}
	
	public double getValorParcela() {
		return valorTotal / numeroParcelas;
	}
}
