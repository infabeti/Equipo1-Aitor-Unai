package Controlador;

import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelFacturas;
import Vista.Vista;

public class ControladorPanelFacturas {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelFacturas panelFacturas;
	private Producto[] listaProductos = new Producto[256];
	private int punt = 0;
	
	public ControladorPanelFacturas(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;	
	}
	
	public void mostrarPanelFacturas() {
		this.panelFacturas = new PanelFacturas(this);
		this.vista.mostrarPanel(this.panelFacturas);
	}
	
	public String[] cogerListaProductos() { 
		String[] lista = this.modelo.getListaProductos();
		return lista;
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		listaProductos[punt] = prod;
		punt++;
		return prod.toString();
	}
	
}