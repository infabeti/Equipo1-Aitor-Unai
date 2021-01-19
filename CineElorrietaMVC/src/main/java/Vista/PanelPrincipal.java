 package Vista;

import javax.swing.JPanel;

import Controlador.ControladorPanelPrincipal;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {

	private ControladorPanelPrincipal controladorPanelPrincipal;
	private JButton btnPedidos;
	private JButton btnTickets;
	private JButton btnFacturas;
	private JButton btnAprovisionamiento;
	private JButton btnComandas;
	private JLabel lblTextoPanel;

	public PanelPrincipal(ControladorPanelPrincipal controladorPanelPrincipal) {

		
			this.controladorPanelPrincipal = controladorPanelPrincipal;
			
			setLayout(null);
			
			btnPedidos = new JButton("Pedidos");
			btnPedidos.setBounds(50, 112, 89, 23);
			add(btnPedidos);
			
			btnTickets = new JButton("Tickets");
			btnTickets.setBounds(280, 112, 89, 23);
			add(btnTickets);
			
			btnFacturas = new JButton("Facturas");
			btnFacturas.setBounds(50, 159, 89, 23);
			add(btnFacturas);
			
			btnAprovisionamiento = new JButton("Aprovisionamiento");
			btnAprovisionamiento.setBounds(133, 216, 162, 23);
			add(btnAprovisionamiento);
			
			btnComandas = new JButton("Comandas");
			btnComandas.setBounds(280, 159, 89, 23);
			add(btnComandas);
			
			lblTextoPanel = new JLabel("PANEL PRINCIPAL");
			lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
			lblTextoPanel.setBounds(89, 0, 450, 67);
			add(lblTextoPanel);
			
			
			
			initializeEvents();
		
		
	}
	
	private void initializeEvents() {
		this.btnPedidos.addActionListener(listenerBotonPedidos(this.controladorPanelPrincipal));
	}
	
	private ActionListener listenerBotonPedidos(ControladorPanelPrincipal controladorPanelPrincipal) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelPrincipal.accionadoBottonMostrarPanelPedidos();
			}
		};
	}
	
}
