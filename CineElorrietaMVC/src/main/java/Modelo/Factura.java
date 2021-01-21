package Modelo;

import java.sql.Date;

public class Factura extends Actividad {

	private String nif;
	private String nombre;
	private String apellido;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Factura(int numTransaccion, Date fecha, String local, String nif, String nombre, String apellido) {
		super(numTransaccion, fecha, local);
		this.nif = nif;
		this.nombre = nombre;
		this.apellido = apellido;
	}

}
