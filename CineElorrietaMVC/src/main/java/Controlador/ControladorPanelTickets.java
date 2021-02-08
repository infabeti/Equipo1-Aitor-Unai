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
		modelo.limpiarListaTemporal();
	}
	
	public String[] cogerListaProductos() { 
		String[] lista = this.modelo.getListaProductos();
		return lista;
	}
	
	//M�todos para la l�gica de a�adir un producto
	
	public String accionadoBotonAnnadirProducto(String producto) {
		Producto prod = modelo.devolverProductoPorString(producto);
		modelo.addProductoTemporal(prod);
		return prod.toString();
	}
	
	public String cantidadProducto(String cantidad, String productoAnadir) { //Este m�todo crea el mensaje para a�adir en la lista de a�adidos, el cual se creaba antes en la propia vista
		return cantidad + " " + productoAnadir;
 	}
	
	public String cantidadTotal(String cantidad, String total, String producto) {
		int cantidadInt = Integer.parseInt(cantidad);
		double totalDouble = Double.parseDouble(total);
		double precioTotalProducto = cantidadInt * Controlador.devolverPrecioProducto(producto);
		return String.valueOf(totalDouble + precioTotalProducto);
	}
	
	//M�todo para la l�gica de eliminar un producto
	
	public String accionadoBotonEliminar(int pos, String eliminar, String total) {
		modelo.eliminarProductoTemporal(pos);
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = Controlador.devolverPrecioProducto(pos);
		double totalDouble = Double.parseDouble(total);
		String totalStr = String.valueOf(totalDouble - (precio * cantidad));
		return totalStr;
	}
	
}