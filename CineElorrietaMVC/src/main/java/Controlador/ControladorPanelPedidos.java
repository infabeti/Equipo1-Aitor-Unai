package Controlador;

import javax.swing.JOptionPane;

import Modelo.LineaPedido;
import Modelo.ListaProductos;
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
	
	public String leerNumTransBBDD() {
		
		return String.valueOf(this.modelo.getConexion().leerNumTransBBDD());
		
	}
	
	public String conseguirLocal() {

		return modelo.getUser().getNifLocal();

	}
	
	public void mostrarPanelPedidos() {
		this.panelPedidos = new PanelPedidos(this);
		this.vista.mostrarPanel(this.panelPedidos);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarListTemporal();
	}
	
	public void accionadoBottonFinalizar() {
		this.controlador.navegarPanelPrincipal();
		JOptionPane.showMessageDialog(null, "Finalizado");
	}
	
	
	public String[] pasarListaProductos() {
		ListaProductos listaProd = this.modelo.getListaProductos();
		String[] lista = listaProd.getListaProductosString();
		return lista;
	}
	
	public Producto devolverProducto(String input) {
		ListaProductos listaProd = modelo.getListaProductos();
		Producto p1 = listaProd.devolverProductoPorString(input);
		return p1;
		
	}
	
	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
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
	
	public String cantidadProducto(String cantidad, String productoAnadir) {
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
