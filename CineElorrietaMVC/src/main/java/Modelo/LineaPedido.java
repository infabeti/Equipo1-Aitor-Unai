package Modelo;

public class LineaPedido {

	private int cantidad;
	private double total;
	private Producto producto;

	private LineaPedido(Producto producto, int cantidad, double total) {

		this.producto = producto;
		this.cantidad = cantidad;
		this.total = total;

	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
