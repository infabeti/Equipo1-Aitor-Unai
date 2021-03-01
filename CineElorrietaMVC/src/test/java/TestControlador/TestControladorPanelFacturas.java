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
	private boolean resultadoEsperadoBoolean, resultadoBoolean;
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

	@Test
	public void TestCantidadTotal() {

		String primer = "2";
		String segun = "3";
		String tercer = "Coca-Cola";

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(listaProductosMock.precioProductoString(tercer)).thenReturn(3.0);

		resultadoString = controladorPanelFacturas.cantidadTotal(primer, segun, tercer);

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

		resultadoString = controladorPanelFacturas.accionadoBotonEliminar(pos, eliminar, total);

		resultadoEsperadoString = "3.0";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestDevolverFechaFormateada() {

		resultadoString = controladorPanelFacturas.devolverFechaFormateada("01/03/2021 21:12");

		resultadoEsperadoString = "2021-03-01 21:12";

		assertEquals(resultadoEsperadoString, resultadoString);

	}

	@Test
	public void TestDevolverFechaFormateadaCatchException() throws Exception {

		resultadoString = controladorPanelFacturas.devolverFechaFormateada(null);

	}
	
	@Test
	public void TestDevolverNombreProducto() {
		
		int i = 1;
		
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		
		String[] arrStringProductos = {"PepsiCola", "Zapatilla"};
		
		when(listaProductosMock.getListaProductosString()).thenReturn(arrStringProductos);
		
		resultadoString = controladorPanelFacturas.devolverNombreProducto(i);

		resultadoEsperadoString = "Zapatilla";

		assertEquals(resultadoEsperadoString, resultadoString);

	}
	
	@Test
	public void TestComprobarNifTRUE() {
		
		String nif = "12345678M";
		
		resultadoBoolean = controladorPanelFacturas.comprobarNif(nif);

		resultadoEsperadoBoolean = true;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarNifFALSE() {
		
		String nif = "ESTO NO ES UN NIF POR LO QUE ME DEVOLVERA FALSO";
		
		resultadoBoolean = controladorPanelFacturas.comprobarNif(nif);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoNombreTODOTRUE() {
		
		String nombre = "Pepito";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(nombre)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = true;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoNombreTODOFALSE() {
		
		String nombre = "1a";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(nombre)).thenReturn(false);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoNombreTRUEFALSE() {
		
		String nombre = "asdasdasdasdasdasdasdasdasdasdasdasdad";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(nombre)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoNombreSEGUNDOIFFALSO() {
		
		String nombre = "ab";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(nombre)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoApellidoTODOTRUE() {
		
		String apellido = "Pepito";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(apellido)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = true;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoApellidoTODOFALSE() {
		
		String apellido = "P1";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(apellido)).thenReturn(false);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoApellidoTRUEFALSE() {
		
		String apellido = "ALFJKSDYHWBFMDKWENJJJFIJRHDUFIWELFNUIFGIOENFGOGNM";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(apellido)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarFormatoApellidoSEGUNDOIFFALSO() {
		
		String apellido = "A";
		
		when(spyControladorPanelFacturas.contieneSoloLetras(apellido)).thenReturn(true);
		
		resultadoBoolean = controladorPanelFacturas.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;
		
		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}


}
