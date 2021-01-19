 package Vista;

import javax.swing.JPanel;

import Controlador.ControladorPanelPrincipal;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class PanelPrincipal extends JPanel {

	private ControladorPanelPrincipal controladorPanelPrincipal;
	private JButton btnPedidos;
	private JButton btnTickets;
	private JButton btnFacturas;
	private JButton btnAprovisionamiento;
	private JButton btnComandas;
	private JLabel lblTextoPanel;

	public PanelPrincipal(ControladorPanelPrincipal controladorPanelPrincipal) {
		setBackground(SystemColor.activeCaption);

		
			this.controladorPanelPrincipal = controladorPanelPrincipal;
			
			setLayout(null);
			
			btnPedidos = new JButton("Pedidos");
			btnPedidos.setBounds(50, 112, 112, 36);
			add(btnPedidos);
			
			btnTickets = new JButton("Tickets");
			btnTickets.setBounds(369, 112, 118, 36);
			add(btnTickets);
			
			btnFacturas = new JButton("Facturas");
			btnFacturas.setBounds(50, 159, 112, 36);
			add(btnFacturas);
			
			btnAprovisionamiento = new JButton("Aprovisionamiento");
			btnAprovisionamiento.setBounds(184, 248, 184, 33);
			add(btnAprovisionamiento);
			
			btnComandas = new JButton("Comandas");
			btnComandas.setBounds(369, 159, 118, 36);
			add(btnComandas);
			
			lblTextoPanel = new JLabel("PANEL PRINCIPAL");
			lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
			lblTextoPanel.setBounds(0, 0, 450, 67);
			add(lblTextoPanel);
			
			
			
			initializeEvents();
		
		
	}
	
	private void initializeEvents() {
		this.btnPedidos.addActionListener(listenerBotonPedidos(this.controladorPanelPrincipal));
		this.btnAprovisionamiento.addActionListener(listenerBotonAprovisionamiento(this.controladorPanelPrincipal));
		this.btnTickets.addActionListener(listenerBotonTickets(this.controladorPanelPrincipal));
		this.btnFacturas.addActionListener(listenerBotonFacturas(this.controladorPanelPrincipal));
	}
	
	private ActionListener listenerBotonPedidos(ControladorPanelPrincipal controladorPanelPrincipal) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelPrincipal.accionadoBottonMostrarPanelPedidos();
			}
		};
	}
	
	private ActionListener listenerBotonAprovisionamiento(ControladorPanelPrincipal controladorPanelPrincipal) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Aprovisionamiento");
				controladorPanelPrincipal.accionadoBottonMostrarPanelAprovisionamiento();
			}
		};
	}
	
	private ActionListener listenerBotonTickets(ControladorPanelPrincipal controladorPanelPrincipal) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Tickets");
				controladorPanelPrincipal.accionadoBottonMostrarPanelTickets();
			}
		};
	}
	
	private ActionListener listenerBotonFacturas(ControladorPanelPrincipal controladorPanelPrincipal) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Facturas");
				controladorPanelPrincipal.accionadoBottonMostrarPanelFacturas();
			}
		};
	}
	
}
