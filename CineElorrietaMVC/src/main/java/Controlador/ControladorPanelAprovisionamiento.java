package Controlador;

import Modelo.Modelo;
import Vista.PanelAprovisionamiento;
import Vista.Vista;

public class ControladorPanelAprovisionamiento {


	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelAprovisionamiento panelAprovisionamiento;
	
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
	
	public PanelAprovisionamiento makePanelAprovisionamiento(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new PanelAprovisionamiento(controladorPanelAprovisionamiento);
	}
	
	
	
}