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
				controladorPanelRegistro.accionadoBottonVolverPanelLogin();
			}
		};
	}

	private ActionListener listenerBotonRegistrar(ControladorPanelRegistro controladorPanelRegistro) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Registrar");

				String nombre = textNombre.getText();
				String dni = textDNI.getText();
				String nif = textNIF.getText();
				String apellido = textApellido.getText();
				@SuppressWarnings("deprecation")
				String password = passwordContrasena.getText();

				if (controladorPanelRegistro.comprobarFormatoNombre(nombre)
						&& controladorPanelRegistro.comprobarFormatoApellido(apellido)
						&& controladorPanelRegistro.comprobarNif(nif) && controladorPanelRegistro.comprobarNif(dni)
						&& controladorPanelRegistro.comprobarBBDDnif(nif)
						&& !controladorPanelRegistro.comprobarBBDDdni(dni)
						&& controladorPanelRegistro.comprobarContraNoVacia(password))

				{
					// insertar datos en empleado
					controladorPanelRegistro.insertarRegistro(dni, nombre, apellido, password, nif);

					JOptionPane.showMessageDialog(null, "Empleado introducido correctamente");
					controladorPanelRegistro.accionadoBottonVolverPanelLogin();

				} else {
					if (controladorPanelRegistro.comprobarFormatoNombre(nombre) == false) {
						JOptionPane.showMessageDialog(null,
								"El nombre no puede contener caracteres que no sean letras ni puede ser mayor de 20 caracteres ni menor que 3");
					}
					if (controladorPanelRegistro.comprobarFormatoApellido(apellido) == false) {
						JOptionPane.showMessageDialog(null,
								"El Apellido no puede contener caracteres que no sean letras ni puede ser mayor de 25 caracteres ni menor que 2");
					}
					if (controladorPanelRegistro.comprobarNif(dni) == false) {
						JOptionPane.showMessageDialog(null, "El dni introducido es incorrecto");
					}
					if (controladorPanelRegistro.comprobarNif(nif) == false) {
						JOptionPane.showMessageDialog(null, "El nif introducido es incorrecto");
					}
					if (controladorPanelRegistro.comprobarBBDDnif(nif) == false) {
						JOptionPane.showMessageDialog(null, "El nif introducido no pertenece a ningun local");
					}
					if (controladorPanelRegistro.comprobarBBDDdni(dni)) {
						JOptionPane.showMessageDialog(null, "El dni introducido ya existe en la BBDD");
					}
					if (controladorPanelRegistro.comprobarContraNoVacia(password) == false) {
						JOptionPane.showMessageDialog(null, "la contraseña tiene que tener un minimo de 5 caracteres");
					}
				}

			}

			{
				JOptionPane.showMessageDialog(null, "Registro iniciado");
			}
		};
	}
}