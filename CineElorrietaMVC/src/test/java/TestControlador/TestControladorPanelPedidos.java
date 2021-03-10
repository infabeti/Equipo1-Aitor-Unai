package TestControlador;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


import org.junit.Test;


import Controlador.Controlador;

import Controlador.ControladorPanelPedidos;


import Modelo.Conexion;

import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Producto;
import Modelo.Usuario;

import Vista.PanelPedidos;
import Vista.Vista;

//Mockito Runner para junit
//@RunWith(MockitoJUnitRunner.class)
public class TestControladorPanelPedidos {

	private Modelo modeloMock = mock(Modelo.class);
	private Vista vistaMock = mock(Vista.class);
	private Controlador controladorMock = mock(Controlador.class);
	private String resultadoEsperadoString, resultadoString;
	private int resultadoEsperadoInt, resultadoInt;

	private double resultadoEsperadoDouble, resultadoDouble;
	private Usuario userMock = mock(Usuario.class);
	private Conexion conexionMock = mock(Conexion.class);
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private Producto productoMock = mock(Producto.class);
	private String[] resultadoEsperadoArrayString;
	private String[] listaProductos;

	private ControladorPanelPedidos controladorPanelPedidos = new ControladorPanelPedidos(modeloMock, vistaMock,
			controladorMock);

	// Test mostrarPanelPedidos
	private PanelPedidos panelPedidosMock = mock(PanelPedidos.class);
	private ControladorPanelPedidos spyControladorPanelPedidos = spy(
			new ControladorPanelPedidos(modeloMock, vistaMock, controladorMock));

	@Test
	public void testConstructorControladorPedidos() {
		assertEquals(controladorPanelPedidos.getControlador(), controladorMock);
		assertEquals(controladorPanelPedidos.getVista(), vistaMock);
		assertEquals(controladorPanelPedidos.getModelo(), modeloMock);
	}

	@Test
	public void testDevolverFechaHora() {
		when(controladorPanelPedidos.devolverFechaHora()).thenReturn("999");

		resultadoString = controladorPanelPedidos.devolverFechaHora();
		resultadoEsperadoString = "999";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testConseguirLocal() {

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		resultadoString = controladorPanelPedidos.conseguirLocal();
		resultadoEsperadoString = "pepe";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testMostrarPanelPedidos() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		doReturn(panelPedidosMock).when(spyControladorPanelPedidos)
				.makePanelPedidos(any(ControladorPanelPedidos.class));

		spyControladorPanelPedidos.mostrarPanelPedidos();
		verify(vistaMock).mostrarPanel(panelPedidosMock);

	}


	@Test
	public void testAccionadoBottonVolverPanelPrincipal() {

		controladorPanelPedidos = new ControladorPanelPedidos(modeloMock, vistaMock, controladorMock);

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		controladorPanelPedidos.accionadoBottonVolverPanelPrincipal();

		verify(controladorMock, times(1)).navegarPanelPrincipal();

	}

	@Test
	public void TestAccionadoBotonAnnadirProducto() {

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		// Le digo que cuando se llame a ese producto con un string "saludos" y luego le
		// llamo
		when(listaProductosMock.devolverProductoPorString("saludos")).thenReturn(productoMock);

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(listaProductosMock.addProducto(productoMock)).thenReturn(true);

		when(productoMock.toString()).thenReturn("hola");

		resultadoString = controladorPanelPedidos.accionadoBotonAnnadirProducto("saludos");

		resultadoEsperadoString = "hola";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void TestExisteProducto() {

		String llamadaMetodo = "Hello";

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.getListaTemporal().devolverPosProductoString(llamadaMetodo)).thenReturn(5);

		resultadoInt = controladorPanelPedidos.existeProducto(llamadaMetodo);
		resultadoEsperadoInt = 5;

		assertEquals(resultadoEsperadoInt, resultadoInt);

	}

	@Test
	public void TestCogerPrecioString() {

		String llamadaMetodo = "Hello";

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.getListaTemporal().precioProductoString(llamadaMetodo)).thenReturn(35.54);

		resultadoDouble = controladorPanelPedidos.cogerPrecioString(llamadaMetodo);

		resultadoEsperadoDouble = 35.54;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0);

	}

	@Test
	public void TestCambiarCantidadProductos() {

		String producto = "1 - Calabaza";

		resultadoString = controladorPanelPedidos.cambiarCantidadProductos(producto, 4);

		resultadoEsperadoString = "5 - Calabaza";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestCantidadProducto() {

		String primer = "Hola";
		String segun = "que tal";

		resultadoString = controladorPanelPedidos.cantidadProducto(primer, segun);

		resultadoEsperadoString = primer + " " + segun;

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestCantidadTotal() {

		String primer = "2";
		String segun = "3";
		String tercer = "Coca-Cola";

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(listaProductosMock.precioProductoString(tercer)).thenReturn(3.0);

		resultadoString = controladorPanelPedidos.cantidadTotal(primer, segun, tercer);

		resultadoEsperadoString = "9.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestAccionadoBotonEliminar() {

		int pos = 0;
		String eliminar = "1 Anfeta";
		String total = "19.0";

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.cogerCantidadString(eliminar)).thenReturn(1);

		when(listaProductosMock.getPrecioProducto(pos)).thenReturn(16.0);

		resultadoString = controladorPanelPedidos.accionadoBotonEliminar(pos, eliminar, total);

		resultadoEsperadoString = "3.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void testPasarListaProductos() {
		// Objeto tipo listaproductos
		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		// array de string
		when(listaProductosMock.getListaProductosString()).thenReturn(listaProductos);

		resultadoEsperadoArrayString = controladorPanelPedidos.pasarListaProductos();

		assertArrayEquals(resultadoEsperadoArrayString, listaProductos);
	}

	@Test
	public void TestDevolverFechaFormateada() {

		resultadoString = controladorPanelPedidos.devolverFechaFormateada("01/03/2021 21:12");

		resultadoEsperadoString = "2021-03-01 21:12";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestDevolverFechaFormateadaCatchException() throws Exception {

		resultadoString = controladorPanelPedidos.devolverFechaFormateada(null);

	}
	
	@Test
	public void TestDevolverNombreProducto() {
		
		int i = 1;
		
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		
		String[] arrStringProductos = {"PepsiCola", "Zapatilla"};
		
		when(listaProductosMock.getListaProductosString()).thenReturn(arrStringProductos);
		
		resultadoString = controladorPanelPedidos.devolverNombreProducto(i);

		resultadoEsperadoString = "Zapatilla";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	

	
}
