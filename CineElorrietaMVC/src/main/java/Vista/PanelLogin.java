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

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.JLayeredPane;

public class PanelLogin extends JPanel {

	private JLabel lblTextoPanel;
	private JTextField textFieldNomUsuario;
	private JTextField textFieldpassword;
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

		textFieldpassword = new JTextField();
		textFieldpassword.setColumns(10);
		textFieldpassword.setBounds(139, 120, 220, 30);
		add(textFieldpassword);
		
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
			
			 String userName = textFieldNomUsuario.getText();
			 String password = textFieldpassword.getText();
			
		if (controlador.login(userName, password)) {
			
			JOptionPane.showMessageDialog(null, "Logueado correctamente");
            controlador.accionadoBottonAceptarPanelPrincipal();
		}
		else
		{
            JOptionPane.showMessageDialog(null, "Usuario o contraseņa incorrectos");

		}
			 
             


				

			 
			
		}
	};
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


}
		