package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controlador.Controlador;
import Controlador.ControladorLogin;
import Controlador.ControladorPanelPedidos;
import Controlador.ControladorPanelPrincipal;
import Controlador.ControladorPanelRegistro;
import Controlador.ControladorPanelTickets;
import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JFormattedTextField;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;

public class PanelRegistro extends JPanel {

	private ControladorLogin controladorPanelPedidos;
	private ControladorPanelRegistro controladorPanelRegistro;
	private JLabel lblTextoPanel;
	private JTextField textFieldNomUsuario;
	private JTextField textFieldContraseña;
	private ControladorPanelRegistro controlador;
	private JButton btnVolver;
	private JPasswordField passwordFieldContraseña;
	
	public PanelRegistro(ControladorPanelRegistro controladorPanelRegistro) {
		setBackground(SystemColor.activeCaption);

		this.controlador = controladorPanelRegistro;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL REGISTRO:");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(28, 22, 187, 46);
		add(lblTextoPanel);
		
		JLabel lblNIF = new JLabel("NIF:");
		lblNIF.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNIF.setBounds(28, 79, 187, 23);
		add(lblNIF);

		textFieldNomUsuario = new JTextField();
		textFieldNomUsuario.setBounds(153, 77, 164, 30);
		add(textFieldNomUsuario);
		textFieldNomUsuario.setColumns(10);
	
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(301, 220, 89, 23);
		add(btnVolver);

	
		initializeEvents();
		
}

private void initializeEvents() {
	this.btnVolver.addActionListener(listenerBotonVolver(this.controlador));
}


private ActionListener listenerBotonVolver(ControladorPanelRegistro controladorPanelRegistro) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Volver");
			controladorPanelRegistro.accionadoBottonVolverPanelRegistro();
		}
	};
}	
}
		