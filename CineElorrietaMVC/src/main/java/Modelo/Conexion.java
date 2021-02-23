package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"Select e.nombre, es.nombre, tipoNegocio, e.NIF from empleado e join establecimiento es on e.NIF = es.NIF where dni=? and contrasena=?");

			st.setString(1, dni);
			st.setString(2, password);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String local = rs.getString("es.nombre");
				String tipoNegocio = rs.getString("tipoNegocio");
				String NIF = rs.getString("NIF");
				Usuario user = new Usuario(nombre, local, tipoNegocio, NIF);
				return user;
			} else {
				Usuario user = new Usuario("", "", "", "");
				return user;
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		Usuario user = new Usuario("", "", "", "");
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

	public int leerNumTransBBDD() {

		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement("select * from actividad;");

			ResultSet rs = st.executeQuery();

			int numero = 1;

			try {
				while (rs.next()) {
					numero++;
				}
				return numero;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 1;
	}

	public int leerNifLocal() {

		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement("select * from establecimiento ;");

			ResultSet rs = st.executeQuery();

			try {
				if (rs.next()) {
					return rs.getInt("transaccion") + 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 999999;
	}

	public String obtenerCodigoAlimentoProducto(String producto) {

		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement("select * from alimento ;");

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

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String nif) {
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement("insert into actividad "
					+ "values(" + transaccion + ",'" + fecha + "'," + totalOperacion + ",'" + nif + "');");

			try {
				st.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	};

	public void insertarProductoActividad(int transaccion, String codigoAlimento, int cantidad, double precioFinal) {
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"insert into lineaproducto (codigoalimento,transaccion,cantidad,preciofinal)" + "values("
							+ codigoAlimento + ",'" + transaccion + "'," + cantidad + ",'" + precioFinal + "');");
			/**************/
			try {
				st.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	};

	public ListaProductos cogerProductosLocal(String NIFLocal) {
		ListaProductos listaProd = new ListaProductos();
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"Select a.Nombre, a.PCompra, p.PVenta, a.Tipo, a.FeCad from alimento a join producto p on a.CodigoAlimento = p.CodigoAlimento join stock s on a.CodigoAlimento = s.CodigoAlimento where s.NIF=?");

			st.setString(1, NIFLocal);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("a.nombre");
				double pCompra = rs.getDouble("a.PCompra");
				double pVenta = rs.getDouble("p.PVenta");
				String tipo = rs.getString("a.Tipo");
				Date feCad = rs.getDate("a.FeCad");
				Producto prod = new Producto(nombre, feCad, tipo, pCompra, pVenta);
				listaProd.addProductoTemporal(prod);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return listaProd;
	}
}
