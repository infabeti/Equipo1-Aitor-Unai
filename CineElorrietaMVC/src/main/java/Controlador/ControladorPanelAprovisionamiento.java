package Controlador;

import Modelo.Modelo;
import Modelo.ListaProductos;
import Vista.PanelAprovisionamiento;
import Vista.Vista;

public class ControladorPanelAprovisionamiento {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelAprovisionamiento panelAprovisionamiento;
	private ListaProductos listaP;

	public ControladorPanelAprovisionamiento(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public Vista getVista() {
		return vista;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void mostrarPanelAprovisionamiento() {
		this.panelAprovisionamiento = makePanelAprovisionamiento(this);
		this.vista.mostrarPanel(this.panelAprovisionamiento);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}

	public String leerNumTransBBDD() {
		return String.valueOf(this.modelo.getConsultas().leerNumTransBBDD());
	}

	public String conseguirLocal() {

		return modelo.getUser().getNifLocal();
	}

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

	public PanelAprovisionamiento makePanelAprovisionamiento(
			ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new PanelAprovisionamiento(controladorPanelAprovisionamiento);
	}
	
	public String devolverFechaFormateada(String input) {
		return this.modelo.validaciones.devolverFechaFormateada(input);
	}

	public String[] pasarListaProductos() {
		listaP = modelo.getConsultas2().cogerProductosAprovisionamiento();
		return listaP.getListaProductosString();
	}

	public boolean accionadoBotonAnnadir(int cantidad, int indice, String nombre) {
		boolean realizado = modelo.getInserciones().insertarAprovisionamientoProductos(cantidad, indice + 1,
				this.modelo.getUser().getNifLocal());
		modelo.insercionesActividades.insertarActividad(modelo.getConsultas().leerNumTransBBDD(), devolverFechaFormateada(modelo.getFechaHoraSys()), 0, "aprovisionamiento", modelo.getUser().getNifLocal());
		modelo.insercionesActividades.insertarAprovisionamiento(modelo.getConsultas().leerNumTransBBDD()-1);
		modelo.getInserciones().insertarProductoActividad(modelo.getConsultas().leerNumTransBBDD()-1, modelo.getConsultas2().obtenerCodigoAlimentoProducto(nombre), cantidad, 0);
		return realizado;
	}
}