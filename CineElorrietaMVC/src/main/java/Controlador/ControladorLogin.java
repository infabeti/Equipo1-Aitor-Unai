package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Modelo.Conexion;
import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.PanelLogin;
import Vista.PanelPedidos;
import Vista.PanelRegistro;
import Vista.Vista;

public class ControladorLogin {

	@SuppressWarnings("unused")
	private Modelo modelo;
	private Vista vista;
	@SuppressWarnings("unused")
	private Controlador controlador;
	private PanelLogin panelLogin;
	private Conexion conexion = new Conexion();

	
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

	public boolean login(String userName,String password) {
		
		boolean res = conexion.login(userName, password);
		
		return res;
		
	}

}