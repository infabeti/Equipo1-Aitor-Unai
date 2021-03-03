package Controlador;

import Modelo.Modelo;
import Modelo.Producto;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelo.ListaProductos;
import Vista.PanelFacturas;
import Vista.Vista;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ControladorPanelFacturas {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelFacturas panelFacturas;
	private double total;

	public ControladorPanelFacturas(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
		this.total = 0.0;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public Vista getVista() {
		return vista;
	}

	public Controlador getControlador() {
		return controlador;
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
		this.panelFacturas = makePanelFacturas(this);
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
		listaProd.limpiarLista();
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

	public double cogerPrecioString(String producto) {
		double precio = modelo.getListaTemporal().precioProductoString(producto);
		return precio;
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

	public String cantidadProducto(String cantidad, String productoAnadir) { // Este m�todo crea el mensaje para
																				// a�adir en la lista de a�adidos,
																				// el cual se creaba antes en la propia
																				// vista
		return cantidad + " " + productoAnadir;
	}

	public String cantidadTotal(String cantidad, String producto) {
		total = this.modelo.getUtil().cantidadTotal(cantidad, producto, total);
		return String.valueOf(total);
	}

	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.getUtil().accionadoBotonEliminar(pos, eliminar, total);
		return String.valueOf(total);
	}

	public String devolverFechaFormateada(String input) {

		String fecha = this.modelo.getUtil().devolverFechaFormateada(input);

		return fecha;
	}

	public String devolverNombreProducto(int i) {

		ListaProductos listaTemporal = this.modelo.getListaTemporal();

		String[] lista = listaTemporal.getListaProductosString();

		return lista[i];
	}

	public void insertarProductoActividad(String nombreProducto, int transaccion, int cantidad, double preciofinal) {

		String codigoAlimento = this.modelo.getConexion().obtenerCodigoAlimentoProducto(nombreProducto);
		this.modelo.getConexion().insertarProductoActividad(transaccion, codigoAlimento, cantidad, preciofinal);

	}

	public void insertarFactura(int transaccion, String nif) {
		this.modelo.getConexion().insertarFactura(transaccion, nif);
	}

	public boolean comprobarNif(String nif) {

		boolean correcto = this.modelo.getUtil().comprobarNif(nif);

		return correcto;

	}

	public boolean comprobarFormatoNombre(String nombre) {

		boolean correcto = this.modelo.getUtil().comprobarFormatoNombre(nombre);

		return correcto;
	}

	public boolean comprobarFormatoApellido(String apellido) {

		boolean correcto = this.modelo.getUtil().comprobarFormatoApellido(apellido);

		return correcto;
	}

	public void insertarComprador(String nif, String nombre, String apellido) {

		this.modelo.getConexion().insertarComprador(nif, nombre, apellido);

	}

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String nif) {
		String fechaFormateada = devolverFechaFormateada(fecha);
		this.modelo.getConexion().insertarActividad(transaccion, fechaFormateada, totalOperacion, nif);
	}

	public boolean contieneSoloLetras(String cadena) {
		boolean correcto = this.modelo.getUtil().contieneSoloLetras(cadena);

		return correcto;
	}

	public PanelFacturas makePanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		return new PanelFacturas(controladorPanelFacturas);
	}
}