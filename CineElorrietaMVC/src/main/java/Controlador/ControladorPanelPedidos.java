package Controlador;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import Modelo.LineaPedido;
import Modelo.ListaProductos;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelFacturas;
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
	private double total;

	public ControladorPanelPedidos(Modelo modelo, Vista vista, Controlador controlador) {
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
	}

	public String[] pasarListaProductos() {
		
		return this.modelo.getUtil().pasarListaProductos();
	}

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

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

	public String cantidadProducto(String cantidad, String productoAnadir) {
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

		return this.modelo.getUtil().devolverNombreProducto(i);
	}

	public void insertarProductoActividad(String nombreProducto, int transaccion, int cantidad, double preciofinal) {

		String codigoAlimento = this.modelo.getConexion().obtenerCodigoAlimentoProducto(nombreProducto);
		this.modelo.getConexion().insertarProductoActividad(transaccion, codigoAlimento, cantidad, preciofinal);

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
