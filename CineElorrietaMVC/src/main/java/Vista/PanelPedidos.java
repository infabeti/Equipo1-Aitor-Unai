package Vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelPedidos;

public class PanelPedidos extends JPanel {

	private ControladorPanelPedidos controladorPanelPedidos;
	private JLabel lblTextoPanel;

	public PanelPedidos(ControladorPanelPedidos controladorPanelPedidos) {

		this.controladorPanelPedidos = controladorPanelPedidos;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL PEDIDOS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(89, 0, 450, 67);
		add(lblTextoPanel);

		// initializeEvents();

	}
	
	

}
