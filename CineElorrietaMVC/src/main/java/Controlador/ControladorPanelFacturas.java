package Controlador;

import Modelo.Modelo;
import Modelo.ListaProductos;
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
		return this.modelo.getUtil().pasarListaProductos();
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarLista();
		this.total = 0.0;
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

		return this.modelo.getUtil().devolverNombreProducto(i);
	}

	public void insertarProductoActividad(int nombreProducto, int transaccion, int cantidad) {

		String producto = devolverNombreProducto(nombreProducto);
		double precioFinal = cogerPrecioString(producto);
		
		String codigoAlimento = this.modelo.getConexion().obtenerCodigoAlimentoProducto(producto);
		this.modelo.getConexion().insertarProductoActividad(transaccion, codigoAlimento, cantidad, precioFinal);

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

	public boolean comprobarCampos(double total, String nif, String nombre, String apellido) {
		boolean comprobarTotal = total > 0;
		boolean comprobarNif = comprobarNif(nif);
		boolean comprobarNombre = comprobarFormatoApellido(nombre);
		boolean comprobarApellido = comprobarFormatoApellido(apellido);
		if (comprobarTotal && comprobarNif && comprobarNombre && comprobarApellido) {
			return true;
		} else {
			return false;
		}
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