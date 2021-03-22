package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utiles {
	
	private Modelo modelo;
	
	public Utiles(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public double cantidadTotal(String cantidad, String producto, double total, String tipo) {
		double precioTotalProducto = 0;
		int cantidadInt = Integer.parseInt(cantidad);
		if(tipo.equalsIgnoreCase("producto")) {
			ListaProductos listaProd = this.modelo.getListaProductos();
			precioTotalProducto = cantidadInt * listaProd.precioProductoString(producto);}
		else {
			ListaPlatos listaPlatos = this.modelo.getListaPlatos();
			precioTotalProducto = cantidadInt * listaPlatos.precioProductoString(producto);}
		total = total + precioTotalProducto;
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		return total;
	}
	
	public double cantidadTotalPlatos(String cantidad, String producto, double total) {
		ListaPlatos listaPlatos = this.modelo.getListaPlatos();
		int cantidadInt = Integer.parseInt(cantidad);
		double precioTotalProducto = cantidadInt * listaPlatos.precioProductoString(producto);
		total = total + precioTotalProducto;
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		return total;
	}
	
	public String annadirProducto(String producto) {
		ListaProductos listaProd = modelo.getListaProductos();
		Producto prod = listaProd.devolverProductoPorString(producto);
		ListaProductos listaTemporal = modelo.getListaTemporal();
		listaTemporal.addProducto(prod);
		return prod.toString();
	}
	
	public String funcionalidadAnnadirPlato(String plato) {
		ListaPlatos listaPlatos = modelo.getListaPlatos();
		Plato plat = listaPlatos.devolverPlatoPorString(plato);
		ListaPlatos listaTemporal = modelo.getListaTemporalPlatos();
		listaTemporal.addPlato(plat);
		return plat.toString();
	}
	
	public String funcionalidadCambioProductos(String producto, int cantidadAnadir) {
		int pos = 0;
		for (int i = 0; Character.isDigit(producto.charAt(i)); i++) {
			pos = i; }
		String cantString = producto.substring(0, pos + 1);
		int cantidad = Integer.parseInt(cantString);
		cantidad = cantidad + cantidadAnadir;
		String cambiada = cantidad + producto.substring(pos + 1);
		return cambiada;
	}
	
	public String devolverNombreProducto(int i) {
		ListaProductos listaTemporal = this.modelo.getListaTemporal();
		String[] lista = listaTemporal.getListaProductosString();
		return lista[i];
	}
	
	public String devolverNombrePlato(int i) {
		ListaPlatos listaTemporal = this.modelo.getListaTemporalPlatos();
		String[] lista = listaTemporal.getListaPlatosString();
		return lista[i];
	}

	public double funcionalidadeliminarProducto(int pos, String eliminar, double total) {
		ListaProductos listaProd = modelo.getListaTemporal();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaProd.getPrecioProducto(pos);
		total = total - (precio * cantidad);
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		listaProd.eliminarProducto(pos);
		return total;
	}
	
	public double funcionalidadeliminarPlato(int pos, String eliminar, double total) {
		ListaPlatos listaPlatos = modelo.getListaTemporalPlatos();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaPlatos.getPrecioPlato(pos);
		total = total - (precio * cantidad);
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		listaPlatos.eliminarPlato(pos);
		return total;
	}
	
	public String[] funcionalidadAnadirProducto(String producto, String cantidad, double total) {
		String[] devolver = new String[2];
		devolver[0] = cantidad + " " + annadirProducto(producto);
		devolver[1] = String.valueOf(cantidadTotal(cantidad, producto, total, "producto"));
		return devolver;
	}

	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto, double total, String tipo) {
		String[] devolver = new String[2];
		devolver[0] = funcionalidadCambioProductos(nombreProductoAnadido, cantidadAnadir);
		devolver[1] = String.valueOf(cantidadTotal(Integer.toString(cantidadAnadir), nombreProducto, total, tipo));
		return devolver;
	}
	
	public String[] funcionalidadAnadirPlato(String plato, String cantidad, double total) {
		String[] devolver = new String[2];
		devolver[0] = cantidad + " " + funcionalidadAnnadirPlato(plato);
		devolver[1] = String.valueOf(cantidadTotalPlatos(cantidad, plato, total));
		return devolver;
	}
}
