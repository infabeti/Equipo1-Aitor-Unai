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
	private final String URL = "jdbc:mysql://localhost:3306/" + NOMBREBD + "?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";

	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	// constructor de la clase
	public Conexion(Modelo modelo) {
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
			System.out.println("Verifique que Mysql est� encendido...");
			System.exit(0);

		}
	}

	public void desconectar() {
		conn = null;
		System.out.println("Desconexion realizada correctamente");
	}

	public Usuario login(String dni, String password) {
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement("Select e.nombre, es.nombre, tipoNegocio from empleado e join establecimiento es on e.NIF = es.NIF where dni=? and contrasena=?");

			st.setString(1, dni);
			st.setString(2, password);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String local = rs.getString("es.nombre");
				String tipoNegocio = rs.getString("tipoNegocio");
				Usuario user = new Usuario(nombre, local, tipoNegocio);
				return user;
			} else {
				Usuario user =  new Usuario("", "", "");
				return user ;
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		Usuario user = new Usuario("", "", "");
		return user;
	}

	public boolean registro(String NIF) {
		try {
			
			java.sql.Connection conexionConn = this.getConn();
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
