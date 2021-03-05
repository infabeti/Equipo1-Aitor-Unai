package Controlador;

import Modelo.Modelo;
import Vista.Vista;
import Modelo.Usuario;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ControladorLogin controladorLogin;
	private ControladorPanelPrincipal controladorPanelPrincipal;
	private ControladorPanelPedidos controladorPanelPedidos;
	private ControladorPanelAprovisionamiento controladorPanelAprovisionamiento;
	private ControladorPanelFacturas controladorPanelFacturas;
	private ControladorPanelTickets controladorPanelTickets;
	private ControladorPanelRegistro controladorPanelRegistro;
	private ControladorPanelComandas controladorPanelComandas;
	
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;

		this.controladorLogin = new ControladorLogin(this.modelo, this.vista, this);
		this.controladorPanelPrincipal = new ControladorPanelPrincipal(this.modelo, this.vista, this);
		this.controladorPanelPedidos = new ControladorPanelPedidos(this.modelo, this.vista, this);
		this.controladorPanelAprovisionamiento = new ControladorPanelAprovisionamiento(this.modelo, this.vista, this);
		this.controladorPanelFacturas = new ControladorPanelFacturas(this.modelo, this.vista, this);
		this.controladorPanelTickets = new ControladorPanelTickets(this.modelo, this.vista, this);
		this.controladorPanelRegistro = new ControladorPanelRegistro(this.modelo, this.vista, this);
		this.controladorPanelComandas = new ControladorPanelComandas(this.modelo, this.vista, this);

		this.navegarPanelLogin();
	}
	
	public void navegarPanelLogin() {
		System.out.println("Login de los usuarios");
		this.controladorLogin.mostrarPanelLogin();
		
	}
	public void navegarPanelRegistro() {
		System.out.println("Navegar panel de registro");
		this.controladorPanelRegistro.mostrarPanelRegistro();
	
	}
		
	public void navegarPanelPrincipal() {
		System.out.println("Navegar panel principal");
		this.controladorPanelPrincipal.mostrarPanelPrincipal();
	}
	
	
	public void navegarPanelPedidos() {
		System.out.println("Navegar panel Pedidos");
		Usuario user = this.modelo.getUser();
		if(user.getTipoLocal().equals("RESTAURANTE") || user.getTipoLocal().equals("CAFETERIA")) {
			this.controladorPanelPedidos.mostrarPanelPedidos();
		}
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
	
	public void navegarPanelComandas() {
		System.out.println("Navegar panel Comandas");
		Usuario user = modelo.getUser();
		if(user.getTipoLocal().equals("RESTAURANTE")) {
			 this.controladorPanelComandas.mostrarPanelComandas();
		}
	}
	
	
}
