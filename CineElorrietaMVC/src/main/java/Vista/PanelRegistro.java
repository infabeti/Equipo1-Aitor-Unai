package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelRegistro;

import java.awt.SystemColor;
import javax.swing.JTextField;

import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelRegistro extends JPanel {

	private static final long serialVersionUID = 8145987517742125982L;
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

		JLabel lblContrasenna = new JLabel("Contrase\u00F1a:");
		lblContrasenna.setFont(new Font("Arial", Font.PLAIN, 17));
		lblContrasenna.setBounds(33, 259, 127, 23);
		add(lblContrasenna);

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

				char validar = e.getKeyChar();

				if (textDNI.getText().length() >= 9) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Inserte ï¿½nicamente 9 caracteres");
				}
			}
		});

		/*
		 * para validar que sean solo numeros(sin la letra final del dni)
		 * 
		 * @Override public void keyTyped(KeyEvent e) {
		 * 
		 * char validar=e.getKeyChar();
		 * 
		 * if (Character.isLetter(validar) || textDNI.getText().length() >=9) {
		 * e.consume(); JOptionPane.showMessageDialog(null,
		 * "Inserte el DNI, sin la letra final"); } } });
		 */

		textDNI.setBounds(371, 86, 141, 20);
		add(textDNI);
		textDNI.setColumns(10);

		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
		
			@Override
		public void keyTyped(KeyEvent e) {

				char validar = e.getKeyChar();

				if (Character.isDigit(validar) || textNombre.getText().length() >=20) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Inserte únicamente 20 caracteres");
				}
			}
		});

		
		textNombre.setBounds(101, 86, 169, 20);
		add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			
			char validar = e.getKeyChar();

			if (Character.isDigit(validar) || textApellido.getText().length() >=25) {
				e.consume();
				JOptionPane.showMessageDialog(null, "Inserte únicamente 25 caracteres");
			}
		}
	});
		
		textApellido.setBounds(101, 120, 169, 20);
		add(textApellido);
		textApellido.setColumns(10);

		passwordContrasena = new JPasswordField();
		passwordContrasena.addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyTyped(KeyEvent e) {
	
			if (passwordContrasena.getText().length() >= 18) {
				e.consume();
				JOptionPane.showMessageDialog(null, "Inserte únicamente 18 caracteres");
			}
		}
	});
		
		
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

	/*private ActionListener listenerBotonRegistrar(ControladorPanelRegistro controladorPanelRegistro) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Registrar");
				String NIF = textNIF.getText();

				if (controladorPanelRegistro.registro(NIF)) {

					JOptionPane.showMessageDialog(null, "Registro correcto");
					controladorPanelRegistro.accionadoBottonRegistrarPanelRegistro();
				} else {
					JOptionPane.showMessageDialog(null, "Registro incorrecto");

				}
			}
		};
	}
}*/
	private ActionListener listenerBotonRegistrar(ControladorPanelRegistro controladorPanelRegistro) {
	return new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Ejecutando evento Boton Registrar");

			String nif = textNIF.getText();

			if (controladorPanelRegistro.registro(nif)) {
				
			}
					// insertar datos en empleado
					controladorPanelRegistro.insertarRegistro(textDNI.getText(),
							textNombre.getText(),
							textApellido.getText(),
							passwordContrasena.getText(),
							textNIF.getText());

					System.out.println("Te has registrado con en este nif: " + nif);
		}  
		{
			JOptionPane.showMessageDialog(null, "Registro iniciado");
		}
		};
	}
}