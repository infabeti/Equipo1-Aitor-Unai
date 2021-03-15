package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Inserciones {

	private Modelo modelo;
	private final SentenciasBBDD sentenciasBBDD = new SentenciasBBDD();
	private java.sql.Connection conexionConn;

	public Inserciones(Modelo modelo) {
		this.modelo = modelo;
		conexionConn = this.modelo.conexionConn;
	}

	public void insertarActividad(int transaccion, String fecha, double totalOperacion, String tipo, String nif) {
		try {
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARACTIVIDAD);

			st.setInt(1, transaccion);
			st.setString(2, fecha);
			st.setDouble(3, totalOperacion);
			st.setString(4, tipo);
			st.setString(5, nif);

			try {
				st.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void insertarPedido(int transaccion, String domicilio) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARPEDIDO);
			try {
				st.setInt(1, transaccion);

				if (domicilio.equalsIgnoreCase("")) {
					st.setNull(2, Types.NULL);
				} else {
					st.setString(2, domicilio);
				}
				st.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public void insertarFactura(int transaccion, String nif) {
		try {
			PreparedStatement st = null;
			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARFACTURA);
			st.setInt(1, transaccion);
			st.setString(2, nif);
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
	
	public boolean insertarComanda(int transaccion) {
		try {
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.INSERTARCOMANDA);

			try {
				st.setInt(1, transaccion);	

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

	public boolean insertarAprovisionamiento(int cantidad, int codAlimento, String nifLocal) {
		int cantidadActual = 0;
		try {
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn)
					.prepareStatement(sentenciasBBDD.CONSEGUIRCANTIDADSTOCK);

			st.setString(1, nifLocal);
			st.setInt(2, codAlimento);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				cantidadActual = rs.getInt("cantidad");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		try {
			PreparedStatement st = null;

			cantidad = cantidad + cantidadActual;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(sentenciasBBDD.REPLACESTOCK);
			try {
				st.setString(1, nifLocal);
				st.setInt(2, codAlimento);
				st.setInt(3, cantidad);

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
