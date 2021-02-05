package Modelo;

import java.sql.Date;
import java.util.Iterator;


public class Modelo {

	private Producto[] listaProductos;
	private LineaPedido[] arrProdSeleccionados = new LineaPedido[256];
	private String usuario;
	private String local;
	private char tipoLocal;
	
	//Conexion a BBDD
	Conexion conexion = new Conexion();
	
	//obtenemos la conexion en el formato que necesitamos para hacer consultas
	java.sql.Connection conexionConn =  conexion.getConn();
	
	public Modelo() {

		listaProductos = productosAlmacenados();

	}

	public LineaPedido[] getArrProdSeleccionados() {
		return arrProdSeleccionados;
	}

	public void setArrProdSeleccionados(LineaPedido[] arrProdSeleccionados) {
		this.arrProdSeleccionados = arrProdSeleccionados;
	}

	public void setListaProductos(Producto[] listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String[] getListaProductos() {

		String listaProductosString[] = new String[listaProductos.length];

		for (int i = 0; i < listaProductos.length; i++) {
			listaProductosString[i] = listaProductos[i].getNombre();
		}

		return listaProductosString;
	}

	public Producto[] productosAlmacenados() {

		Date date = new Date(0);

		Producto p1 = new Producto("Bocata", date, "comida", 1.00, 1.50);
		Producto p2 = new Producto("Coca-Cola", date, "bebida", 0.35, 1.50);

		Producto[] listadoProductos = { p1, p2 };

		return listadoProductos;
	}
	

	public Producto devolverProductoPorString(String nombre) {

		Producto[] listadoProductos = productosAlmacenados();
		for (int i = 0; i < listadoProductos.length; i++) {
			if (nombre.equalsIgnoreCase(listadoProductos[i].getNombre())) {
				return listadoProductos[i];
			}

		}

		return null;

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
