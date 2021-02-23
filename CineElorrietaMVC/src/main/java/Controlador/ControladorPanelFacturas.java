package Controlador;

import Modelo.Modelo;
import Modelo.Producto;
import Modelo.ListaProductos;
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

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

	public String conseguirLocal() {

		return modelo.getUser().getNifLocal();

	}

	public String leerNumTransBBDD() {

		return String.valueOf(this.modelo.getConexion().leerNumTransBBDD());

	}

	public void mostrarPanelFacturas() {
		this.panelFacturas = new PanelFacturas(this);
		this.vista.mostrarPanel(this.panelFacturas);
	}

	public String[] cogerListaProductos() {
		ListaProductos listaProd = this.modelo.getListaProductos();
		String[] lista = listaProd.getListaProductosString();
		return lista;
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarListTemporal();
	}

	public String accionadoBotonAnnadirProducto(String producto) {
		ListaProductos listaProd = modelo.getListaProductos();
		Producto prod = listaProd.devolverProductoPorString(producto);
		ListaProductos listaTemporal = modelo.getListaTemporal();
		listaTemporal.addProductoTemporal(prod);
		return prod.toString();
	}
	
	public int existeProducto(String producto) {
		int pos = modelo.getListaTemporal().devolverPosProductoString(producto);
		return pos;
	}
	
	public double cogerPrecioString (String producto) {
		double precio = modelo.getListaTemporal().precioProductoString(producto);
		return precio;
	}
	
	public String cambiarCantidadProductos(String producto, int cantidadAnadir) {
		int pos = 0;
		for(int i = 0; Character.isDigit(producto.charAt(i));i++) {
			pos = i;
		}
		String cantString = producto.substring(0, pos+1);
		int cantidad = Integer.parseInt(cantString);
		cantidad = cantidad + cantidadAnadir;
		String cambiada = cantidad + producto.substring(pos+1);
		return cambiada;
	}

	public String cantidadProducto(String cantidad, String productoAnadir) { // Este m�todo crea el mensaje para
																				// a�adir en la lista de a�adidos,
																				// el cual se creaba antes en la propia
																				// vista
		return cantidad + " " + productoAnadir;
	}

	public String cantidadTotal(String cantidad, String total, String producto) {
		ListaProductos listaProd = this.modelo.getListaProductos();
		int cantidadInt = Integer.parseInt(cantidad);
		double totalDouble = Double.parseDouble(total);
		double precioTotalProducto = cantidadInt * listaProd.precioProductoString(producto);
		return String.valueOf(totalDouble + precioTotalProducto);
	}

	public String accionadoBotonEliminar(int pos, String eliminar, String total) {
		ListaProductos listaProd = modelo.getListaTemporal();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaProd.getPrecioProducto(pos);
		double totalDouble = Double.parseDouble(total);
		String totalStr = String.valueOf(totalDouble - (precio * cantidad));
		listaProd.eliminarProductoTemporal(pos);
		return totalStr;
	}
}