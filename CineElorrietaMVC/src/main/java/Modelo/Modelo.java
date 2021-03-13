package Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Modelo {

	private ListaProductos listaProductos = new ListaProductos();
	private Usuario user;
	public Utiles util;
	private Inserciones inserciones;
	private Consultas consultas;
	private Registro registro;

	public Registro getRegistro() {
		return registro;
	}

	private ConsultasComprobaciones consultasComprobaciones;

	public ConsultasComprobaciones getConsultasComprobaciones() {
		return consultasComprobaciones;
	}

	public Consultas getConsultas() {
		return consultas;
	}

	public Inserciones getInserciones() {
		return inserciones;
	}

	private Conexion conexion = new Conexion();
	private ListaProductos listaTemporal = new ListaProductos();

	// obtenemos la conexion en el formato que necesitamos para hacer consultas
	java.sql.Connection conexionConn = conexion.getConn();

	public Modelo() {
		user = new Usuario("", "", "", "");
		util = new Utiles(this);
		inserciones = new Inserciones(this);
		consultasComprobaciones = new ConsultasComprobaciones(this);
		consultas = new Consultas(this);
		registro = new Registro(this);
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public Conexion getConexion() {
		return this.conexion;
	}

	public void setListaTemporal(ListaProductos listaTemporal) {
		this.listaTemporal = listaTemporal;
	}

	public ListaProductos getListaTemporal() {
		return this.listaTemporal;
	}

	public ListaProductos getListaProductos() {
		return this.listaProductos;
	}

	public String getFechaHoraSys() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		return dateFormat.format(date);
	}

	public int cogerCantidadString(String linea) {
		int punt = 0;
		for (int i = 0; linea.charAt(i) != ' '; i++) {
			punt = i;
		}
		punt++;
		int cantidad = Integer.parseInt(linea.substring(0, punt));
		return cantidad;
	}

	public void actualizarListaProductosLocal() {
		this.listaProductos = consultas.cogerProductosLocal(user.getNifLocal());
	}

	public Usuario getUser() {
		return this.user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
