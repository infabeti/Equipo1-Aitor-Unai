package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Modelo.Conexion;
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

import com.sun.jdi.connect.spi.Connection;

import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;

public class PanelLogin extends JPanel {

	private JLabel lblTextoPanel;
	private JTextField textFieldNomUsuario;
	private JPasswordField textFieldpassword;
	private ControladorLogin controlador;
	private JButton btnAceptar;
	private JButton btnRegistro;
	
	public PanelLogin(ControladorLogin controladorLogin) {
		setBackground(SystemColor.activeCaption);

		this.controlador = controladorLogin;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL LOGIN");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 50));
		lblTextoPanel.setBounds(28, 23, 412, 46);
		add(lblTextoPanel);

		JLabel lbldni= new JLabel("DNI: ");
		lbldni.setFont(new Font("Arial", Font.PLAIN, 28));
		lbldni.setBounds(31, 129, 141, 23);
		add(lbldni);

		textFieldNomUsuario = new JTextField();
		textFieldNomUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldNomUsuario.setBounds(206, 120, 234, 46);
		add(textFieldNomUsuario);
		textFieldNomUsuario.setColumns(10);

		JLabel lblLocal = new JLabel("Contrase\u00F1a:");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 28));
		lblLocal.setBounds(28, 214, 169, 23);
		add(lblLocal);


		textFieldpassword = new JPasswordField();
		textFieldpassword.setFont(new Font("Arial", Font.PLAIN, 20));
		textFieldpassword.setColumns(10);
		textFieldpassword.setBounds(206, 205, 234, 46);
		add(textFieldpassword);

		

	
		btnAceptar = new JButton("Login");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAceptar.setBounds(57, 323, 115, 41);
		add(btnAceptar);
		
		btnRegistro= new JButton("Registro");
		btnRegistro.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRegistro.setBounds(288, 323, 141, 41);
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
			System.out.println("Ejecutando evento Boton LOGIN");
			
			 String userName = textFieldNomUsuario.getText();
			 @SuppressWarnings("deprecation")
			String password = textFieldpassword.getText();
			
		if (controlador.login(userName, password)) {
			
			JOptionPane.showMessageDialog(null, "Logueado correctamente");
            controlador.accionadoBottonAceptarPanelPrincipal();
		}
		else
		{
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");

		}
			 
       
			
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

/*public void actionPerformed(ActionEvent e) {
    String userName = textField.getText();
    String password = passwordField.getText();
    try {
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo",
            "root", "root");

        PreparedStatement st = (PreparedStatement) connection
            .prepareStatement("Select name, password from student where name=? and password=?");

        st.setString(1, userName);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            dispose();
            UserHome ah = new UserHome(userName);
            ah.setTitle("Welcome");
            ah.setVisible(true);
            JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
        } else {
            JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
        }
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }
}
});*/



