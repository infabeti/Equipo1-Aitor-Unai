package TestControlador;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import Controlador.Controlador;
import Controlador.ControladorPanelFacturas;
import Controlador.ControladorPanelRegistro;
import Modelo.Conexion;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Producto;
import Modelo.Usuario;
import Vista.PanelFacturas;
import Vista.PanelRegistro;
import Vista.Vista;

public class TestControladorPanelRegistro {

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

	private ControladorPanelRegistro controladorPanelRegistro = new ControladorPanelRegistro(modeloMock, vistaMock,
			controladorMock);

	// Test mostrarPanelRegistro
	private PanelRegistro panelRegistroMock = mock(PanelRegistro.class);
	private ControladorPanelRegistro spyControladorPanelRegistro = spy(
			new ControladorPanelRegistro(modeloMock, vistaMock, controladorMock));

	@Test
	public void testConstructorControladorRegistro() {
		assertEquals(controladorPanelRegistro.getControlador(), controladorMock);
		assertEquals(controladorPanelRegistro.getVista(), vistaMock);
		assertEquals(controladorPanelRegistro.getModelo(), modeloMock);
	}
	
	@Test
	public void testMostrarPanelRegistro() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		doReturn(panelRegistroMock).when(spyControladorPanelRegistro)
				.makePanelRegistro(any(ControladorPanelRegistro.class));

		spyControladorPanelRegistro.mostrarPanelRegistro();
		verify(vistaMock).mostrarPanel(panelRegistroMock);

	}
	
	@Test
	public void TestComprobarNifTRUE() {

		String nif = "12345678M";

		resultadoBoolean = controladorPanelRegistro.comprobarNif(nif);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarNifFALSE() {

		String nif = "ESTO NO ES UN NIF POR LO QUE ME DEVOLVERA FALSO";

		resultadoBoolean = controladorPanelRegistro.comprobarNif(nif);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTODOTRUE() {

		String nombre = "Pepito";

		when(spyControladorPanelRegistro.contieneSoloLetras(nombre)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTODOFALSE() {

		String nombre = "1a";

		when(spyControladorPanelRegistro.contieneSoloLetras(nombre)).thenReturn(false);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreTRUEFALSE() {

		String nombre = "asdasdasdasdasdasdasdasdasdasdasdasdad";

		when(spyControladorPanelRegistro.contieneSoloLetras(nombre)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoNombreSEGUNDOIFFALSO() {

		String nombre = "ab";

		when(spyControladorPanelRegistro.contieneSoloLetras(nombre)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoNombre(nombre);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTODOTRUE() {

		String apellido = "Pepito";

		when(spyControladorPanelRegistro.contieneSoloLetras(apellido)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTODOFALSE() {

		String apellido = "P1";

		when(spyControladorPanelRegistro.contieneSoloLetras(apellido)).thenReturn(false);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoTRUEFALSE() {

		String apellido = "ALFJKSDYHWBFMDKWENJJJFIJRHDUFIWELFNUIFGIOENFGOGNM";

		when(spyControladorPanelRegistro.contieneSoloLetras(apellido)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestComprobarFormatoApellidoSEGUNDOIFFALSO() {

		String apellido = "A";

		when(spyControladorPanelRegistro.contieneSoloLetras(apellido)).thenReturn(true);

		resultadoBoolean = controladorPanelRegistro.comprobarFormatoApellido(apellido);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestContieneSoloLetras() {

		String input = "hola";

		resultadoBoolean = controladorPanelRegistro.contieneSoloLetras(input);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}

	@Test
	public void TestContieneSoloLetrasFALSE() {

		String input = "123";

		resultadoBoolean = controladorPanelRegistro.contieneSoloLetras(input);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarContraNoVaciaTRUE() {

		String input = "123456";

		resultadoBoolean = controladorPanelRegistro.comprobarContraNoVacia(input);

		resultadoEsperadoBoolean = true;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
	@Test
	public void TestComprobarContraNoVaciaFALSE() {

		String input = "abc";

		resultadoBoolean = controladorPanelRegistro.comprobarContraNoVacia(input);

		resultadoEsperadoBoolean = false;

		assertEquals(resultadoEsperadoBoolean, resultadoBoolean);

	}
	
}
