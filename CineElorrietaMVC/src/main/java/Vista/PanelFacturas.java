package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelFacturas;
import Controlador.ControladorPanelPedidos;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelFacturas extends JPanel {

	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private ControladorPanelFacturas controladorPanelFacturas;
	private JTextField textNIF;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTransaccion;
	private JTextField textCantidad;
	private JList listaProductos;
	private JList listaAnnadidos;
	private DefaultListModel<String> annadidos = new DefaultListModel<String>();
	private JButton btnAnnadir;
	private JButton btnFinalizar;

	public PanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelFacturas = controladorPanelFacturas;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL FACTURAS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(425, 374, 89, 23);
		add(btnVolver);
		
		textNIF = new JTextField();
		textNIF.setBounds(102, 64, 113, 20);
		add(textNIF);
		textNIF.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(102, 95, 113, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(102, 126, 113, 20);
		add(textApellido);
		textApellido.setColumns(10);
		
		textTransaccion = new JTextField();
		textTransaccion.setBounds(102, 157, 113, 20);
		add(textTransaccion);
		textTransaccion.setColumns(10);
		
		JLabel lblNIF = new JLabel("NIF");
		lblNIF.setBounds(10, 67, 46, 14);
		add(lblNIF);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 98, 46, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 129, 46, 14);
		add(lblApellido);
		
		JLabel lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setBounds(10, 160, 57, 14);
		add(lblTransaccion);
		
		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(260, 64, 162, 171);
		add(scrollPaneProductos);
		
		listaProductos = new JList(controladorPanelFacturas.cogerListaProductos());
		scrollPaneProductos.setViewportView(listaProductos);
		
		JScrollPane scrollPaneAnnadidos = new JScrollPane();
		scrollPaneAnnadidos.setBounds(10, 185, 162, 67);
		add(scrollPaneAnnadidos);
		
		listaAnnadidos = new JList(annadidos);
		scrollPaneAnnadidos.setViewportView(listaAnnadidos);
		
		btnAnnadir = new JButton("Annadir");
		btnAnnadir.setBounds(333, 246, 89, 23);
		add(btnAnnadir);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(66, 263, 89, 23);
		add(btnFinalizar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(182, 188, 46, 14);
		add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(177, 213, 51, 20);
		add(textCantidad);
		textCantidad.setColumns(10);
		
		

		initializeEvents();

	}
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelFacturas));
		this.btnAnnadir.addActionListener(listenerBotonAnnadir(this.controladorPanelFacturas));
	}
	
	private ActionListener listenerBotonVolver(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelFacturas.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonAnnadir(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Annadir");
				String producto = (String) listaProductos.getSelectedValue();
				System.out.println("Producto " + producto);
				producto = controladorPanelFacturas.accionadoBotonAnnadirProducto(producto);
				int cantidad = 1;
				String texto = textCantidad.getText();
				try {
					cantidad = Integer.parseInt(texto);
				}
				catch(Exception e) {
					System.out.println("El campo cantidad no contiene un entero");
				}
				annadidos.addElement(cantidad +  " " +producto);
			}
		};
	}
}
