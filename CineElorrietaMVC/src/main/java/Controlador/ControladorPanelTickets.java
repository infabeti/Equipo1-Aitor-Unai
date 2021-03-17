package Controlador;

import javax.swing.DefaultListModel;
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
		return String.valueOf(this.modelo.getConsultas().leerNumTransBBDD());
	}

	public void insertarTicket(int transaccion, String fecha, double totalOperacion, String nif,
			DefaultListModel<String> lista) {

		this.modelo.insercionesActividades.insertarActividad(transaccion, devolverFechaFormateada(fecha), totalOperacion, "TICKET", nif);

		for (int i = 0; i < lista.getSize(); i++) {
			String textoSpliteado[] = lista.get(i).split(" ");
			insertarProductoActividad(i, transaccion, Integer.parseInt(textoSpliteado[0]));
		}
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {
		String producto = devolverNombreProducto(nombreProducto);
		this.modelo.getInserciones().insertarProductoActividad(transaccion,
				this.modelo.getConsultas().obtenerCodigoAlimentoProducto(producto), cantidad,
				cogerPrecioString(producto));
	}

	public String conseguirLocal() {
		return modelo.getUser().getNifLocal();
	}

	public String devolverFechaHora() {
		return modelo.getFechaHoraSys();
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		modelo.getListaTemporal().limpiarLista();
		this.total = 0.0;
	}

	public String[] cogerListaProductos() {
		return this.modelo.getListaProductos().getListaProductosString();
	}

	public int existeProducto(String nombreProducto) {
		return modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
	}

	public double cogerPrecioString(String nombreProducto) {
		return modelo.getListaTemporal().precioProductoString(nombreProducto);
	}

	public String[] accionadoBotonAnnadirProducto(String producto, String cantidad) {
		String[] devolver = this.modelo.util.accionadoBotonAnnadirProducto(producto, cantidad, this.total);
		this.total = Double.parseDouble(devolver[1]);
		return devolver;
	}

	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto) {
		String[] devolver = this.modelo.util.cambiarCantidadProductos(nombreProductoAnadido, cantidadAnadir, nombreProducto, this.total);
		this.total = Double.parseDouble(devolver[1]);
		return devolver;
	}

	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.util.eliminarProducto(pos, eliminar, total);
		return String.valueOf(total);
	}

	public String devolverFechaFormateada(String input) {
		return this.modelo.validaciones.devolverFechaFormateada(input);
	}

	public String devolverNombreProducto(int i) {
		return this.modelo.util.devolverNombreProducto(i);
	}

	public PanelTickets makePanelTickets(ControladorPanelTickets controladorPanelTickets) {
		return new PanelTickets(controladorPanelTickets);
	}
}