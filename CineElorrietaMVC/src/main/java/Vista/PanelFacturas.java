package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelFacturas;
import Controlador.ControladorPanelPedidos;

public class PanelFacturas extends JPanel {

	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private ControladorPanelFacturas controladorPanelFacturas;

	public PanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelFacturas = controladorPanelFacturas;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL FACTURAS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(425, 374, 89, 23);
		add(btnVolver);
		
		

		initializeEvents();

	}
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelFacturas));
	}
	
	private ActionListener listenerBotonVolver(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelFacturas.accionadoBottonVolverPanelPrincipal();
			}
		};
	}

}
