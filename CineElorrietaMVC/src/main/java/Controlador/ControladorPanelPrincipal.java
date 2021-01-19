package Controlador;

import Modelo.Modelo;
import Vista.PanelPrincipal;
import Vista.Vista;

public class ControladorPanelPrincipal {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelPrincipal panelPrincipal;
	
	public ControladorPanelPrincipal(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;	
	}
	
	public void mostrarPanelPrincipal() {
		this.panelPrincipal = new PanelPrincipal(this);
		this.vista.mostrarPanel(this.panelPrincipal);
	}
	
	public void accionadoBottonMostrarPanelPedidos() {
		this.controlador.navegarPanelPedidos();
	}
	
	public void accionadoBottonMostrarPanelAprovisionamiento() {
		this.controlador.navegarPanelAprovisionamiento();
	}
	
	public void accionadoBottonMostrarPanelTickets() {
		this.controlador.navegarPanelTickets();
	}
	
	public void accionadoBottonMostrarPanelFacturas() {
		this.controlador.navegarPanelFacturas();
	}
	
	/*public void accionadoBottonMostrarPanelGeneros() {
		this.controlador.navegarPanelGeneros();
	}*/
	
}
