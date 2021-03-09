package Controlador;

import Modelo.Modelo;
import Vista.Vista;
import Vista.PanelComandas;

public class ControladorPanelComandas {
	
	private Modelo modelo;
	private Vista vista;
	private Controlador controlador;
	private PanelComandas panelComandas;
	
	public ControladorPanelComandas(Modelo modelo, Vista vista, Controlador controlador) {
		this.modelo = modelo;
		this.vista = vista;
		this.controlador = controlador;
	}
	
	public void mostrarPanelComandas() {
		this.panelComandas = makePanelComandas(this);
		this.vista.mostrarPanel(this.panelComandas);
	}
	
	public void accionadoBotonVolverPanelPrincipal() {
		this.controlador.navegarPanelPrincipal();
	}
	
	public PanelComandas makePanelComandas(ControladorPanelComandas controladorPanelComandas) {
		return new PanelComandas(controladorPanelComandas);
	}
	
	public String[] cogerListaProductos() {
		return modelo.getListaProductos().getListaProductosString();
	}
	
	public String[] cogerListaPlatos() {
		return modelo.getListaPlatos().getListaPlatosString();
	}
	
}
