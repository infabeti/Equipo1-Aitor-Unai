package Controlador;

import Modelo.Modelo;
import Modelo.Producto;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelo.ListaProductos;
import Vista.PanelFacturas;
import Vista.Vista;

public class ControladorPanelFacturas {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelFacturas panelFacturas;

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

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(nif);

		if (matcher.matches()) {

			correcto = true;

		} else {

			correcto = false;

		}

		return correcto;

	}

	public boolean comprobarFormatoNombre(String nombre) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(nombre) && nombre.length() <= 20) {
			if(nombre.length() >= 3)
			{
				return true;
			}
			return false;
			
		} else {

			return false;
		}
	}

	public boolean comprobarFormatoApellido(String apellido) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(apellido) && apellido.length() <= 25) {
			if(apellido.length() >= 2)
			{
				return true;
			}
			return false;
		} else {

			return false;
		}
	}

	public void insertarComprador(String nif, String nombre, String apellido) {

		this.modelo.getConexion().insertarComprador(nif, nombre, apellido);

	}

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String nif) {
		this.modelo.getConexion().insertarActividad(transaccion, fecha, totalOperacion, nif);
	}

	public boolean contieneSoloLetras(String cadena) {
		for (int x = 0; x < cadena.length(); x++) {
			char c = cadena.charAt(x);
			// Si no est� entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}
		return true;
	}

	public PanelFacturas makePanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		return new PanelFacturas(controladorPanelFacturas);
	}
}