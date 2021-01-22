package Controlador;

import Modelo.*;
import Vista.PanelPedidos;
import Vista.PanelProductos;
import Vista.Vista;

public class ControladorPanelProductos {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelProductos panelProductos;
	private String[] listaProductos;
	
	
	
	public ControladorPanelProductos(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;	
	}
	
	public void mostrarPanelProductos() {
		this.panelProductos = new PanelProductos(this);
		this.vista.mostrarPanel(this.panelProductos);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}
	
	public String[] pasarListaProductos() {
		listaProductos = modelo.getListaProductos();
		return listaProductos;
	}
	
	
	
	
	
	
}