package Modelo;

import java.sql.Date;

public class Modelo {

	private Producto[] listaProductos;

	public Modelo() {

		listaProductos = productosAlmacenados();

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
}
