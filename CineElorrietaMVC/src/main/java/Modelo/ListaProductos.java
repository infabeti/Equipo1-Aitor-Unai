package Modelo;

import java.util.ArrayList;

public class ListaProductos {
	
	private ArrayList<Producto> listaProd; 
	
	public ListaProductos() {
		listaProd  = new ArrayList<Producto>();
	}
	
	public void addProductoTemporal(Producto prod) {
		listaProd.add(prod);
	}
	
	public void limpiarListTemporal() {
		listaProd.clear();
	}
	
	public void eliminarProductoTemporal(int pos) {
		listaProd.remove(pos);
	}
	
	public Producto cogerProducto(int pos) {
		return listaProd.get(pos);
	}
	
	public double getPrecioProducto(int pos) {
		return cogerProducto(pos).getPrecioVenta();
	}
	
}
