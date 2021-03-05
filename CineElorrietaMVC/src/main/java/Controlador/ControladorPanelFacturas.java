package Controlador;

import Modelo.Modelo;

import javax.swing.DefaultListModel;

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
		return this.modelo.getListaProductos().getListaProductosString();
	}

	public void accionadoBottonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
		ListaProductos listaProd = modelo.getListaTemporal();
		listaProd.limpiarLista();
		this.total = 0.0;
	}

	public String accionadoBotonAnnadirProducto(String producto) {
		return this.modelo.getUtil().annadirProducto(producto);
	}

	public int existeProducto(String nombreProducto) {
		int pos = modelo.getListaTemporal().devolverPosProductoString(nombreProducto);
		return pos;
	}

	public double cogerPrecioString(String nombreProducto) {
		double precio = modelo.getListaTemporal().precioProductoString(nombreProducto);
		return precio;
	}

	public String cambiarCantidadProductos(String nombreProducto, int cantidadAnadir) {
		return this.modelo.getUtil().cambiarCantidadProductos(nombreProducto, cantidadAnadir);
	}

	public String cantidadProducto(String cantidad, String productoAnadir) { // Este m�todo crea el mensaje para a�adir en la lista de a�adidos,el cual se creaba antes en la propiavista
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
	public boolean comprobarCampos(double total, String nif, String nombre, String apellido) {
		boolean comprobarTotal = total > 0;
		boolean comprobarCampos = this.modelo.getUtil().comprobarCamposString(nif, nombre, apellido);
		if (comprobarTotal && comprobarCampos) {
			return true;
		} else {
			return false;
		}
	}
	
	public void insertarFactura(int transaccion, String fecha, double totalOperacion, String nifLocal, String nombre, String apellido, DefaultListModel<String> lista, String nifComprador) {
		String fechaFormateada = devolverFechaFormateada(fecha);
		this.modelo.getConexion().insertarActividad(transaccion, fechaFormateada, totalOperacion, nifLocal);
				
		if(this.modelo.getConexion().comprobarSiExisteComprador(nifComprador)) {
			System.out.println("El comprador ya existe, no se hace la insert en la tabla comprador");
		}
		else
		{
			this.modelo.getConexion().insertarComprador(nifComprador, nombre, apellido);
		}
		
		
		this.modelo.getConexion().insertarFactura(transaccion, nifComprador);
		
		for (int i = 0; i < lista.getSize(); i++) {
			String textoRecogido = lista.get(i);
			String textoSpliteado[] = textoRecogido.split(" ");

			int cantidad = Integer.parseInt(textoSpliteado[0]);
			
			insertarProductoActividad(i, transaccion, cantidad);
		}
	}

	public boolean contieneSoloLetras(String cadena) {
		boolean correcto = this.modelo.getUtil().contieneSoloLetras(cadena);
		return correcto;
	}

	public PanelFacturas makePanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		return new PanelFacturas(controladorPanelFacturas);
	}
}