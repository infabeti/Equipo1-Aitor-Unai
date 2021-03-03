package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utiles {
	
	private Modelo modelo;
	
	public Utiles(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public double cantidadTotal(String cantidad, String producto, double total) {
		ListaProductos listaProd = this.modelo.getListaProductos();
		int cantidadInt = Integer.parseInt(cantidad);
		double precioTotalProducto = cantidadInt * listaProd.precioProductoString(producto);
		total = total + precioTotalProducto;
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		return total;
	}

	public double accionadoBotonEliminar(int pos, String eliminar, double total) {
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
	
}
