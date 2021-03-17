package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Trigger_Stock {

	private Modelo modelo;
	private final SentenciasBBDD sentenciasBBDD = new SentenciasBBDD();
	private java.sql.Connection conexionConn;

	public Trigger_Stock(Modelo modelo) {
		this.modelo = modelo;
		conexionConn = this.modelo.conexionConn;
	}

	public int comprobarStock(int CodigoAlimento, String nif) {
		int cant = 0;
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.CODIGOALIMENTO);

			st.setString(1, nif);
			st.setInt(2, CodigoAlimento);
			
			try {
				ResultSet rs = st.executeQuery();
				st.executeQuery();
				cant = rs.getInt(3);
				return cant;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return cant;
	}

	public static String comprobarNIF(String metodo) {
		Connection Conexion = SentenciasBBDD.getConexion();

		String query1 = (SentenciasBBDD.NIF + "'" + metodo + "'");
		String met = null;

		try {
			ResultSet re;
			PreparedStatement p;
			p = Conexion.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				met = re.getString("El NIF");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return met;
	}

	public void MetodoCarro(ArrayList<Carro_Stock> Carro_Stock, int Transaccion, String metodo)
			throws ClassNotFoundException, SQLException {

		for (int i = 0; i < Carro_Stock.size(); i++) {
			int cantidad = Carro_Stock.get(i).getcantidad();
			int CodigoAlimento = Carro_Stock.get(i).getCodigoAlimento();

			if (ComprobarStock(CodigoAlimento, Transaccion) == true) {

				Stock(CodigoAlimento, cantidad);

				if (comprobarStock(CodigoAlimento, comprobarNIF(metodo)) < 5) {
					Stock_2(CodigoAlimento);
				}
			} else {
				actualizarStock(CodigoAlimento, cantidad);
			}
		}
	}

	public void Stock(int CodigoAlimento, int cantidad) throws ClassNotFoundException, SQLException {
		Connection Conexion = Conexion.getConexion();

		String query1 = (SentenciasBBDD.Stock + "('" + CodigoAlimento + "', " + (prep() - 1) + ")");
		try {
			Statement s;
			s = Conexion.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean ComprobarStock(int CodigoAlimento, int Transaccion) {

		boolean si = false;
		Connection Conexion = Conexion.getConexion();

		String query1 = (SentenciasBBDD.ComprobarStock + "'" + CodigoAlimento + "' and Transaccion = '"
				+ (Transaccion - 1) + "'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = Conexion.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				si = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return si;
	}

	public void actualizarStock(int CodigoAlimento, int cantidad) throws ClassNotFoundException, SQLException {
		Connection Conexion = Conexion.getConexion();

		String query1 = (SentenciasBBDD.actualizarStock + "cantidad + " + cantidad + " where CodigoAlimento = '"
				+ CodigoAlimento + "' and Transaccion = " + (metodoak.jasoTransakzioZbk() - 1) + "");
		try {
			Statement s;
			s = Conexion.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int prep() throws SQLException, ClassNotFoundException {
		Connection konekzioa = Conexion.getConexion();

		String query1 = (SentenciasBBDD.Transaccion);
		int Transaccion = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				if (re.getInt("max(Transaccion)") == 0) {
					Transaccion = 1;
				} else {
					Transaccion = re.getInt("max(Transaccion)");
					Transaccion++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Transaccion;
	}

	public void Stock_2(int CodigoAlimento) {

		Connection Conexion = Conexion.getConexion();

		String query1 = (SentenciasBBDD.actualizacion50 + "'" + CodigoAlimento + "' and NIF = ("
				+ SentenciasBBDD.actividad + "(" + SentenciasBBDD.Transaccion + "))");
		try {
			Statement s;
			s = Conexion.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}