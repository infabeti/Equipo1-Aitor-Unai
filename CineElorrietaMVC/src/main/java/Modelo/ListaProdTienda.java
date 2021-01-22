package Modelo;

import java.sql.Date;
import java.util.Arrays;

public class ListaProdTienda {
	
	private int punt = 0;
	private ProductoTienda[] lista = new ProductoTienda[256];
	private final Date FECHADEFAULT = new Date(0);
	private final Producto PRODUCTODEFAULT = new Producto("DEFAULT",FECHADEFAULT, "DEFAULT", 0, 0);
	private final ProductoTienda DEFAULT = new ProductoTienda(PRODUCTODEFAULT, 0);
	
	public ListaProdTienda() {
		Arrays.fill(lista, DEFAULT);
	}
	
	public boolean annadirProductoTienda(ProductoTienda prod) {
		boolean ok = false;
		try {
			lista[punt] = prod;
			punt++;
			ok = true;
		}
		catch(Exception e) {
			System.out.println("No se ha podido insertar el producto en la lista");
		}
		return ok;
	}
	
	public ProductoTienda buscarProductoTienda(String nombre) {
		ProductoTienda prod = new ProductoTienda();
		boolean ok = false;
		for(int i = 0; i<punt;i++) {
			if(nombre == lista[i].getProd().getNombre()) {
				prod = lista[i];
				ok = true;
			}
		}
		if(!ok) {
			prod = null;
		}
		return prod;
	}
	
	public boolean eliminarProductoTienda(String  nombre) {
		boolean ok = false;
		int puntero = 0;
		for(int i = 0; i<punt;i++) {
			if(nombre == lista[i].getProd().getNombre()) {
				puntero = i;
				ok = true;
				i = lista.length;
			}
		}
		if(ok) {
			for(int i = puntero; i<punt-1;i++) {
				lista[i] = lista[i+1];
			}
			lista[punt] = DEFAULT;
			punt--;
		}
		return ok;
	}
	
}
