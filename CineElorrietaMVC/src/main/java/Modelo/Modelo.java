package Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Modelo {

	private ListaProductos listaProductos = new ListaProductos();
	private ListaPlatos listaPlatos = new ListaPlatos();
	private Usuario user;
	public Utiles util;
	private Inserciones inserciones;
	private Consultas consultas;
	private Consultas2 consultas2;
	private Registro registro;
	public InsercionesActividades insercionesActividades;
	public Validaciones validaciones;

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
	private ListaPlatos listaTemporalPlatos = new ListaPlatos();

	// obtenemos la conexion en el formato que necesitamos para hacer consultas
	java.sql.Connection conexionConn = conexion.getConn();

	public Modelo() {
		user = new Usuario("", "", "", "");
		util = new Utiles(this);
		inserciones = new Inserciones(this);
		consultasComprobaciones = new ConsultasComprobaciones(this);
		consultas = new Consultas(this);
		consultas2 = new Consultas2(this);
		registro = new Registro(this);
		insercionesActividades = new InsercionesActividades(this);
		validaciones = new Validaciones();
	}

	public Consultas2 getConsultas2() {
		return consultas2;
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
	
	public void setListaTemporalPlatos(ListaPlatos listaTemporalPlatos) {
		this.listaTemporalPlatos = listaTemporalPlatos;
	}
	
	public ListaPlatos getListaTemporalPlatos() {
		return this.listaTemporalPlatos;
	}

	public ListaProductos getListaProductos() {
		return this.listaProductos;
	}
	
	public ListaPlatos getListaPlatos() {
		return this.listaPlatos;
	}
	
	public void setListaPlatos(ListaPlatos listaPlatos) {
		this.listaPlatos = listaPlatos;
	}

	public void productosAlmacenados() {

		Date date = new Date(0);

		Producto p1 = new Producto("Bocata", date, "comida", 1.00, 1.50);
		Producto p2 = new Producto("Coca-Cola", date, "bebida", 0.35, 1.50);

		listaProductos.addProducto(p1);
		listaProductos.addProducto(p2);
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
