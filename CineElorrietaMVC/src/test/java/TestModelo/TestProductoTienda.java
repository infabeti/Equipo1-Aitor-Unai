package TestModelo;

import Modelo.*;
import java.sql.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestProductoTienda {
	
	private Date fecha = new Date(0);
	private Producto p1 = new Producto("Prod1",fecha, "Unproducto", 1, 3);
	private Producto p2 = new Producto("Prod2", fecha, "Otroproducto",2,4);
	private ProductoTienda pt1 = new ProductoTienda(p1, 3);
	private ProductoTienda pt2 = new ProductoTienda(p2);
	
	@Test
	public void testAddCantidad() {
		int esperado = 5;
		int res = pt1.addCantidad(2);
		assertEquals(esperado, res);
		esperado = 8;
		res = pt2.addCantidad(8);
		assertEquals(esperado, res);
	}
	
	@Test
	public void testRestarCantidad() {
		int esperado = 1;
		int res = pt1.restarCantidad(2);
		assertEquals(esperado, res);
		esperado = -6;
		res = pt2.restarCantidad(6);
		assertEquals(esperado, res);
	}
	
}
