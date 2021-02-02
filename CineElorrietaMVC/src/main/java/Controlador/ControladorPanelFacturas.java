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
		modelo.limpiarListaTemporal();
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		return prod.toString();
	}
	
	public void accionadoBotonEliminar(int pos) {
		modelo.eliminiarProductoTemporal(pos);
	}
}