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
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelRegistro extends JPanel {

	
	private JLabel lblTextoPanel;
	private JTextField textNIF;
	private JPasswordField passwordContrasena;
	private ControladorPanelRegistro controlador;
	private JButton btnVolver;
	private JButton btnRegistrar;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellido;

	
	public PanelRegistro(ControladorPanelRegistro controladorPanelRegistro) {
		setBackground(SystemColor.activeCaption);

		this.controlador = controladorPanelRegistro;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL REGISTRO:");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(191, 22, 187, 46);
		add(lblTextoPanel);
		
		JLabel lblNIF = new JLabel("NIF:");
		lblNIF.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNIF.setBounds(33, 225, 48, 23);
		add(lblNIF);

		textNIF = new JTextField();
		textNIF.setBounds(78, 228, 192, 20);
		add(textNIF);
		textNIF.setColumns(10);
	
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(137, 330, 89, 23);
		add(btnVolver);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(33, 330, 89, 23);
		add(btnRegistrar);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDNI.setBounds(330, 79, 81, 30);
		add(lblDNI);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setFont(new Font("Arial", Font.PLAIN, 17));
		lblContraseña.setBounds(33, 259, 127, 23);
		add(lblContraseña);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNombre.setBounds(28, 82, 89, 24);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 17));
		lblApellido.setBounds(28, 117, 106, 20);
		add(lblApellido);
		
		textDNI = new JTextField();
		textDNI.addKeyListener(new KeyAdapter() {
	
//para validar que sean solo 9 caracteres//
			@Override
	public void keyTyped(KeyEvent e) {
			
			char validar=e.getKeyChar();
	
			if (textDNI.getText().length() >=9) 
			{
				e.consume();
		           JOptionPane.showMessageDialog(null, "Inserte únicamente 9 caracteres");
				}	
			}		
	});
		
		/*para validar que sean solo numeros(sin la letra final del dni)
@Override
	public void keyTyped(KeyEvent e) {
		
	char validar=e.getKeyChar();

	if (Character.isLetter(validar) || textDNI.getText().length() >=9) {
		e.consume();
	    JOptionPane.showMessageDialog(null, "Inserte el DNI, sin la letra final");
			}	
		}		
});
	*/
		
		textDNI.setBounds(371, 86, 141, 20);
		add(textDNI);
		textDNI.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(101, 86, 169, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(101, 120, 169, 20);
		add(textApellido);
		textApellido.setColumns(10);
		
	    passwordContrasena = new JPasswordField();
		passwordContrasena.setBounds(137, 262, 133, 20);
		add(passwordContrasena);
	
		initializeEvents();
		
}

private void initializeEvents() {
	this.btnVolver.addActionListener(listenerBotonVolver(this.controlador));
	this.btnRegistrar.addActionListener(listenerBotonRegistrar(this.controlador));
}


private ActionListener listenerBotonVolver(ControladorPanelRegistro controladorPanelRegistro) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Volver");
			controlador.accionadoBottonVolverPanelRegistro();
		}
	};
}	


private ActionListener listenerBotonRegistrar(ControladorPanelRegistro controladorPanelRegistro) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Registrar");

			
		
			
			 String NIF = textNIF.getText();
			 
		if (controladorPanelRegistro.registro(NIF)) {
			
			JOptionPane.showMessageDialog(null, "Registro correcto");
			controladorPanelRegistro.accionadoBottonRegistrarPanelRegistro();
		}
		else
		{
           JOptionPane.showMessageDialog(null, "Registro incorrecto");

		}
	}
};	
}
}
		