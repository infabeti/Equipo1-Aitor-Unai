package Modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas2 {

	private Modelo modelo;
	private java.sql.Connection conexionConn;
	private final SentenciasBBDD sentenciasBBDD = new SentenciasBBDD();

	public Consultas2(Modelo modelo) {
		this.modelo = modelo;
		conexionConn = this.modelo.conexionConn;
	}

	public String obtenerCodigoPlato(String plato) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.CONSULTAPLATO);
			ResultSet rs = st.executeQuery();
			try {
				while (rs.next()) {
					if (rs.getString("nombre").equalsIgnoreCase(plato)) {
						return rs.getString("codigoplato");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}

	public ListaProductos cogerProductosAprovisionamiento() {
		ListaProductos listaProd = new ListaProductos();
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.ALIMENTOORDENADO);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("a.nombre");
				double pCompra = rs.getDouble("a.PCompra");
				String tipo = rs.getString("a.Tipo");
				Date feCad = rs.getDate("a.FeCad");
				Producto prod = new Producto(nombre, feCad, tipo, pCompra);
				listaProd.addProducto(prod);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return listaProd;
	}

	public String obtenerCodigoAlimentoProducto(String producto) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(sentenciasBBDD.CONSULTAALIMENTO);
			ResultSet rs = st.executeQuery();
			try {
				while (rs.next()) {
					if (rs.getString("nombre").equalsIgnoreCase(producto)) {
						return rs.getString("codigoalimento");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}

	public ListaPlatos cogerListaPlatos(String NIFLocal) {
		ListaPlatos listaPlatos = new ListaPlatos();
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					sentenciasBBDD.PLATOJOINCARTA);
			st.setString(1, NIFLocal);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("p.Nombre");
				double pvp = rs.getDouble("p.pvp");
				Plato plato = new Plato(nombre, pvp);
				listaPlatos.addPlato(plato);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return listaPlatos;
	}
	
}