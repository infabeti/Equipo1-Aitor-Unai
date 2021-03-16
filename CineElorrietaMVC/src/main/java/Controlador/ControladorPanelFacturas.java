package Controlador;

import Modelo.Modelo;
import javax.swing.DefaultListModel;
import Vista.PanelFacturas;
import Vista.Vista;

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
		return this.modelo.getFechaHoraSys();
	}

	public String conseguirLocal() {
		return this.modelo.getUser().getNifLocal();
	}

	public String leerNumTransBBDD() {
		return String.valueOf(this.modelo.getConsultas().leerNumTransBBDD());
	}

	public void mostrarPanelFacturas() {
		this.panelFacturas = makePanelFacturas(this);
		this.vista.mostrarPanel(this.panelFacturas);
	}

	public String[] cogerListaProductos() {
		return this.modelo.getListaProductos().getListaProductosString();
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		this.modelo.getListaTemporal().limpiarLista();
		this.total = 0.0;
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

	public int existeProducto(String nombreProducto) {
		return this.modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
	}

	public double cogerPrecioString(String nombreProducto) {
		return this.modelo.getListaTemporal().precioProductoString(nombreProducto);
	}


	public String accionadoBotonEliminar(int pos, String eliminar) {
		total = this.modelo.util.eliminarProducto(pos, eliminar, total);
		return String.valueOf(total);
	}

	public String devolverFechaFormateada(String input) {
		return this.modelo.util.devolverFechaFormateada(input);
	}

	public String devolverNombreProducto(int i) {
		return this.modelo.util.devolverNombreProducto(i);
	}

	public boolean contieneSoloLetras(String cadena) {
		return this.modelo.util.contieneSoloLetras(cadena);
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {
		String producto = devolverNombreProducto(nombreProducto);
		this.modelo.getInserciones().insertarProductoActividad(transaccion,
				this.modelo.getConsultas().obtenerCodigoAlimentoProducto(producto), cantidad,
				cogerPrecioString(producto));
	}

	public boolean comprobarCampos(double total, String nif, String nombre, String apellido) {
		return total > 0 && this.modelo.util.comprobarCamposString(nif, nombre, apellido);
	}

	public void insertarFactura(int transaccion, String fecha, double totalOperacion, String nifLocal, String nombre,
			String apellido, DefaultListModel<String> lista, String nifComprador) {
		this.modelo.insercionesActividades.insertarActividad(transaccion, devolverFechaFormateada(fecha), totalOperacion, "FACTURA",
				nifLocal);

		if (this.modelo.getConsultasComprobaciones().comprobarSiExisteComprador(nifComprador)) {
			System.out.println("El comprador ya existe, no se hace la insert en la tabla comprador");
		} else {
			this.modelo.getInserciones().insertarComprador(nifComprador, nombre, apellido);
		}

		this.modelo.insercionesActividades.insertarFactura(transaccion, nifComprador);
		for (int i = 0; i < lista.getSize(); i++) {
			String textoSpliteado[] = lista.get(i).split(" ");
			insertarProductoActividad(i, transaccion, Integer.parseInt(textoSpliteado[0]));
		}
	}

	public PanelFacturas makePanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		return new PanelFacturas(controladorPanelFacturas);
	}
}