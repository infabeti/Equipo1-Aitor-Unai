package TestControlador;

import Controlador.*;
import Modelo.Conexion;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Producto;
import Modelo.Usuario;
import Vista.PanelFacturas;
import Vista.Vista;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestControladorPanelFacturas {

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

	private ControladorPanelFacturas controladorPanelFacturas = new ControladorPanelFacturas(modeloMock, vistaMock,
			controladorMock);

	// Test mostrarPanelFacturas
	private PanelFacturas panelFacturasMock = mock(PanelFacturas.class);
	private ControladorPanelFacturas spyControladorPanelFacturas = spy(
			new ControladorPanelFacturas(modeloMock, vistaMock, controladorMock));

	@Test
	public void testConstructorControladorFacturas() {
		assertEquals(controladorPanelFacturas.getControlador(), controladorMock);
		assertEquals(controladorPanelFacturas.getVista(), vistaMock);
		assertEquals(controladorPanelFacturas.getModelo(), modeloMock);
	}

	@Test
	public void testDevolverFechaHora() {
		when(controladorPanelFacturas.devolverFechaHora()).thenReturn("999");

		resultadoString = controladorPanelFacturas.devolverFechaHora();
		resultadoEsperadoString = "999";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testConseguirLocal() {

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		resultadoString = controladorPanelFacturas.conseguirLocal();
		resultadoEsperadoString = "pepe";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testLeerNumTransBBDD() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(conexionMock.leerNumTransBBDD()).thenReturn(69);

		resultadoString = controladorPanelFacturas.leerNumTransBBDD();
		resultadoEsperadoString = "69";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testMostrarPanelFacturas() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		doReturn(panelFacturasMock).when(spyControladorPanelFacturas)
				.makePanelFacturas(any(ControladorPanelFacturas.class));

		spyControladorPanelFacturas.mostrarPanelFacturas();
		verify(vistaMock).mostrarPanel(panelFacturasMock);

	}

	@Test
	public void testCogerListaProductos() {
		// Objeto tipo listaproductos
		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		// array de string
		when(listaProductosMock.getListaProductosString()).thenReturn(listaProductos);

		resultadoEsperadoArrayString = controladorPanelFacturas.cogerListaProductos();

		assertArrayEquals(resultadoEsperadoArrayString, listaProductos);
	}

	@Test
	public void testAccionadoBottonVolverPanelPrincipal() {

		controladorPanelFacturas = new ControladorPanelFacturas(modeloMock, vistaMock, controladorMock);

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		controladorPanelFacturas.accionadoBottonVolverPanelPrincipal();

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

		resultadoString = controladorPanelFacturas.accionadoBotonAnnadirProducto("saludos");

		resultadoEsperadoString = "hola";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void TestExisteProducto() {

		String llamadaMetodo = "Hello";
		
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		
		when(modeloMock.getListaTemporal().devolverPosProductoString(llamadaMetodo)).thenReturn(5);

		resultadoInt = controladorPanelFacturas.existeProducto(llamadaMetodo);
		resultadoEsperadoInt = 5;

		assertEquals(resultadoEsperadoInt, resultadoInt);

	}
	

	@Test
	public void TestCogerPrecioString() {

		
		String llamadaMetodo = "Hello";
		
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		
		when(modeloMock.getListaTemporal().precioProductoString(llamadaMetodo)).thenReturn(35.54);
		
		resultadoDouble = controladorPanelFacturas.cogerPrecioString(llamadaMetodo);
		
		resultadoEsperadoDouble = 35.54;
		
		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0);
		

	}
	
	@Test
	public void TestCambiarCantidadProductos() {
		
		String producto = "1 - Calabaza";

		resultadoString = controladorPanelFacturas.cambiarCantidadProductos(producto, 4);
		
		resultadoEsperadoString = "5 - Calabaza"; 
		
		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestCantidadProducto() {
		
		String primer = "Hola";
		String segun = "que tal";

		resultadoString = controladorPanelFacturas.cantidadProducto(primer, segun);
		
		resultadoEsperadoString = primer + " " + segun; 
		
		assertEquals(resultadoEsperadoString, resultadoString);

	}

}
