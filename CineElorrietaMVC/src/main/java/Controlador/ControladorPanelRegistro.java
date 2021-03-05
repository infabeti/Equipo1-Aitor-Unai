package Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Modelo.Modelo;
import Vista.PanelRegistro;
import Vista.Vista;

public class ControladorPanelRegistro {

	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelRegistro panelRegistro;

	public ControladorPanelRegistro(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}
	
	public Modelo getModelo() {
		return modelo;
	}

	public Vista getVista() {
		return vista;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void mostrarPanelRegistro() {
		this.panelRegistro = makePanelRegistro(this);
		this.vista.mostrarPanel(this.panelRegistro);

	}

	public void accionadoBottonVolverPanelLogin() {
		this.controlador.navegarPanelLogin();
	}

	public boolean comprobarBBDDnif(String nif) {
		return this.modelo.getConexion().comprobarSiExisteNIF(nif);
	}

	public boolean comprobarBBDDdni(String dni) {
		return this.modelo.getConexion().comprobarSiExisteDNI(dni);
	}

	public void insertarRegistro(String DNI, String Nombre, String Apellido, String contrasena, String nif) {
		this.modelo.getConexion().insertarRegistro(DNI, Nombre, Apellido, contrasena, nif);

	}

	public boolean comprobarNif(String nif) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(nif);

		if (matcher.matches()) {

			correcto = true;

		} else {

			correcto = false;

		}

		return correcto;

	}

	public boolean comprobarFormatoNombre(String nombre) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(nombre) && nombre.length() <= 20) {
			if (nombre.length() >= 3) {
				return true;
			}
			return false;

		} else {

			return false;
		}
	}

	public boolean comprobarContraNoVacia(String pass) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (pass.length() >= 5) {
			return true;
		} else {
			return false;
		}
	}

	public boolean comprobarFormatoApellido(String apellido) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(apellido) && apellido.length() <= 25) {
			if (apellido.length() >= 2) {
				return true;
			}
			return false;
		} else {

			return false;
		}
	}

	public boolean contieneSoloLetras(String cadena) {
		for (int x = 0; x < cadena.length(); x++) {
			char c = cadena.charAt(x);
			// Si no est� entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}
		return true;
	}
	
	public PanelRegistro makePanelRegistro(ControladorPanelRegistro controladorPanelRegistro) {
		return new PanelRegistro(controladorPanelRegistro);
	}

}
