package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inserciones {

	private Modelo modelo;
	private final SentenciasBBDD sentenciasBBDD = new SentenciasBBDD();
	private java.sql.Connection conexionConn;

	public Inserciones(Modelo modelo) {
		this.modelo = modelo;
		conexionConn = this.modelo.conexionConn;
	}

	public void insertarProductoActividad(int transaccion, String codigoAlimento, int cantidad, double precioFinal) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARPRODUCTOACTIVIDAD);
			st.setString(1, codigoAlimento);
			st.setInt(2, transaccion);
			st.setInt(3, cantidad);
			st.setDouble(4, precioFinal);
			try {
				st.executeUpdate();
				actualizarStockMenorQueCinco(codigoAlimento);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void actualizarStockMenorQueCinco(String codigoAlimento) {

		String nif = this.modelo.getUser().getNifLocal();

		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.CODIGOALIMENTO);
			st.setString(1, codigoAlimento);
			st.setString(2, nif);

			try {
				ResultSet rs = st.executeQuery();
				rs.next();
				int cantidad = rs.getInt("cantidad");
				if (cantidad < 5) {

					updateStock(nif, codigoAlimento, cantidad);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void updateStock(String nif, String codigoAlimento, int cantidad) {

		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.ACTUALIZARSTOCK);
			st.setInt(1, (cantidad+50));
			st.setString(2, nif);
			st.setString(3, codigoAlimento);

			try {
				st.executeUpdate();			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void insertarComprador(String nif, String nombre, String apellido) {
		try {
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARCOMPRADOR);
			st.setString(1, nif);
			st.setString(2, nombre);
			st.setString(3, apellido);
			try {
				st.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public boolean insertarRegistro(String dni, String Nombre, String Apellido, String contrasena, String nif) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTAREMPLEADO);
			try {
				st.setString(1, dni);
				st.setString(2, Nombre);
				st.setString(3, Apellido);
				st.setString(4, contrasena);
				st.setString(5, nif);
				st.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}

	public void insertarPlatoActividad(int transaccion, String codigoPlato, int cantidad) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement("insert into lineaplato (codigoplato,transaccion,cantidad)" + " values("
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