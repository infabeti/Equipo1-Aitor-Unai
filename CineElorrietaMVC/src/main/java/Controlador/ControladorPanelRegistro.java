package Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Modelo.Modelo;
import Vista.PanelRegistro;
import Vista.Vista;

public class ControladorPanelRegistro {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelRegistro panelRegistro;

	public ControladorPanelRegistro(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}

	public void mostrarPanelRegistro() {
		this.panelRegistro = new PanelRegistro(this);
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

		boolean correcto = this.modelo.getUtil().comprobarNif(nif);

		return correcto;

	}

	public boolean comprobarCamposRegistro(String nombre, String apellido, String nif, String dni, String password) {

		boolean okNombre = comprobarFormatoNombre(nombre);
		boolean okApellido = comprobarFormatoApellido(apellido);
		boolean okNif = comprobarNif(nif);
		boolean  okDni = comprobarNif(dni);
		boolean  okComprobarBBDDnif= comprobarBBDDnif(nif);
		boolean  okComprobarBBDDdni= comprobarBBDDdni(dni);
		boolean  okPass= comprobarContraNoVacia(password);
		
		if (okNombre && okApellido && okNif
				&& okDni && okComprobarBBDDnif && !okComprobarBBDDdni
				&& okPass)

		{
			return true;
		} else {
			
			if (okNombre == false) {
				JOptionPane.showMessageDialog(null,
						"El nombre no puede contener caracteres que no sean letras ni puede ser mayor de 20 caracteres ni menor que 3");
			}
			if (okApellido == false) {
				JOptionPane.showMessageDialog(null,
						"El Apellido no puede contener caracteres que no sean letras ni puede ser mayor de 25 caracteres ni menor que 2");
			}
			if (okDni == false) {
				JOptionPane.showMessageDialog(null, "El dni introducido es incorrecto");
			}
			if (okNif == false) {
				JOptionPane.showMessageDialog(null, "El nif introducido es incorrecto");
			}
			if (okComprobarBBDDnif == false) {
				JOptionPane.showMessageDialog(null, "El nif introducido no pertenece a ningun local");
			}
			if (okComprobarBBDDdni) {
				JOptionPane.showMessageDialog(null, "El dni introducido ya existe en la BBDD");
			}
			if (okPass == false) {
				JOptionPane.showMessageDialog(null, "la contraseña tiene que tener un minimo de 5 caracteres");
			}
			
			return false;
		}

	}

	public boolean comprobarFormatoNombre(String nombre) {
		boolean correcto = this.modelo.getUtil().comprobarFormatoNombre(nombre);
		

		return correcto;
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
		boolean correcto = this.modelo.getUtil().comprobarFormatoApellido(apellido);
		
		return correcto;
	}

	public boolean contieneSoloLetras(String cadena) {
		boolean correcto = this.modelo.getUtil().contieneSoloLetras(cadena);

		return correcto;
	}

}
