package Modelo;

import java.sql.Date;

public class Actividad {

	private int numTransaccion;
	private Date fecha;
	private String local;
	
	public int getNumTransaccion() {
		return numTransaccion;
	}
	public void setNumTransaccion(int numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public Actividad(int numTransaccion, Date fecha, String local)
	{
		this.numTransaccion = numTransaccion;
		this.fecha = fecha;
		this.local = local;
	}
	
	public Actividad() {}
	
}
