package Controlador;

import Modelo.ListaProductos;
import Modelo.Plato;
import Modelo.ListaPlatos;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.Vista;
import Vista.PanelComandas;

public class ControladorPanelComandas {
	
	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelComandas panelComandas;
	
	public ControladorPanelComandas(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}
	
	public Modelo getModelo() {return this.modelo;}
	public Vista getVista() {return this.vista;}
	public Controlador getControlador() {return this.controlador;}
	
	public void mostrarPanelComandas() {
		this.panelComandas = makePanelComandas(this);
		this.vista.mostrarPanel(this.panelComandas);
	}
	
	public void accionadoBotonVolverPanelPrincipal() {
		this.modelo.getListaTemporal().limpiarLista();
		this.modelo.getListaTemporalPlatos().limpiarLista();
		this.controlador.navegarPanelPrincipal();
	}
	
	public PanelComandas makePanelComandas(ControladorPanelComandas controladorPanelComandas) {
		return new PanelComandas(controladorPanelComandas);
	}
	
	public String[] cogerListaProductos() {
		return modelo.getListaProductos().getListaProductosString();
	}
	
	public String[] cogerListaPlatos() {
		return modelo.getListaPlatos().getListaPlatosString();
	}
	
	public String accionadoBotonAnnadirProducto(String producto) {
		ListaProductos listaProd = modelo.getListaProductos();
		Producto prod = listaProd.devolverProductoPorString(producto);
		ListaProductos listaTemporal = modelo.getListaTemporal();
		listaTemporal.addProducto(prod);
		return prod.toString();
	}
	
	public int existeProducto(String producto) {
		int pos = modelo.getListaTemporal().devolverPosProductoString(producto);
		return pos;
	}
	
	public String cantidadProducto(String cantidad, String productoAnadir) { 
		return cantidad + " " + productoAnadir;
	}
	
	public String cambiarCantidadProductos(String producto, int cantidadAnadir) {
		int pos = 0;
		for (int i = 0; Character.isDigit(producto.charAt(i)); i++) {
			pos = i;
		}
		String cantString = producto.substring(0, pos + 1);
		int cantidad = Integer.parseInt(cantString);
		cantidad = cantidad + cantidadAnadir;
		String cambiada = cantidad + producto.substring(pos + 1);
		return cambiada;
	}
	
	public double cogerPrecioString(String producto) {
		double precio = modelo.getListaTemporal().precioProductoString(producto);
		return precio;
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
		listaProd.eliminarProducto(pos);
		return totalStr;
	}
	
	public int existePlato(String plato) {
		int pos = modelo.getListaTemporalPlatos().devolverPosPlatoString(plato);
		return pos;
	}
	
	public String accionadoBotonAnnadirPlato(String plato) {
		ListaPlatos listaPl = modelo.getListaPlatos();
		Plato plat = listaPl.devolverPlatoPorString(plato);
		ListaPlatos listaTemporal = modelo.getListaTemporalPlatos();
		listaTemporal.addPlato(plat);
		return plato.toString();
	}
	
	public String cantidadTotalPlatos(String cantidad, String total, String plato) {
		ListaPlatos listaPlatos = this.modelo.getListaPlatos();
		int cantidadInt = Integer.parseInt(cantidad);
		double totalDouble = Double.parseDouble(total);
		double precioTotalPlato = cantidadInt * listaPlatos.precioProductoString(plato);
		return String.valueOf(totalDouble + precioTotalPlato);
	}
	
	public double cogerPrecioStringPlato(String plato) {
		double precio = modelo.getListaTemporalPlatos().precioProductoString(plato);
		return precio;
	}
	
	public String accionadoBotonEliminarPlato(int pos, String eliminar, String total) {
		ListaPlatos listaPl = modelo.getListaTemporalPlatos();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaPl.getPrecioPlato(pos);
		double totalDouble = Double.parseDouble(total);
		String totalStr = String.valueOf(totalDouble - (precio * cantidad));
		listaPl.eliminarPlato(pos);
		return totalStr;
	}
	
}
