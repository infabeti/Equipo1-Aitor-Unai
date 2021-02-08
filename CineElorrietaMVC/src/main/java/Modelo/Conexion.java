package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	private String nombreBd = "reto3";
	private String usuario = "root";
	private String password = "elorrieta";
	private String url = "jdbc:mysql://localhost:3306/" + nombreBd + "?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";

	private String user, pass;
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
			conn = DriverManager.getConnection(url, usuario, password);
			if (conn == null) {
				System.out.println("******************NO SE PUDO CONECTAR " + nombreBd);
				System.exit(0);
			} else {
				System.out.println(
						"Conectado correctamente a la base de datos " + nombreBd + " con el usuario " + usuario);
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

	public Conexion(String user, String pass) {
		try {
			// obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// obtener la conexion
			conn = DriverManager.getConnection(url, user, pass);
			if (conn == null) {
				System.out.println("******************NO SE PUDO CONECTAR " + nombreBd);
				System.exit(0);
			} else {
				System.out.println(
						"Conectado correctamente a la base de datos " + nombreBd + " con el usuario " + usuario);
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

	public Connection getConnection() {
		return conn;

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
