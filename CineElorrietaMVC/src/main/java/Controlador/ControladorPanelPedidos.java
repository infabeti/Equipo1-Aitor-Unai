package Controlador;

import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelPedidos;
import Vista.Vista;

public class ControladorPanelPedidos {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelPedidos panelPedidos;
	private String[] listaProductos;
	
	public ControladorPanelPedidos(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;	
	}
	
	public void mostrarPanelPedidos() {
		this.panelPedidos = new PanelPedidos(this);
		this.vista.mostrarPanel(this.panelPedidos);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		modelo.limpiarListaTemporal();
	}
	
	
	public String[] pasarListaProductos() {
		listaProductos = modelo.getListaProductos();
		return listaProductos;
	}
	
	public Producto devolverProducto(String input) {
		
		Producto p1 = modelo.devolverProductoPorString(input);
		return p1;
		
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		modelo.addProductoTemporal(prod);
		return producto.toString();
	}
	
	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}
	
	public void accionadoBotonEliminar(int pos) {
		modelo.eliminarProductoTemporal(pos);
	}
	
}
