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

public class TestListaProductos {
	
	private ListaProductos lp = new ListaProductos();
	private Producto mockP1 = mock(Producto.class);
	
	@Test
	public void testAnnadir() {
		boolean test = lp.addProducto(mockP1);
		assertTrue(test);
	}
	
	@Test
	public void testLimpiar() {
		boolean test = lp.limpiarLista();
		assertTrue(test);
	}
	
	/*@Test
	public void testCogerProducto() {
		Producto test = lp.cogerProducto(0);
		when(mockP1.getNombre()).thenReturn("uno");
		assertTrue(test.equals(mockP1));
	}*/
	
}
