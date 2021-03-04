package TestControlador;

import Controlador.*;
import Modelo.Conexion;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Usuario;
import Modelo.Utiles;
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
	private boolean resultadoBoolean, resultadoEsperadoBoolean;
	private Usuario userMock = mock(Usuario.class);
	private Conexion conexionMock = mock(Conexion.class);
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private String[] resultadoEsperadoArrayString;
	private String[] listaProductos;
	private Utiles utilesMock = mock(Utiles.class);

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
	
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.pasarListaProductos()).thenReturn(listaProductos);

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
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.accionadoBotonAnnadirProducto("saludos")).thenReturn("bocata de calamares");

		resultadoString = controladorPanelFacturas.accionadoBotonAnnadirProducto("saludos");

		resultadoEsperadoString = "bocata de calamares";

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
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.cambiarCantidadProductos(producto, 4)).thenReturn("ZAPATO");

		resultadoString = controladorPanelFacturas.cambiarCantidadProductos(producto, 4);

		resultadoEsperadoString = "ZAPATO";

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

	@Test
	public void TestCantidadTotal() {

		String primer = "2";
		String segun = "3";
		double tercer = 0;
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.cantidadTotal(primer, segun, tercer)).thenReturn(999.0);

		resultadoString = controladorPanelFacturas.cantidadTotal(primer, segun);

		resultadoEsperadoString = "999.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestAccionadoBotonEliminar() {

		int pos = 0;
		String eliminar = "1 Anfeta";

		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.accionadoBotonEliminar(pos, eliminar, pos)).thenReturn(99.0);
	
		resultadoString = controladorPanelFacturas.accionadoBotonEliminar(pos, eliminar);

		resultadoEsperadoString = "99.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestDevolverFechaFormateada() {

		String input = "colchon";

		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.devolverFechaFormateada(input)).thenReturn("pedro");
	
		resultadoString = controladorPanelFacturas.devolverFechaFormateada(input);

		resultadoEsperadoString = "pedro";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestDevolverNombreProducto() {
		
		int i = 2;
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.devolverNombreProducto(i)).thenReturn("solero");
		
		resultadoString = controladorPanelFacturas.devolverNombreProducto(i);

		resultadoEsperadoString = "solero";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestComprobarNif() {
		
		String nif = "123312";
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.comprobarNif(nif)).thenReturn(false);
		
		resultadoBoolean = controladorPanelFacturas.comprobarNif(nif); 

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoNombre() {
		
		String nombre = "joni";
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.comprobarFormatoNombre(nombre)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoNombre(nombre); 

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoApellido() {
		
		String apellido = "Perro";
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.comprobarFormatoApellido(apellido)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoApellido(apellido); 

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestContieneSoloLetras() {
		
		String input = "123";
		
		when(modeloMock.getUtil()).thenReturn(utilesMock);
		
		when(utilesMock.contieneSoloLetras(input)).thenReturn(false);
		
		resultadoBoolean = controladorPanelFacturas.contieneSoloLetras(input); 

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	

}
