package Controlador;

import Modelo.Modelo;
import Modelo.Usuario;
import Vista.PanelLogin;

import Vista.Vista;

public class ControladorLogin {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelLogin panelLogin;

	public ControladorLogin(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}

	public void mostrarPanelLogin() {
		this.panelLogin = new PanelLogin(this);
		this.vista.mostrarPanel(this.panelLogin);
	}

	public void accionadoBottonAceptarPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}

	public void accionadoBottonRegistroPanelLogin() {
		this.controlador.navegarPanelRegistro();
	}

	public boolean login(String user, String password) {

		Usuario res = this.modelo.getConsultas().login(user, password);

		this.modelo.setUser(res);

		if (res.getNombre().equals("")) {
			return false;
		} else {
			modelo.actualizarListaProductosLocal();
			return true;
		}

	}

}
