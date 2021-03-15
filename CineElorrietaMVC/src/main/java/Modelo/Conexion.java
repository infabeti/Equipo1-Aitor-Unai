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
			System.out.println("Verifique que Mysql est� encendido...");
			System.exit(0);
		}
	}
	
	public ListaPlatos cogerListaPlatos(String NIFLocal) {
		ListaPlatos listaPlatos = new ListaPlatos();
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"Select p.Nombre, p.pvp from plato p join carta c on p.codigoplato = c.codigoplato where c.nif=?");
			
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
	
	public ListaProductos cogerProductosAprovisionamiento() {
		ListaProductos listaProd = new ListaProductos();
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"Select a.Nombre, a.PCompra, a.Tipo, a.FeCad from alimento a order by a.CodigoAlimento asc");

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
	
	public String obtenerCodigoPlato(String plato) {

		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement("select * from plato ;");

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
	public void insertarPlatoActividad(int transaccion, String codigoPlato, int cantidad) {
		try {
			java.sql.Connection conexionConn = this.getConn();
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(
					"insert into lineaplato (codigoplato,transaccion,cantidad)" + " values("
							+ codigoPlato + "," + transaccion + "," + cantidad + ");");
			try {
				st.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

	}
}