package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelPedidos;
import Controlador.ControladorPanelTickets;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PanelTickets extends JPanel {

	private ControladorPanelTickets controladorPanelTickets;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JTextField textField;
	private JButton btnFinalizar;
	private JLabel lblTransaccion;
	private JList listaProductos;
	private JButton btnAnadir;
	private JList listaAnnadidos;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listaPAnnadidos = new DefaultListModel<String>();
	
	
	public PanelTickets(ControladorPanelTickets controladorPanelTickets) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelTickets = controladorPanelTickets;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL TICKETS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(425, 374, 89, 23);
		add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(107, 91, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setBounds(0, 93, 102, 15);
		add(lblTransaccion);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(69, 249, 117, 25);
		add(btnFinalizar);
		
		listaProductos = new JList(controladorPanelTickets.cogerListaProductos());
		listaProductos.setBounds(270, 58, 153, 146);
		add(listaProductos);
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.setBounds(292, 226, 117, 25);
		add(btnAnadir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 119, 196, 112);
		add(scrollPane);
		
		listaAnnadidos = new JList(listaPAnnadidos);
		scrollPane.setViewportView(listaAnnadidos);
		
		initializeEvents();

	}
	
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelTickets));
		this.btnAnadir.addActionListener(listenerBotonAnadir(this.controladorPanelTickets));
	}
	
	private ActionListener listenerBotonVolver(ControladorPanelTickets controladorPanelTickets) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelTickets.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonAnadir(ControladorPanelTickets controladorPanelTickets) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Añadir");
				String producto = (String) listaProductos.getSelectedValue();
				System.out.println("Producto " + producto);
				producto = controladorPanelTickets.accionadoBotonAnnadirProducto(producto);
				listaPAnnadidos.addElement(producto);
			}
		};
	}
}
