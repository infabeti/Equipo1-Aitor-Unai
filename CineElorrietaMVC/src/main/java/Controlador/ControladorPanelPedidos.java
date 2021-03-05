package Controlador;

import Modelo.ListaProductos;
import Modelo.Modelo;

import Vista.PanelPedidos;
import Vista.Vista;

public class ControladorPanelPedidos {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelPedidos panelPedidos;
	private double total;

	public ControladorPanelPedidos(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
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

	public String leerNumTransBBDD() {
		return String.valueOf(this.modelo.getConexion().leerNumTransBBDD());
	}

	public String conseguirLocal() {
		return modelo.getUser().getNifLocal();
	}

	public void mostrarPanelPedidos() {
		this.panelPedidos = makePanelPedidos(this);
		this.vista.mostrarPanel(this.panelPedidos);
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarLista();
		this.total = 0.0;
	}

	public String[] cogerListaProductos() {
		return this.modelo.getListaProductos().getListaProductosString();
	}

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

	public String[] accionadoBotonAnnadirProducto(String producto, String cantidad) {
		String[] devolver = new String[2];
		String productoAnadir = this.modelo.getUtil().annadirProducto(producto);
		devolver[0] = cantidadProducto(cantidad, productoAnadir);
		devolver[1] = cantidadTotal(cantidad, producto);
		return devolver;
	}

	public int existeProducto(String nombreProducto) {
		int pos = modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
		return pos;
	}

	public double cogerPrecioString(String nombreProducto) {
		double precio = modelo.getListaTemporal().precioProductoString(nombreProducto);
		return precio;
	}

	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto) {
		String[] devolver =  new String[2];
		devolver[0] = this.modelo.getUtil().cambiarCantidadProductos(nombreProductoAnadido, cantidadAnadir);
		devolver[1] = this.cantidadTotal(Integer.toString(cantidadAnadir), nombreProducto);
		return devolver;
	}

	public String cantidadProducto(String cantidad, String productoAnadir) {
		return cantidad + " " + productoAnadir;
	}

	public String cantidadTotal(String cantidad, String producto) {
		total = this.modelo.getUtil().cantidadTotal(cantidad, producto, total);
		return String.valueOf(total);
	}

	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.getUtil().eliminarProducto(pos, eliminar, total);
		return String.valueOf(total);
	}

	public String devolverFechaFormateada(String input) {
		String fecha = this.modelo.getUtil().devolverFechaFormateada(input);
		return fecha;
	}

	public String devolverNombreProducto(int i) {
		return this.modelo.getUtil().devolverNombreProducto(i);
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {
		String producto = devolverNombreProducto(nombreProducto);
		double precioFinal = cogerPrecioString(producto);
		String codigoAlimento = this.modelo.getConexion().obtenerCodigoAlimentoProducto(producto);
		this.modelo.getConexion().insertarProductoActividad(transaccion, codigoAlimento, cantidad, precioFinal);
	}

	public void insertarPedido(int transaccion, String domicilio) {
		this.modelo.getConexion().insertarPedido(transaccion, domicilio);
	}

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String nif) {
		String fechaFormateada = devolverFechaFormateada(fecha);
		this.modelo.getConexion().insertarActividad(transaccion, fechaFormateada, totalOperacion, nif);
	}

	public PanelPedidos makePanelPedidos(ControladorPanelPedidos controladorPanelPedidos) {
		return new PanelPedidos(controladorPanelPedidos);
	}
}
