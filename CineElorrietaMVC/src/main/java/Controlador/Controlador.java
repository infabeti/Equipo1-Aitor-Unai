package Controlador;

import Modelo.Modelo;
import Vista.Vista;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ControladorPanelPrincipal controladorPanelPrincipal;
	private ControladorPanelPedidos controladorPanelPedidos;
	
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.controladorPanelPrincipal = new ControladorPanelPrincipal(this.modelo, this.vista, this);
		
		this.navegarPanelPrincipal();
	}
	
	public void navegarPanelPrincipal() {
		System.out.println("Navegar panel principal");
		this.controladorPanelPrincipal.mostrarPanelPrincipal();
	}
	
	public void navegarPanelPedidos() {
		System.out.println("Navegar panel Pedidos");
		this.controladorPanelPedidos.mostrarPanelPedidos();
	}
	
	
}
