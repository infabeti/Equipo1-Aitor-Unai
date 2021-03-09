package Controlador;

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

	public void mostrarPanelRegistro() {
		this.panelRegistro = new PanelRegistro(this);
		this.vista.mostrarPanel(this.panelRegistro);
	}

	public void accionadoBottonVolverPanelLogin() {
		this.controlador.navegarPanelLogin();
	}

	public boolean comprobarBBDDnif(String nif) {
		return this.modelo.getConsultasComprobaciones().comprobarSiExisteNIF(nif);
	}

	public boolean comprobarBBDDdni(String dni) {
		return this.modelo.getConsultasComprobaciones().comprobarSiExisteDNI(dni);
	}

	public void insertarRegistro(String DNI, String Nombre, String Apellido, String contrasena, String nif) {
		this.modelo.getInserciones().insertarRegistro(DNI, Nombre, Apellido, contrasena, nif);
	}

	public boolean comprobarNif(String nif) {
		return this.modelo.util.comprobarNif(nif);
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
		
		if (okNombre && okApellido && okNif && okDni && okComprobarBBDDnif && !okComprobarBBDDdni && okPass){
			return devolver;
		} else {
			if (okNombre == false) {
				devolver += "El nombre no puede contener caracteres que no sean letras ni puede ser mayor de 20 caracteres ni menor que 3";
			}
			else if (okApellido == false) {
				devolver += "\nEl Apellido no puede contener caracteres que no sean letras ni puede ser mayor de 25 caracteres ni menor que 2";
			}
			else if (okDni == false) {
				devolver += "\nEl dni introducido es incorrecto";
			}
			else if (okNif == false) {
				devolver += "\nEl nif introducido es incorrecto";
			}
			else if (okComprobarBBDDnif == false) {
				devolver += "\nEl nif introducido no pertenece a ningun local";
			}
			else if (okComprobarBBDDdni) {
				devolver += "\nEl dni introducido ya existe en la BBDD";
			}
			else if (okPass == false) {
				devolver += "\nla contrase�a tiene que tener un minimo de 5 caracteres";
			}
			return devolver;
		}
	}

	public boolean comprobarFormatoNombre(String nombre) {
		return this.modelo.util.comprobarFormatoNombre(nombre);
	}

	public boolean comprobarContraNoVacia(String pass) {		
		return pass.length() >= 5;
	}

	public boolean comprobarFormatoApellido(String apellido) {
		return this.modelo.util.comprobarFormatoApellido(apellido);
	}

	public boolean contieneSoloLetras(String cadena) {
		return this.modelo.util.contieneSoloLetras(cadena);
	}
}
