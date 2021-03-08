package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasComprobaciones {
	
	private Modelo modelo;
	private java.sql.Connection conexionConn;
	private final SentenciasBBDD sentenciasBBDD = new SentenciasBBDD();
	
	public ConsultasComprobaciones(Modelo modelo) {
		this.modelo = modelo;
		conexionConn =  this.modelo.conexionConn;
	}

	public boolean comprobarSiExisteNIF(String nif) {
		try {

			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(sentenciasBBDD.CONSULTANIF);
			st.setString(1, nif);
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
	
	public boolean comprobarSiExisteComprador(String nif) {
		try {

			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(sentenciasBBDD.EXISTECOMPRADOR);
			st.setString(1, nif);
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

	public boolean comprobarSiExisteDNI(String dni) {
		try {
			PreparedStatement st = null;

			st = (PreparedStatement) ((java.sql.Connection) conexionConn).prepareStatement(sentenciasBBDD.CONSULATDNI);
			st.setString(1, dni);
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
