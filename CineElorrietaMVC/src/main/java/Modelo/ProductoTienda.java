package Modelo;

public class ProductoTienda {
	
	private Producto prod;
	private int cantidad = 0;
	
	public ProductoTienda() {}
	
	public ProductoTienda(Producto prod) {
		this.prod = prod;
	}
	
	public ProductoTienda(Producto prod, int cantidad) {
		this.prod = prod;
		this.cantidad = cantidad;
	}

	public Producto getProd() {
		return prod;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int addCantidad(int cantidad) { //Suma cantidad a la cantidad del producto y devuelve el nuevo stock
		this.cantidad = this.cantidad + cantidad;
		return this.cantidad;
	}
	
	public int restarCantidad(int cantidad) { 
		/*
		 * Resta cantidad a la cantidad del producto y devuelve el nuevo stock. Nunca puede ser menos de 0.
		 * Si la cantidad resultante fuese < 0, devuelve el stock que falta en negativo.
		 */
		int devolver = 0;
		if(this.cantidad < cantidad) {
			this.cantidad = 0;
			devolver = this.cantidad - cantidad;
		}
		else {
			this.cantidad = this.cantidad - cantidad;
			devolver = this.cantidad;
		}
		return devolver;
	}
	
}