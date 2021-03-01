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

public class TestLineaPedido {
	
	private Producto prodMock = mock(Producto.class);
	private Producto prodMock2 =  mock(Producto.class);
	private LineaPedido lp = new LineaPedido(prodMock, 5, 3.0);
	
	@Test
	public void testConstructor() {
		assertEquals(lp.getProducto(), prodMock);
		assertEquals(lp.getCantidad(), 5);
		assertEquals(lp.getTotal(), 3.0, 0);
	}
	
	@Test
	public void testSetProducto() {
		lp.setProducto(prodMock2);
		assertEquals(lp.getProducto(), prodMock2);
	}
	
	@Test
	public void testSetCantidad() {
		lp.setCantidad(4);
		assertEquals(4, lp.getCantidad());
	}
	
	@Test
	public void testSetTotal() {
		lp.setTotal(8.0);
		assertEquals(8.0, lp.getTotal(), 0);
	}
	
}
