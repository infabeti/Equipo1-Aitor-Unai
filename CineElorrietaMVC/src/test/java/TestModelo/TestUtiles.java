package TestModelo;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import Modelo.ListaPlatos;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Plato;
import Modelo.Producto;

import Modelo.Utiles;


public class TestUtiles {
	
	private Modelo modeloMock = mock(Modelo.class);
	private String resultadoEsperadoString, resultadoString;
	private String[] resultadoArrayString;
	private double resultadoEsperadoDouble, resultadoDouble;
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private ListaPlatos listaPlatosMock = mock(ListaPlatos.class);
	private Plato platoMock = mock(Plato.class);
	private Producto productoMock = mock(Producto.class);

	
	private Utiles utiles = new Utiles(modeloMock);
	
	@Test
	public void TestAnnadirProducto() {

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		// Le digo que cuando se llame a ese producto con un string "saludos" y luego le
		// llamo
		when(listaProductosMock.devolverProductoPorString("saludos")).thenReturn(productoMock);

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(listaProductosMock.addProducto(productoMock)).thenReturn(true);

		when(productoMock.toString()).thenReturn("hola");

		resultadoString = utiles.annadirProducto("saludos");

		resultadoEsperadoString = "hola";

		assertEquals(resultadoEsperadoString, resultadoString);
	}
	
	@Test
	public void TestAnnadirPlato() {

		when(modeloMock.getListaPlatos()).thenReturn(listaPlatosMock);

		String input = "Macarroni";
		
		when(listaPlatosMock.devolverPlatoPorString(input)).thenReturn(platoMock);

		when(modeloMock.getListaTemporalPlatos()).thenReturn(listaPlatosMock);

		when(listaPlatosMock.addPlato(platoMock)).thenReturn(true);

		when(platoMock.toString()).thenReturn("mentos");

		resultadoString = utiles.funcionalidadAnnadirPlato(input);

		resultadoEsperadoString = "mentos";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	

	@Test
	public void TestCambiarCantidadProductos() {

		String producto = "1 - Calabaza";

		resultadoString = utiles.funcionalidadCambioProductos(producto, 4);

		resultadoEsperadoString = "5 - Calabaza";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestCantidadTotal() {

		String primer = "2";
		String segun = "3";
		double tercer = 3;

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(listaProductosMock.precioProductoString(segun)).thenReturn(3.0);

		resultadoDouble = utiles.cantidadTotal(primer, segun, tercer, "producto");

		resultadoEsperadoDouble = 9.0;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0.01);

	}

	@Test
	public void TestEliminarProducto() {

		int pos = 0;
		String eliminar = "1 Pepito";
		double total = 19.0;

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.cogerCantidadString(eliminar)).thenReturn(1);

		when(listaProductosMock.getPrecioProducto(pos)).thenReturn(16.0);

		resultadoDouble = utiles.funcionalidadeliminarProducto(pos, eliminar, total);

		resultadoEsperadoDouble = 3.0;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0.01);

	}
	
	@Test
	public void TestEliminarPlato() {

		int pos = 0;
		String eliminar = "1 Pepito";
		double total = 19.0;

		when(modeloMock.getListaTemporalPlatos()).thenReturn(listaPlatosMock);

		when(modeloMock.cogerCantidadString(eliminar)).thenReturn(1);

		when(listaPlatosMock.getPrecioPlato(pos)).thenReturn(16.0);

		resultadoDouble = utiles.funcionalidadeliminarPlato(pos, eliminar, total);

		resultadoEsperadoDouble = 3.0;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0.01);

	}

	@Test
	public void TestDevolverNombreProducto() {

		int i = 1;

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		String[] arrStringProductos = { "PepsiCola", "Zapatilla" };

		when(listaProductosMock.getListaProductosString()).thenReturn(arrStringProductos);

		resultadoString = utiles.devolverNombreProducto(i);

		resultadoEsperadoString = "Zapatilla";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestAccionadoBotonAnadirProducto() {
		String producto = "prod";
		String cantidad = "3";
		double total = 0.0;
		
		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);
		when(listaProductosMock.devolverProductoPorString(producto)).thenReturn(productoMock);
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		when(productoMock.toString()).thenReturn("string");
		when(listaProductosMock.precioProductoString(producto)).thenReturn(3.0);
		
		resultadoArrayString = utiles.funcionalidadAnadirProducto(producto, cantidad, total);
		
		String [] resultadoEsperadoArrayString = new String[2];
		resultadoEsperadoArrayString[0] = "3 string";
		resultadoEsperadoArrayString[1] = "9.0";
		
		assertArrayEquals(resultadoEsperadoArrayString, resultadoArrayString);
	}
	
	@Test
	public void TestAccionadoBotonAnadirPlato() {
		String plato = "prod";
		String cantidad = "3";
		double total = 0.0;
		
		when(modeloMock.getListaPlatos()).thenReturn(listaPlatosMock);
		when(listaPlatosMock.devolverPlatoPorString(plato)).thenReturn(platoMock);
		when(modeloMock.getListaTemporalPlatos()).thenReturn(listaPlatosMock);
		when(platoMock.toString()).thenReturn("string");
		when(listaPlatosMock.precioProductoString(plato)).thenReturn(3.0);
		
		resultadoArrayString = utiles.funcionalidadAnadirPlato(plato, cantidad, total);
		
		String [] resultadoEsperadoArrayString = new String[2];
		resultadoEsperadoArrayString[0] = "3 string";
		resultadoEsperadoArrayString[1] = "9.0";
		
		assertArrayEquals(resultadoEsperadoArrayString, resultadoArrayString);
	}
	
	@Test
	public void cambiarCantidadProductos() {
		String nombreProductoAnadido = "1 producto";
		int cantidadAnadir = 3;
		String nombreProducto = "producto";
		double total = 3.0;
		
		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);
		when(listaProductosMock.precioProductoString(nombreProducto)).thenReturn(3.0);
		
		resultadoArrayString = utiles.cambiarCantidadProductos(nombreProductoAnadido, cantidadAnadir, nombreProducto, total, "producto");
		
		String [] resultadoEsperadoArrayString = new String[2];
		resultadoEsperadoArrayString[0] = "4 producto";
		resultadoEsperadoArrayString[1] = "12.0";
		
		assertArrayEquals(resultadoEsperadoArrayString, resultadoArrayString);
	}
}
