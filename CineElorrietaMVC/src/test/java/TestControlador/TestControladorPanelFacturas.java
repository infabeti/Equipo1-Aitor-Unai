package TestControlador;

import Controlador.*;
import Modelo.Conexion;
import Modelo.Modelo;
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
	private Usuario userMock = mock(Usuario.class);
	private Conexion conexionMock = mock(Conexion.class);
	private String[] ListaProductosMock = {"Avila","Burgos","León","Palencia","Salamanca",
			   "Segovia","Soria","Valladolid","Zamora"};
	 

	private ControladorPanelFacturas controladorPanelFacturas = new ControladorPanelFacturas(modeloMock, vistaMock, controladorMock);
	
	
	
	// Test mostrarPanelFacturas
		private PanelFacturas panelFacturasMock = mock(PanelFacturas.class); 
		private ControladorPanelFacturas spyControladorPanelFacturas = spy(new ControladorPanelFacturas(modeloMock, vistaMock, controladorMock));
	
	@Test
	public void testDevolverFechaHora() {
		when(controladorPanelFacturas.devolverFechaHora())
		.thenReturn("999");
		
		resultadoString = controladorPanelFacturas.devolverFechaHora();
		resultadoEsperadoString = "999";
		
		assertEquals(resultadoEsperadoString, resultadoString);
	}
	
	@Test
	public void testConseguirLocal() {
		
		when(modeloMock.getUser())
		.thenReturn(userMock);
		
		when(userMock.getNifLocal())
		.thenReturn("pepe");
		
		resultadoString = controladorPanelFacturas.conseguirLocal();
		resultadoEsperadoString = "pepe";
		
		assertEquals(resultadoEsperadoString, resultadoString);
	}
	
	@Test
	public void testLeerNumTransBBDD() {
		
		when(modeloMock.getConexion())
		.thenReturn(conexionMock);
		
		when(conexionMock.leerNumTransBBDD())
		.thenReturn(69);
		
		resultadoString = controladorPanelFacturas.leerNumTransBBDD();
		resultadoEsperadoString = "69";
		
		assertEquals(resultadoEsperadoString, resultadoString);
	}
	
	@Test
	public void testMostrarPanelFacturas() {
		
		doReturn(panelFacturasMock)
    	.when(spyControladorPanelFacturas)
    	.makePanelFacturas(any( ControladorPanelFacturas.class)); 
		
		when(modeloMock.getConexion())
		.thenReturn(conexionMock);
		
		
		/*when(modeloMock.getListaProductos())
		.thenReturn(ListaProductosMock);*/
		
		spyControladorPanelFacturas.mostrarPanelFacturas();
		verify(vistaMock, times(1)).mostrarPanel(panelFacturasMock);
		
	}
	
}
