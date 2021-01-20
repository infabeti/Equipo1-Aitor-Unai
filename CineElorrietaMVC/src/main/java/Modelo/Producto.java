package Modelo;

public class Producto {

	private String nombre;
	private Double precioUnidad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public Producto(String nombre, Double precioUnidad) {
		this.nombre = nombre;
		this.precioUnidad = precioUnidad;
	}

}
