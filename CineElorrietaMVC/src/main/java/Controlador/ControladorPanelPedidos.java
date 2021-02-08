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
	
	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		modelo.addProductoTemporal(prod);
		return producto.toString();
	}
	
	public String cantidadProducto(String cantidad, String productoAnadir) {
		return cantidad + " " + productoAnadir;
 	}
	
	public String cantidadTotal(String cantidad, String total, String producto) {
		int cantidadInt = Integer.parseInt(cantidad);
		double totalDouble = Double.parseDouble(total);
		double precioTotalProducto = cantidadInt * Controlador.devolverPrecioProducto(producto);
		return String.valueOf(totalDouble + precioTotalProducto);
	}
	
	public String accionadoBotonEliminar(int pos, String eliminar, String total) {
		modelo.eliminarProductoTemporal(pos);
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = Controlador.devolverPrecioProducto(pos);
		double totalDouble = Double.parseDouble(total);
		String totalStr = String.valueOf(totalDouble - (precio * cantidad));
		return totalStr;
	}
	
}
