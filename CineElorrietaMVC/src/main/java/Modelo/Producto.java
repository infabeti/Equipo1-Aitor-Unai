package Modelo;

import java.sql.Date;

public class Producto {

	private String nombre;
	private double precioCompra, precioVenta;
	private Date fechaCaducidad;
	private String tipo;
	
	public Producto(String nombre, Date fechaCaducidad, String tipo, double precioCompra, double precioVenta) {
		this.nombre = nombre;
		this.fechaCaducidad = fechaCaducidad;
		this.tipo = tipo;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
	}
	
	public Producto()
	{
		this.nombre="DEFAULT";
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return nombre.concat(" Precio de compra: ").concat(Double.toString(precioCompra)).concat(" Precio de venta: ").concat(Double.toString(precioVenta)).concat(" Fecha de caducidad ").concat(fechaCaducidad.toString()).concat(" Tipo de producto: ").concat(tipo);
	}
	
}
