package Controlador;
import Modelo.ListaProductos;

import javax.swing.DefaultListModel;

import Modelo.ListaPlatos;
import Modelo.Modelo;
import Vista.Vista;
import Vista.PanelComandas;
public class ControladorPanelComandas {
	
	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelComandas panelComandas;
	private double total;
	
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
		this.total = 0.0;
	}
	
	public PanelComandas makePanelComandas(ControladorPanelComandas controladorPanelComandas) {
		return new PanelComandas(controladorPanelComandas);
	}
	
	public String[] cogerListaProductos() {
		return modelo.getListaProductos().getListaProductosString();
	}
	
	public int conseguirStockProductos(String nif, String producto) {
		return this.modelo.getConsultas().obtenerStock(nif, producto);
	}
	
	public String[] cogerListaPlatos() {
		return modelo.getListaPlatos().getListaPlatosString();
	}
	
	public String[] accionadoBotonAnnadirProducto(String producto, String cantidad) {
		String[] devolver = this.modelo.util.funcionalidadAnadirProducto(producto, cantidad, this.total);
		this.total = Double.parseDouble(devolver[1]);
		return devolver;
	}
	
	public int existeProducto(String nombreProducto) {
		return this.modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
	}
	
	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto, String tipo) {
		String[] devolver = this.modelo.util.cambiarCantidadProductos(nombreProductoAnadido, cantidadAnadir, nombreProducto, this.total, tipo);
		this.total = Double.parseDouble(devolver[1]);
		return devolver;
	}
	
	public double cogerPrecioString(String nombreProducto) {
		return this.modelo.getListaTemporal().precioProductoString(nombreProducto);
	}
	
	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.util.funcionalidadeliminarProducto(pos, eliminar, this.total);
		return String.valueOf(total);
	}
	
	public int existePlato(String plato) {
		int pos = modelo.getListaTemporalPlatos().devolverPosPlatoString(plato);
		return pos;
	}
	
	public String[] accionadoBotonAnnadirPlato(String plato, String cantidad) {
		String[] devolver = this.modelo.util.funcionalidadAnadirPlato(plato, cantidad, this.total);
		this.total = Double.parseDouble(devolver[1]);
		return devolver;
	}
	
	public double cogerPrecioStringPlato(String plato) {
		double precio = modelo.getListaTemporalPlatos().precioProductoString(plato);
		return precio;
	}
	
	public String accionadoBotonEliminarPlato(int pos, String eliminar) {
		this.total = this.modelo.util.funcionalidadeliminarPlato(pos, eliminar, this.total);

		return String.valueOf(total);
	}
	
	public String devolverNombreProducto(int i) {
		ListaProductos listaTemporal = this.modelo.getListaTemporal();
		String[] lista = listaTemporal.getListaProductosString();		
		return lista[i];
	}
	
	public void insertarProductoActividad(String nombreProducto, int transaccion, int cantidad, double preciofinal) {
		String codigoAlimento = this.modelo.getConsultas().obtenerCodigoAlimentoProducto(nombreProducto);
		this.modelo.getInserciones().insertarProductoActividad(transaccion, codigoAlimento, cantidad, preciofinal);
	}
	
	public void insertarPlatoActividad(String nombrePlato, int transaccion, int cantidad) {
		String codigoPlato = this.modelo.getConsultas().obtenerCodigoPlato(nombrePlato);
		this.modelo.getInserciones().insertarPlatoActividad(transaccion, codigoPlato, cantidad);
	}
	
	public String devolverFechaFormateada(String input) {
		return this.modelo.validaciones.devolverFechaFormateada(input);
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
	
	public void insertarComanda(int transaccion, String fecha, double totalOperacion, String nif, DefaultListModel<String> listaProductos, DefaultListModel<String> listaPlatos) {
		this.modelo.insercionesActividades.insertarActividad(transaccion, devolverFechaFormateada(fecha), totalOperacion,"COMANDA", nif);
		this.modelo.insercionesActividades.insertarComanda(transaccion);
		for (int i = 0; i < listaProductos.getSize(); i++) {
			String textoRecogido = listaProductos.get(i);
			String textoSpliteado[] = textoRecogido.split(" ");
			String producto = devolverNombreProducto(i);
			insertarProductoActividad(producto, transaccion, Integer.parseInt(textoSpliteado[0]),
					cogerPrecioString(producto));
		}
		for (int i = 0; i < listaPlatos.getSize(); i++) {
			String textoRecogido = listaPlatos.get(i);
			String textoSpliteado[] = textoRecogido.split(" ");
			insertarPlatoActividad(devolverNombrePlato(i), transaccion, Integer.parseInt(textoSpliteado[0]));
		}
	}
	
	public String devolverNombrePlato(int i) {
		ListaPlatos listaTemporal = this.modelo.getListaTemporalPlatos();
		String[] lista = listaTemporal.getListaPlatosString();		
		return lista[i];
	}
}
