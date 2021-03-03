package Controlador;

import Modelo.ListaProductos;
import Modelo.Modelo;

import Vista.PanelTickets;
import Vista.Vista;

public class ControladorPanelTickets {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelTickets panelTickets;
	private double total;

	public ControladorPanelTickets(Modelo modelo, Vista vista, Controlador controlador) {
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

	public void mostrarPanelTickets() {
		this.panelTickets = makePanelTickets(this);
		this.vista.mostrarPanel(this.panelTickets);
	}

	public String leerNumTransBBDD() {

		return String.valueOf(this.modelo.getConexion().leerNumTransBBDD());

	}

	public void insertarTicket(int transaccion, String fecha, double totalOperacion, String nif) {
		String fechaFormateada = devolverFechaFormateada(fecha);
		this.modelo.getConexion().insertarActividad(transaccion, fechaFormateada, totalOperacion, nif);
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {

		String producto = devolverNombreProducto(nombreProducto);
		double precioFinal = cogerPrecioString(producto);

		String codigoAlimento = this.modelo.getConexion().obtenerCodigoAlimentoProducto(producto);
		this.modelo.getConexion().insertarProductoActividad(transaccion, codigoAlimento, cantidad, precioFinal);

	}

	public String conseguirLocal() {

		return modelo.getUser().getNifLocal();

	}

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarLista();
		this.total = 0.0;
	}

	public String[] cogerListaProductos() {
		return this.modelo.getUtil().pasarListaProductos();
	}

	// M�todos para la l�gica de a�adir un producto

	public String accionadoBotonAnnadirProducto(String producto) {
		return this.modelo.getUtil().accionadoBotonAnnadirProducto(producto);
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
		return this.modelo.getUtil().cambiarCantidadProductos(producto, cantidadAnadir);
	}

	public String cantidadProducto(String cantidad, String productoAnadir) { // Este m�todo crea el mensaje para
																				// a�adir en la lista de a�adidos,
																				// el cual se creaba antes en la propia
																				// vista
		return cantidad + " " + productoAnadir;
	}

	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.getUtil().accionadoBotonEliminar(pos, eliminar, total);
		return String.valueOf(total);
	}

	// M�todo para la l�gica de eliminar un producto

	public String cantidadTotal(String cantidad, String producto) {
		total = this.modelo.getUtil().cantidadTotal(cantidad, producto, total);
		return String.valueOf(total);
	}

	public String devolverFechaFormateada(String input) {

		String fecha = this.modelo.getUtil().devolverFechaFormateada(input);

		return fecha;
	}

	public String devolverNombreProducto(int i) {

		return this.modelo.getUtil().devolverNombreProducto(i);
	}

	public PanelTickets makePanelTickets(ControladorPanelTickets controladorPanelTickets) {
		return new PanelTickets(controladorPanelTickets);
	}

}