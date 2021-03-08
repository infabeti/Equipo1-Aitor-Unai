package Controlador;

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

	public String comprobarCamposRegistro(String nombre, String apellido, String nif, String dni, String password) {

		boolean okNombre = comprobarFormatoNombre(nombre);
		boolean okApellido = comprobarFormatoApellido(apellido);
		boolean okNif = comprobarNif(nif);
		boolean  okDni = comprobarNif(dni);
		boolean  okComprobarBBDDnif= comprobarBBDDnif(nif);
		boolean  okComprobarBBDDdni= comprobarBBDDdni(dni);
		boolean  okPass= comprobarContraNoVacia(password);
		String devolver = "";
		
		if (okNombre && okApellido && okNif && okDni && okComprobarBBDDnif && !okComprobarBBDDdni && okPass)

		{
			return devolver;
		} else {
			
			if (okNombre == false) {
				devolver = "El nombre no puede contener caracteres que no sean letras ni puede ser mayor de 20 caracteres ni menor que 3";
			}
			else if (okApellido == false) {
				devolver = "El Apellido no puede contener caracteres que no sean letras ni puede ser mayor de 25 caracteres ni menor que 2";
			}
			else if (okDni == false) {
				devolver = "El dni introducido es incorrecto";
			}
			else if (okNif == false) {
				devolver = "El nif introducido es incorrecto";
			}
			else if (okComprobarBBDDnif == false) {
				devolver = "El nif introducido no pertenece a ningun local";
			}
			else if (okComprobarBBDDdni) {
				devolver = "El dni introducido ya existe en la BBDD";
			}
			else if (okPass == false) {
				devolver = "la contrase�a tiene que tener un minimo de 5 caracteres";
			}
			
			return devolver;
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
