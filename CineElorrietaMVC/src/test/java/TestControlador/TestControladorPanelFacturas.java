package TestControlador;

import Controlador.*;
import Modelo.Modelo;
import Modelo.Usuario;
import Vista.Vista;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestControladorPanelFacturas {
	
	private Modelo modeloMock = mock(Modelo.class);
	private Vista vistaMock = mock(Vista.class);
	private Controlador controladorMock = mock(Controlador.class);
	private String resultadoEsperadoString, resultadoString;
	private Usuario userMock = mock(Usuario.class);
	
	private ControladorPanelFacturas controladorPanelFacturas = new ControladorPanelFacturas(modeloMock, vistaMock, controladorMock);
	
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
		
		when(modeloMock.getUser())
		.thenReturn(userMock);
		
		when(userMock.getNifLocal())
		.thenReturn("pepe");
		
		resultadoString = controladorPanelFacturas.conseguirLocal();
		resultadoEsperadoString = "pepe";
		
		assertEquals(resultadoEsperadoString, resultadoString);
	}
	
}
