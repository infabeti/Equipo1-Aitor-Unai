package Controlador;

import Modelo.Modelo;
import Vista.PanelTickets;
import Vista.Vista;
import Modelo.Producto;

public class ControladorPanelTickets {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelTickets panelTickets;
	private Producto[] listaProductos = new Producto[256];
	private int punt = 0;
	
	public ControladorPanelTickets(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;	
	}
	
	public void mostrarPanelTickets() {
		this.panelTickets = new PanelTickets(this);
		this.vista.mostrarPanel(this.panelTickets);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}
	
	public String[] cogerListaProductos() { 
		String[] lista = this.modelo.getListaProductos();
		return lista;
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		listaProductos[punt] = prod;
		punt++;
		return prod.toString();
	}
	
	public void accionadoBotonCrearTicket() {
		
	}
	
}