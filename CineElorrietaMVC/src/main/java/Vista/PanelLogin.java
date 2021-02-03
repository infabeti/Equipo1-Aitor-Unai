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

public class PanelLogin extends JPanel {

	private ControladorLogin controladorPanelPedidos;
	private JLabel lblTextoPanel;
	private JTextField textFieldNomUsuario;
	private JTextField textFieldContraseña;
	private ControladorLogin controlador;
	private JButton btnAceptar;
	
	public PanelLogin(ControladorLogin controladorLogin) {
		setBackground(SystemColor.activeCaption);

		this.controlador = controladorLogin;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL LOGIN");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(195, 23, 187, 46);
		add(lblTextoPanel);

		JLabel lblNumTrans = new JLabel("Nombre de usuario:");
		lblNumTrans.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNumTrans.setBounds(28, 80, 187, 23);
		add(lblNumTrans);

		textFieldNomUsuario = new JTextField();
		textFieldNomUsuario.setBounds(195, 78, 164, 30);
		add(textFieldNomUsuario);
		textFieldNomUsuario.setColumns(10);

		JLabel lblLocal = new JLabel("Contrase\u00F1a:");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 17));
		lblLocal.setBounds(28, 122, 113, 23);
		add(lblLocal);

		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(139, 120, 220, 30);
		add(textFieldContraseña);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(270, 166, 89, 23);
		add(btnAceptar);

		initializeEvents();
	
}
	


private void initializeEvents() {
	this.btnAceptar.addActionListener(listenerBotonAceptar(this.controlador));
}

private ActionListener listenerBotonAceptar(ControladorLogin controladorLogin) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Aceptar");
			controladorLogin.accionadoBottonAceptarPanelPrincipal();
		}
	};
}}
		