package Controlador;

import javax.swing.DefaultListModel;
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
		return String.valueOf(this.modelo.getConsultas().leerNumTransBBDD());
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
		modelo.getListaTemporal().limpiarLista();
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
		devolver[0] = cantidad + " " + this.modelo.util.annadirProducto(producto);
		devolver[1] = cantidadTotal(cantidad, producto);
		return devolver;
	}

	public int existeProducto(String nombreProducto) {
		return modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
	}

	public double cogerPrecioString(String nombreProducto) {
		return modelo.getListaTemporal().precioProductoString(nombreProducto);
	}

	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto) {
		String[] devolver = new String[2];
		devolver[0] = this.modelo.util.cambiarCantidadProductos(nombreProductoAnadido, cantidadAnadir);
		devolver[1] = this.cantidadTotal(Integer.toString(cantidadAnadir), nombreProducto);
		return devolver;
	}

	public String cantidadTotal(String cantidad, String producto) {
		total = this.modelo.util.cantidadTotal(cantidad, producto, total);
		return String.valueOf(total);	}

	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.util.eliminarProducto(pos, eliminar, total);
		return String.valueOf(total);	}

	public String devolverFechaFormateada(String input) {
		return this.modelo.util.devolverFechaFormateada(input);
	}

	public String devolverNombreProducto(int i) {
		return this.modelo.util.devolverNombreProducto(i);
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {
		String producto = devolverNombreProducto(nombreProducto);
		this.modelo.getInserciones().insertarProductoActividad(transaccion, this.modelo.getConsultas().obtenerCodigoAlimentoProducto(producto), cantidad, cogerPrecioString(producto));
	}

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String nif, String domicilio,
			DefaultListModel<String> lista) {
		this.modelo.getInserciones().insertarActividad(transaccion, devolverFechaFormateada(fecha), totalOperacion,
				nif);
		this.modelo.getInserciones().insertarPedido(transaccion, domicilio);
		for (int i = 0; i < lista.getSize(); i++) {
			String textoSpliteado[] = lista.get(i).split(" ");
			insertarProductoActividad(i, transaccion, Integer.parseInt(textoSpliteado[0]));
		}
	}

	public PanelPedidos makePanelPedidos(ControladorPanelPedidos controladorPanelPedidos) {
		return new PanelPedidos(controladorPanelPedidos);
	}
}