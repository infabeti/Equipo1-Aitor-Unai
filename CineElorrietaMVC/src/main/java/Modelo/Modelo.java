package Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Modelo {

	private Producto[] listaProductos;
	private LineaPedido[] arrProdSeleccionados = new LineaPedido[256];
	private Conexion conexion = new Conexion();
	private ListaProductos listaTemporal = new ListaProductos();
	
	public Modelo() {

		listaProductos = productosAlmacenados();

	}
	
	public void setConexion(Conexion conexion){
		this.conexion = conexion;
	}
	
	public Conexion getConexion() {
		return this.conexion;
	}
	
	public void setListaTemporal(ListaProductos listaTemporal) {
		this.listaTemporal = listaTemporal;
	}
	
	public ListaProductos getListaTemporal() {
		return this.listaTemporal;
	}


	public LineaPedido[] getArrProdSeleccionados() {
		return arrProdSeleccionados;
	}

	public void setArrProdSeleccionados(LineaPedido[] arrProdSeleccionados) {
		this.arrProdSeleccionados = arrProdSeleccionados;
	}

	public void setListaProductos(Producto[] listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String[] getListaProductos() {

		String listaProductosString[] = new String[listaProductos.length];

		for (int i = 0; i < listaProductos.length; i++) {
			listaProductosString[i] = listaProductos[i].getNombre();
		}

		return listaProductosString;
	}

	public Producto[] productosAlmacenados() {

		Date date = new Date(0);

		Producto p1 = new Producto("Bocata", date, "comida", 1.00, 1.50);
		Producto p2 = new Producto("Coca-Cola", date, "bebida", 0.35, 1.50);

		Producto[] listadoProductos = { p1, p2 };

		return listadoProductos;
	}
	

	public Producto devolverProductoPorString(String nombre) {

		Producto[] listadoProductos = productosAlmacenados();
		for (int i = 0; i < listadoProductos.length; i++) {
			if (nombre.equalsIgnoreCase(listadoProductos[i].getNombre())) {
				return listadoProductos[i];
			}

		}

		return null;

	}
	
	public double precioProductoString(String nombre) {
		Producto prod = devolverProductoPorString(nombre);
		return prod.getPrecioVenta();
	}
	
	public double precioProductoPos(int pos) {
		Producto prod = listaTemporal.cogerProducto(pos);
		return prod.getPrecioVenta();
	}
	
	public static String getFechaHoraSys() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date date = new Date(0);
		return dateFormat.format(date);
	}
	
	public int cogerCantidadString(String linea) {
		int punt = 0;
		for(int i = 0; linea.charAt(i)!= ' ';i++) {
			punt = i;
		}
		punt++;
		int cantidad = Integer.parseInt(linea.substring(0, punt));
		return cantidad;
	}
	

}
