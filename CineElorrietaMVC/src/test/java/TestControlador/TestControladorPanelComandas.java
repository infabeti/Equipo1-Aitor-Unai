package TestControlador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import Controlador.Controlador;
import Controlador.ControladorPanelComandas;
import Modelo.Conexion;
import Modelo.ListaPlatos;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Usuario;
import Vista.PanelComandas;
import Vista.Vista;

public class TestControladorPanelComandas {

	private Modelo modeloMock = mock(Modelo.class);
	private Vista vistaMock = mock(Vista.class);
	private Controlador controladorMock = mock(Controlador.class);
	private Conexion conexionMock = mock(Conexion.class);
	private Usuario userMock = mock(Usuario.class);
	private ListaProductos listaProductosMock = mock(ListaProductos.class);
	private ListaPlatos listaPlatosMock = mock(ListaPlatos.class);
	
	private ControladorPanelComandas controladorPanelComandas = new ControladorPanelComandas(modeloMock, vistaMock,
			controladorMock);
	
	// Test mostrarPanel
		private PanelComandas panelComandasMock = mock(PanelComandas.class);
		private ControladorPanelComandas spyControladorPanelComandas= spy(
				new ControladorPanelComandas(modeloMock, vistaMock, controladorMock));

	
	@Test
	public void TestAccionadoBottonVolverPanelPrincipal() {

		controladorPanelComandas = new ControladorPanelComandas(modeloMock, vistaMock, controladorMock);

		when(modeloMock.getListaTemporalPlatos()).thenReturn(listaPlatosMock);
		
		when(modeloMock.getListaTemporal()).thenReturn(listaProductosMock);
		
		when(listaPlatosMock.limpiarLista()).thenReturn(true);		

		controladorPanelComandas.accionadoBotonVolverPanelPrincipal();

		verify(controladorMock).navegarPanelPrincipal();
	}
	
	@Test
	public void testMostrarPanelComandas() {

		when(modeloMock.getConexion()).thenReturn(conexionMock);

		when(modeloMock.getListaProductos()).thenReturn(listaProductosMock);

		when(modeloMock.getUser()).thenReturn(userMock);

		when(userMock.getNifLocal()).thenReturn("pepe");

		doReturn(panelComandasMock).when(spyControladorPanelComandas)
				.makePanelComandas(any(ControladorPanelComandas.class));

		spyControladorPanelComandas.mostrarPanelComandas();
		verify(vistaMock).mostrarPanel(panelComandasMock);

	}
	
}
