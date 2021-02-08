package Controlador;

import Modelo.Conexion;
import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelLogin;
import Vista.PanelPedidos;
import Vista.PanelRegistro;
import Vista.Vista;

public class ControladorPanelRegistro {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelRegistro panelRegistro;
	private Conexion conexion = new Conexion();
	
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
	
public boolean registro(String NIF) {
		
	boolean res = conexion.registro(NIF);
	
	return res;
	
		
	}
}