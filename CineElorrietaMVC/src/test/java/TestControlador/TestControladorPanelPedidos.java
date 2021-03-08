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
import Modelo.Usuario;
import Modelo.Utiles;
import Vista.PanelPedidos;
import Vista.Vista;

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

	private String[] resultadoEsperadoArrayString, resultadoArrayString;
	private String[] listaProductos;
	private Utiles utilesMock = mock(Utiles.class);

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
		
		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.annadirProducto("saludos")).thenReturn("bocata de calamares");
		
		when(utilesMock.cantidadTotal("1", "saludos", 0.0)).thenReturn(0.0);

		resultadoArrayString = controladorPanelPedidos.accionadoBotonAnnadirProducto("saludos", "1");
		
		resultadoEsperadoArrayString = new String[2];

		resultadoEsperadoArrayString[0] = "1 bocata de calamares";
		
		resultadoEsperadoArrayString[1] = "0.0";

		assertArrayEquals(resultadoEsperadoArrayString, resultadoArrayString);
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
		
		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.cambiarCantidadProductos(producto, 4)).thenReturn("ZAPATO");
		
		when(utilesMock.cantidadTotal("4", producto, 2.0)).thenReturn(0.0);

		resultadoArrayString = controladorPanelPedidos.cambiarCantidadProductos(producto, 4, producto);

		resultadoEsperadoArrayString = new String[2];
		resultadoEsperadoArrayString[0]= "ZAPATO";
		resultadoEsperadoArrayString[1] = "0.0";

		assertArrayEquals(resultadoEsperadoArrayString, resultadoArrayString);

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
		double tercer = 0;
		
		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.cantidadTotal(primer, segun, tercer)).thenReturn(999.0);

		resultadoString = controladorPanelPedidos.cantidadTotal(primer, segun);

		resultadoEsperadoString = "999.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestAccionadoBotonEliminar() {

		int pos = 0;
		String eliminar = "1 Anfeta";

		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.eliminarProducto(pos, eliminar, pos)).thenReturn(99.0);
	
		resultadoString = controladorPanelPedidos.accionadoBotonEliminar(pos, eliminar);

		resultadoEsperadoString = "99.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void testPasarListaProductos() {
		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);
		
		when(listaProductosMock.getListaProductosString()).thenReturn(listaProductos);

		resultadoEsperadoArrayString = controladorPanelPedidos.cogerListaProductos();

		assertArrayEquals(resultadoEsperadoArrayString, listaProductos);
	}
	
	@Test
	public void TestDevolverFechaFormateada() {

		String input = "colchon";

		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.devolverFechaFormateada(input)).thenReturn("pedro");
	
		resultadoString = controladorPanelPedidos.devolverFechaFormateada(input);

		resultadoEsperadoString = "pedro";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestDevolverNombreProducto() {
		
		int i = 2;
		
		when(modeloMock.util).thenReturn(utilesMock);
		
		when(utilesMock.devolverNombreProducto(i)).thenReturn("solero");
		
		resultadoString = controladorPanelPedidos.devolverNombreProducto(i);

		resultadoEsperadoString = "solero";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	

	
}
