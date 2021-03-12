package TestModelo;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Producto;

import Modelo.Utiles;


public class TestUtiles {
	
	private Modelo modeloMock = mock(Modelo.class);
	private String resultadoEsperadoString, resultadoString;
	private boolean resultadoEsperadoBoolean, resultadoBoolean;
	private double resultadoEsperadoDouble, resultadoDouble;
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private Producto productoMock = mock(Producto.class);

	
	private Utiles utiles = new Utiles(modeloMock);
	
	@Test
	public void TestAccionadoBotonAnnadirProducto() {

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
	public void TestCambiarCantidadProductos() {

		String producto = "1 - Calabaza";

		resultadoString = utiles.cambioProductos(producto, 4);

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

		resultadoDouble = utiles.cantidadTotal(primer, segun, tercer);

		resultadoEsperadoDouble = 9.0;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0.01);

	}

	@Test
	public void TestAccionadoBotonEliminar() {

		int pos = 0;
		String eliminar = "1 Pepito";
		double total = 19.0;

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.cogerCantidadString(eliminar)).thenReturn(1);

		when(listaProductosMock.getPrecioProducto(pos)).thenReturn(16.0);

		resultadoDouble = utiles.eliminarProducto(pos, eliminar, total);

		resultadoEsperadoDouble = 3.0;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0.01);

	}

	@Test
	public void TestDevolverFechaFormateada() {

		resultadoString = utiles.devolverFechaFormateada("01/03/2021 21:12");

		resultadoEsperadoString = "2021-03-01 21:12";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	

	@Test
	public void TestDevolverFechaFormateadaCatchException() throws Exception {

		resultadoString = utiles.devolverFechaFormateada(null);

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
	public void TestComprobarNifTRUE() {

		String nif = "12345678M";

		resultadoBoolean = utiles.comprobarNif(nif);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarNifFALSE() {

		String nif = "ESTO NO ES UN NIF POR LO QUE ME DEVOLVERA FALSO";

		resultadoBoolean = utiles.comprobarNif(nif);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTODOTRUE() {

		String nombre = "Pepito";

		resultadoBoolean = utiles.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTODOFALSE() {

		String nombre = "1a";

		resultadoBoolean = utiles.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTRUEFALSE() {

		String nombre = "asdasdasdasdasdasdasdasdasdasdasdasdad";

		resultadoBoolean = utiles.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreSEGUNDOIFFALSO() {

		String nombre = "ab";

		resultadoBoolean = utiles.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTODOTRUE() {

		String apellido = "Pepito";
		
		resultadoBoolean = utiles.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTODOFALSE() {

		String apellido = "P1";

		resultadoBoolean = utiles.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTRUEFALSE() {

		String apellido = "ALFJKSDYHWBFMDKWENJJJFIJRHDUFIWELFNUIFGIOENFGOGNM";

		resultadoBoolean = utiles.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoSEGUNDOIFFALSO() {

		String apellido = "A";

		resultadoBoolean = utiles.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestContieneSoloLetrasTRUE() {

		String input = "hola";

		resultadoBoolean = utiles.contieneSoloLetras(input);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestContieneSoloLetrasFALSE() {

		String input = "123";

		resultadoBoolean = utiles.contieneSoloLetras(input);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarCamposStringTODOTRUE() {

		String NIF = "78945632C";
		String nombre = "Joni";
		String apellido = "Mela";
		

		resultadoBoolean = utiles.comprobarCamposString(NIF, nombre, apellido);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarCamposStringTODOFALSE() {

		String NIF = "A1D3";
		String nombre = "12 ";
		String apellido = "2 ";
		

		resultadoBoolean = utiles.comprobarCamposString(NIF, nombre, apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarCamposStringFALSETRUETRUE() {

		String NIF = "A1D3";
		String nombre = "Abraham";
		String apellido = "Burguesa";
		

		resultadoBoolean = utiles.comprobarCamposString(NIF, nombre, apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarCamposStringTRUEFALSETRUE() {

		String NIF = "78965478S";
		String nombre = "1";
		String apellido = "Burguesa";
		

		resultadoBoolean = utiles.comprobarCamposString(NIF, nombre, apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarCamposStringTRUETRUEFALSE() {

		String NIF = "78965478S";
		String nombre = "Arrengamie";
		String apellido = "1";
		

		resultadoBoolean = utiles.comprobarCamposString(NIF, nombre, apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
}
