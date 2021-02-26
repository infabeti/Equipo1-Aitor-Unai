package TestModelo;

import Modelo.*;
import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

public class TestProductoTienda {
	
	private Producto mockP1 = mock(Producto.class);
	private Producto mockP2 = mock(Producto.class);
	private ProductoTienda pt1 = new ProductoTienda(mockP1, 3);
	private ProductoTienda pt2 = new ProductoTienda(mockP2,0);
	
	@Test
	public void testAddCantidad() {
		int esperado = 5;
		int res = pt1.addCantidad(2);
		assertEquals(esperado, res);
		esperado = 13;
		res = pt1.addCantidad(8);
		assertEquals(esperado, res);
	}
	
	@Test
	public void testRestarCantidad() {
		int esperado = 1;
		int res = pt1.restarCantidad(2);
		assertEquals(esperado, res);
	}
	
	@Test
	public void testEquals() {
		when(mockP1.getNombre()).thenReturn("uno");
		when(mockP2.getNombre()).thenReturn("dos");
		boolean test = pt1.equals(pt2);
		assertFalse(test);
		test = pt1.equals(pt1);
		assertTrue(test);
		ProductoTienda pt3 = pt1;
		test = pt1.equals(pt3);
		assertTrue(test);
	}
}
