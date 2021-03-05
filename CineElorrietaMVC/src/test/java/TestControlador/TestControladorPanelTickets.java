package TestControlador;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import Controlador.Controlador;
import Controlador.ControladorPanelTickets;
import Modelo.Conexion;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Usuario;
import Modelo.Utiles;
import Vista.PanelTickets;
import Vista.Vista;

public class TestControladorPanelTickets {

	private Modelo modeloMock = mock(Modelo.class);
	private Vista vistaMock = mock(Vista.class);
	private Controlador controladorMock = mock(Controlador.class);
	private String resultadoEsperadoString, resultadoString;
	private int resultadoEsperadoInt, resultadoInt;
	private double resultadoEsperadoDouble, resultadoDouble;
	private Usuario userMock = mock(Usuario.class);
	private Conexion conexionMock = mock(Conexion.class);
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private String[] resultadoEsperadoArrayString;
	private String[] listaProductos;
	private Utiles utilesMock = mock(Utiles.class);

	private ControladorPanelTickets controladorPanelTickets = new ControladorPanelTickets(modeloMock, vistaMock,
			controladorMock);

	// Test mostrarPanelFacturas
	private PanelTickets panelTicketsMock = mock(PanelTickets.class);
	private ControladorPanelTickets spyControladorPanelTickets = spy(
			new ControladorPanelTickets(modeloMock, vistaMock, controladorMock));

	@Test
	public void testConstructorControladorFacturas() {
		assertEquals(controladorPanelTickets.getControlador(), controladorMock);
		assertEquals(controladorPanelTickets.getVista(), vistaMock);
		assertEquals(controladorPanelTickets.getModelo(), modeloMock);
	}

	@Test
	public void testDevolverFechaHora() {
		when(controladorPanelTickets.devolverFechaHora()).thenReturn("999");

		resultadoString = controladorPanelTickets.devolverFechaHora();
		resultadoEsperadoString = "999";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testConseguirLocal() {

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		resultadoString = controladorPanelTickets.conseguirLocal();
		resultadoEsperadoString = "pepe";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testLeerNumTransBBDD() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(conexionMock.leerNumTransBBDD()).thenReturn(69);

		resultadoString = controladorPanelTickets.leerNumTransBBDD();
		resultadoEsperadoString = "69";

		assertEquals(resultadoEsperadoString, resultadoString);
	}

	@Test
	public void testMostrarPanelFacturas() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		doReturn(panelTicketsMock).when(spyControladorPanelTickets)
				.makePanelTickets(any(ControladorPanelTickets.class));

		spyControladorPanelTickets.mostrarPanelTickets();
		verify(vistaMock).mostrarPanel(panelTicketsMock);

	}

	@Test
	public void testCogerListaProductos() {
when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);
		
		when(listaProductosMock.getListaProductosString()).thenReturn(listaProductos);

		resultadoEsperadoArrayString = controladorPanelTickets.cogerListaProductos();

		assertArrayEquals(resultadoEsperadoArrayString, listaProductos);
	}

	@Test
	public void testAccionadoBottonVolverPanelPrincipal() {

		controladorPanelTickets = new ControladorPanelTickets(modeloMock, vistaMock, controladorMock);

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		controladorPanelTickets.accionadoBottonVolverPanelPrincipal();

		verify(controladorMock, times(1)).navegarPanelPrincipal();

	}

	/*@Test
	public void TestAccionadoBotonAnnadirProducto() {

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.annadirProducto("saludos")).thenReturn("bocata de calamares");

		resultadoString = controladorPanelTickets.accionadoBotonAnnadirProducto("saludos");

		resultadoEsperadoString = "bocata de calamares";

		assertEquals(resultadoEsperadoString, resultadoString);
	}*/

	@Test
	public void TestExisteProducto() {

		String llamadaMetodo = "Hello";

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.getListaTemporal().devolverPosProductoString(llamadaMetodo)).thenReturn(5);

		resultadoInt = controladorPanelTickets.existeProducto(llamadaMetodo);
		resultadoEsperadoInt = 5;

		assertEquals(resultadoEsperadoInt, resultadoInt);

	}

	@Test
	public void TestCogerPrecioString() {

		String llamadaMetodo = "Hello";

		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);

		when(modeloMock.getListaTemporal().precioProductoString(llamadaMetodo)).thenReturn(35.54);

		resultadoDouble = controladorPanelTickets.cogerPrecioString(llamadaMetodo);

		resultadoEsperadoDouble = 35.54;

		assertEquals(resultadoEsperadoDouble, resultadoDouble, 0);

	}

	/*@Test
	public void TestCambiarCantidadProductos() {

		String producto = "1 - Calabaza";

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.cambiarCantidadProductos(producto, 4)).thenReturn("ZAPATO");

		resultadoString = controladorPanelTickets.cambiarCantidadProductos(producto, 4);

		resultadoEsperadoString = "ZAPATO";

		assertEquals(resultadoEsperadoString, resultadoString);

	}*/

	@Test
	public void TestCantidadProducto() {

		String primer = "Hola";
		String segun = "que tal";

		resultadoString = controladorPanelTickets.cantidadProducto(primer, segun);

		resultadoEsperadoString = primer + " " + segun;

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestCantidadTotal() {

		String primer = "2";
		String segun = "3";
		double tercer = 0;

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.cantidadTotal(primer, segun, tercer)).thenReturn(999.0);

		resultadoString = controladorPanelTickets.cantidadTotal(primer, segun);

		resultadoEsperadoString = "999.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestAccionadoBotonEliminar() {

		int pos = 0;
		String eliminar = "1 Anfeta";

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.eliminarProducto(pos, eliminar, pos)).thenReturn(99.0);

		resultadoString = controladorPanelTickets.accionadoBotonEliminar(pos, eliminar);

		resultadoEsperadoString = "99.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestDevolverFechaFormateada() {

		String input = "colchon";

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.devolverFechaFormateada(input)).thenReturn("pedro");

		resultadoString = controladorPanelTickets.devolverFechaFormateada(input);

		resultadoEsperadoString = "pedro";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestDevolverNombreProducto() {

		int i = 2;

		when(modeloMock.getUtil()).thenReturn(utilesMock);

		when(utilesMock.devolverNombreProducto(i)).thenReturn("solero");

		resultadoString = controladorPanelTickets.devolverNombreProducto(i);

		resultadoEsperadoString = "solero";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

}
