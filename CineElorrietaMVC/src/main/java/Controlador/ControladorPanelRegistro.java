package Controlador;

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

	public void accionadoBottonVolverPanelRegistro() {
		this.controlador.navegarPanelLogin();
	}

	public void accionadoBottonRegistrarPanelRegistro() {
		this.controlador.navegarPanelLogin();
	}


	public void insertarRegistro(String DNI, String Nombre, String Apellido, String contrasena, String nif) {
		boolean ok = this.modelo.getConexion().insertarRegistro(DNI, Nombre, Apellido, contrasena, nif);
		if(ok) {
			this.controlador.navegarPanelLogin();
		}
	}
	


}
