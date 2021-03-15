package Controlador;

import Modelo.ListaProductos;
import Modelo.Plato;

import java.text.SimpleDateFormat;

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
	
	
	public String devolverNombreProducto(int i) {
		
		ListaProductos listaTemporal = this.modelo.getListaTemporal();
		
		String[] lista = listaTemporal.getListaProductosString();		
		
		return lista[i];
	}
	
	public void insertarProductoActividad(String nombreProducto, int transaccion, int cantidad, double preciofinal) {
		// aqui necesitamos cambiar el nombreproducto por el CodigoAlimento
		// consulta a bbdd comparando el nombre para sacar el codalimento
		String codigoAlimento = this.modelo.getConsultas2().obtenerCodigoAlimentoProducto(nombreProducto);
		this.modelo.getInserciones().insertarProductoActividad(transaccion, codigoAlimento, cantidad, preciofinal);

	}
	
	public void insertarPlatoActividad(String nombrePlato, int transaccion, int cantidad) {
		String codigoPlato = this.modelo.getConsultas2().obtenerCodigoPlato(nombrePlato);
		this.modelo.getInserciones().insertarPlatoActividad(transaccion, codigoPlato, cantidad);

	}
	
	public String devolverFechaFormateada(String input) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateInString = input;

		try {

			java.util.Date date1 = formatter.parse(dateInString);
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	public String conseguirLocal() {
		return modelo.getUser().getNifLocal();
	}
	
	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}
	
	public String leerNumTransBBDD() {
		return String.valueOf(this.modelo.getConsultas().leerNumTransBBDD());
	}
	
	public void insertarComanda(int transaccion, String fecha, double totalOperacion, String nif) {
		this.modelo.getInserciones().insertarActividad(transaccion, fecha, totalOperacion,"COMANDA", nif);
		this.modelo.getInserciones().insertarComanda(transaccion);
	}
	
	public String devolverNombrePlato(int i) {
		
		ListaPlatos listaTemporal = this.modelo.getListaTemporalPlatos();
		
		String[] lista = listaTemporal.getListaPlatosString();		
		
		return lista[i];
	}
}
