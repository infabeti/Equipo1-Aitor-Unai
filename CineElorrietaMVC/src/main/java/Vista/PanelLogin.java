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

public class PanelLogin extends JPanel {

	private ControladorLogin controladorPanelPedidos;
	private ControladorPanelRegistro controladorPanelRegistro;
	private JLabel lblTextoPanel;
	private JTextField textFieldNomUsuario;
	private JTextField textFieldContraseña;
	private ControladorLogin controlador;
	private JButton btnAceptar;
	private JButton btnRegistro;
	private JPasswordField passwordFieldContraseña;
	private JPasswordField passwordFieldContrasena;
	
	public PanelLogin(ControladorLogin controladorLogin) {
		setBackground(SystemColor.activeCaption);

		this.controlador = controladorLogin;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL LOGIN");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(28, 23, 187, 46);
		add(lblTextoPanel);

		JLabel lblNumTrans = new JLabel("Nombre de usuario:");
		lblNumTrans.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNumTrans.setBounds(28, 80, 187, 23);
		add(lblNumTrans);

		textFieldNomUsuario = new JTextField();
		textFieldNomUsuario.setBounds(180, 78, 164, 30);
		add(textFieldNomUsuario);
		textFieldNomUsuario.setColumns(10);

		JLabel lblLocal = new JLabel("Contrase\u00F1a:");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 17));
		lblLocal.setBounds(28, 122, 113, 23);
		add(lblLocal);

		
		passwordFieldContrasena = new JPasswordField();
		passwordFieldContrasena.setBounds(125, 114, 219, 28);
		add(passwordFieldContrasena);
	
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(352, 247, 89, 23);
		add(btnAceptar);
		
		btnRegistro= new JButton("Registro");
		btnRegistro.setBounds(96, 247, 89, 23);
		add(btnRegistro);
		
		initializeEvents();
	
}	

private void initializeEvents() {
	this.btnAceptar.addActionListener(listenerBotonAceptar(this.controlador));
	this.btnRegistro.addActionListener(listenerBotonRegistro(this.controlador));

}

private ActionListener listenerBotonAceptar(ControladorLogin controladorLogin) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Aceptar");
			controladorLogin.accionadoBottonAceptarPanelPrincipal();
		}
	};
}	

private ActionListener listenerBotonRegistro(ControladorLogin controladorLogin) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Registro");
			controladorLogin.accionadoBottonRegistroPanelLogin();
		}
	};
}
}
		