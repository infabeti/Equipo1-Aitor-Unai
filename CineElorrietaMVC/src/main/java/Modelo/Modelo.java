package Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;


public class Modelo {

	private Producto[] listaProductos;
	private LineaPedido[] arrProdSeleccionados = new LineaPedido[256];
	//private Conexion conexion = new Conexion();
	private ArrayList<Producto> listaProd  = new ArrayList<Producto>(); 
	
	public Modelo() {

		listaProductos = productosAlmacenados();

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
	
	public static String getFechaHoraSys() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date date = new Date(0);
		return dateFormat.format(date);
	}
	
	public void addProductoTemporal(Producto prod) { 
		listaProd.add(prod);
	}
	
	public void limpiarListaTemporal() {
		listaProd.clear();
	}
	
	public void eliminiarProductoTemporal(int pos) {
		listaProd.remove(pos);
	}

}
