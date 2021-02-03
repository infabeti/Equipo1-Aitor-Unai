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
		modelo.addProductoTemporal(prod);
		return prod.toString();
	}
	
	public String cantidadProducto(String cantidad, String productoAnadir) { //Este método crea el mensaje para añadir en la lista de añadidos, el cual se creaba antes en la propia vista
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
		int punt = 0;
		for(int i = 0; eliminar.charAt(i)!= ' ';i++) {
			punt = i;
		}
		punt++;
		int cantidad = Integer.parseInt(eliminar.substring(0, punt));
		double precio = Controlador.devolverPrecioProducto(pos);
		double totalDouble = Double.parseDouble(total);
		String totalStr = String.valueOf(totalDouble - (precio * cantidad));
		return totalStr;
	}
}