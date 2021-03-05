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
	
	public PanelAprovisionamiento makePanelAprovisionamiento(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new PanelAprovisionamiento(controladorPanelAprovisionamiento);
	}
	
	public String[] pasarListaProductos() {
		listaP = modelo.getConexion().cogerProductosAprovisionamiento();
		return listaP.getListaProductosString();
	}
	
	public boolean accionadoBotonAnnadir(int cantidad, int indice) {
		boolean realizado = modelo.getConexion().insertarAprovisionamiento(cantidad, indice+1, this.modelo.getUser().getNifLocal());
		return realizado;
	}
	
	
	
}