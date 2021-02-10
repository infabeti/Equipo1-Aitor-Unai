package Controlador;

import Modelo.Modelo;
import Vista.Vista;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ControladorPanelPrincipal controladorPanelPrincipal;
	private ControladorPanelPedidos controladorPanelPedidos;
	private ControladorPanelAprovisionamiento controladorPanelAprovisionamiento;
	private ControladorPanelFacturas controladorPanelFacturas;
	private ControladorPanelTickets controladorPanelTickets;
	
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.controladorPanelPrincipal = new ControladorPanelPrincipal(this.modelo, this.vista, this);
		this.controladorPanelPedidos = new ControladorPanelPedidos(this.modelo, this.vista, this);
		this.controladorPanelAprovisionamiento = new ControladorPanelAprovisionamiento(this.modelo, this.vista, this);
		this.controladorPanelFacturas = new ControladorPanelFacturas(this.modelo, this.vista, this);
		this.controladorPanelTickets = new ControladorPanelTickets(this.modelo, this.vista, this);
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
	
	public void navegarPanelAprovisionamiento() {
		System.out.println("Navegar panel Aprovisionamiento");
		this.controladorPanelAprovisionamiento.mostrarPanelAprovisionamiento();
	}
	
	public void navegarPanelFacturas() {
		System.out.println("Navegar panel Facturas");
		this.controladorPanelFacturas.mostrarPanelFacturas();
	}
	
	public void navegarPanelTickets() {
		System.out.println("Navegar panel Pedidos");
		this.controladorPanelTickets.mostrarPanelTickets();
	}
	
}
