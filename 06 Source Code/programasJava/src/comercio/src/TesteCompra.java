import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteCompra {

	@Test
	public void compraVista() {
		Compra c = new Compra(555.55);
		assertEquals(1, c.getNumeroParcelas());
		assertEquals(555.55, c.getValorTotal());
		assertEquals(555.55, c.getValorParcela());
	}	
	
	@Test
	public void compraParcelada() {
		Compra c = new Compra(5, 555.55);
		assertEquals(5, c.getNumeroParcelas());
		assertEquals(2777.75, c.getValorTotal());
		assertEquals(555.55, c.getValorParcela());
	}
}