package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	// constructor de la clase

	private final String NOMBREBD = "reto3";
	private final String USUARIO = "root";
	private final String PASSWORD = "elorrieta";
	private final String URL = "jdbc:mysql://localhost:33060/" + NOMBREBD + "?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";

	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	// constructor de la clase
	public Conexion() {

		try {
			// obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// obtener la conexion
			conn = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			if (conn == null) {
				System.out.println("******************NO SE PUDO CONECTAR " + NOMBREBD);

				System.exit(0);
			} else {
				System.out.println(
						"Conectado correctamente a la base de datos " + NOMBREBD + " con el usuario " + USUARIO);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : " + e.getMessage());
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: " + e.getMessage());
			System.out.println("Verifique que Mysql esté encendido...");
			System.exit(0);

		}
	}

	public void desconectar() {
		conn = null;
		System.out.println("Desconexion realizada correctamente");
	}

	public boolean login(String userName, String password) {
		try {
			Conexion conexion1 = new Conexion();
			java.sql.Connection conexionConn = conexion1.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement("Select nombre, apellido from empleado where nombre=? and contrasena=?");

			st.setString(1, userName);
			st.setString(2, password);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return false;
	}

	public boolean registro(String NIF) {
		try {
			Conexion conexion = new Conexion();
			java.sql.Connection conexionConn = conexion.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement("Select nif from empleado where NIF=?");

			st.setString(1, NIF);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return false;
	}
}
