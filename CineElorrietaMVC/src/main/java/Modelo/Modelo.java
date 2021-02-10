package Modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Modelo {

	private ListaProductos listaProductos = new ListaProductos();
	private LineaPedido[] arrProdSeleccionados = new LineaPedido[256];

	private String usuario;
	private String local;
	private char tipoLocal;
	
	//Conexion a BBDD

	private Conexion conexion = new Conexion();
	private ListaProductos listaTemporal = new ListaProductos();

	
	//obtenemos la conexion en el formato que necesitamos para hacer consultas
	java.sql.Connection conexionConn =  conexion.getConn();
	
	public Modelo() {
		
		productosAlmacenados();

	}
	
	public void setConexion(Conexion conexion){
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

	public void setListaProductos(ListaProductos listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public ListaProductos getListaProductos() {
		return this.listaProductos;
	}

	public LineaPedido[] getArrProdSeleccionados() {
		return arrProdSeleccionados;
	}

	public void setArrProdSeleccionados(LineaPedido[] arrProdSeleccionados) {
		this.arrProdSeleccionados = arrProdSeleccionados;
	}

	public void productosAlmacenados() {

		Date date = new Date(0);

		Producto p1 = new Producto("Bocata", date, "comida", 1.00, 1.50);
		Producto p2 = new Producto("Coca-Cola", date, "bebida", 0.35, 1.50);

		listaProductos.addProductoTemporal(p1);
		listaProductos.addProductoTemporal(p2);
	}
	
	public static String getFechaHoraSys() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date date = new Date(0);
		return dateFormat.format(date);
	}
	
	public int cogerCantidadString(String linea) {
		int punt = 0;
		for(int i = 0; linea.charAt(i)!= ' ';i++) {
			punt = i;
		}
		punt++;
		int cantidad = Integer.parseInt(linea.substring(0, punt));
		return cantidad;
	}
	

	public void usuarioLocal(String usuario, String local, char tipoLocal) { //Para guardar el usuario, local y tipo de local
		this.usuario = usuario;
		this.local = local;
		this.tipoLocal = tipoLocal;
	}
	
	public void eliminarUsuarioLocal() {//Metodo para desloguear
		this.usuario = "";
		this.local = "";
		this.tipoLocal = ' ';
	}
	
	//Métodos para devolver los atributos de arriba
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getLocal() {
		return this.local;
	}
	
	public char getTipoLocal() {
		return this.tipoLocal;
	}
	
	public String getTipoLocalCompleto() {
		String tipo = "";
		if(this.tipoLocal == 'b' || this.tipoLocal == 'B') {
			tipo = "Bar";
		}
		else if(this.tipoLocal == 'c' || this.tipoLocal == 'C') {
			tipo = "Cafeteria";
		}
		else if(this.tipoLocal == 'r' || this.tipoLocal == 'R') {
			tipo = "Restaurante";
		}
		return tipo;
	}


}
