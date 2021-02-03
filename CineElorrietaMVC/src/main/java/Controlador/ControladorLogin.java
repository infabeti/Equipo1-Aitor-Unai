package Controlador;

import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelLogin;
import Vista.PanelPedidos;
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
	
}
