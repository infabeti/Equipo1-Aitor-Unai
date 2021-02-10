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
	
	public String[] getListaProductosString() {
		String listaProductosString[] = new String[listaProd.size()];
		
		for(int i = 0; i < listaProd.size(); i++) {
			listaProductosString[i] = listaProd.get(i).getNombre();
		}
		
		return listaProductosString;
	}
	
	public Producto devolverProductoPorString(String nombre) {
		for (int i = 0; i < listaProd.size(); i++) {
			if(listaProd.get(i).getNombre() == nombre) {
				return listaProd.get(i);
			}
		}
		return null;
	}
	
	public double precioProductoString(String nombre) {
		System.out.println(nombre);
		Producto prod = this.devolverProductoPorString(nombre);
		return prod.getPrecioVenta();
	}
}
