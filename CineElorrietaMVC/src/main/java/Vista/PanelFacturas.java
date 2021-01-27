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
	private JLabel lblProdDisp;
	private JLabel lblProductosSeleccionados;

	public PanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelFacturas = controladorPanelFacturas;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL FACTURAS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(497, 399, 89, 23);
		add(btnVolver);
		
		textNIF = new JTextField();
		textNIF.setBounds(131, 64, 113, 20);
		add(textNIF);
		textNIF.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(131, 94, 113, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(131, 125, 113, 20);
		add(textApellido);
		textApellido.setColumns(10);
		
		textTransaccion = new JTextField();
		textTransaccion.setBounds(131, 156, 113, 20);
		add(textTransaccion);
		textTransaccion.setColumns(10);
		
		JLabel lblNIF = new JLabel("NIF");
		lblNIF.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNIF.setBounds(25, 63, 67, 20);
		add(lblNIF);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombre.setBounds(25, 95, 82, 17);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		lblApellido.setBounds(25, 126, 82, 17);
		add(lblApellido);
		
		JLabel lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTransaccion.setBounds(25, 157, 98, 17);
		add(lblTransaccion);
		
		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(380, 90, 150, 160);
		add(scrollPaneProductos);
		
		listaProductos = new JList(controladorPanelFacturas.cogerListaProductos());
		listaProductos.setBackground(SystemColor.activeCaption);
		scrollPaneProductos.setViewportView(listaProductos);
		
		JScrollPane scrollPaneAnnadidos = new JScrollPane();
		scrollPaneAnnadidos.setBounds(30, 244, 296, 153);
		add(scrollPaneAnnadidos);
		
		listaAnnadidos = new JList(annadidos);
		listaAnnadidos.setBackground(SystemColor.activeCaption);
		scrollPaneAnnadidos.setViewportView(listaAnnadidos);
		
		btnAnnadir = new JButton("Seleccionar");
		btnAnnadir.setBounds(400, 288, 106, 30);
		add(btnAnnadir);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(66, 263, 89, 23);
		add(btnFinalizar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantidad.setBounds(390, 261, 82, 14);
		add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(457, 257, 51, 20);
		add(textCantidad);
		textCantidad.setColumns(10);
		
		lblProdDisp = new JLabel("Productos");
		lblProdDisp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProdDisp.setBounds(414, 50, 92, 22);
		add(lblProdDisp);
		
		lblProductosSeleccionados = new JLabel("Productos Seleccionados");
		lblProductosSeleccionados.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProductosSeleccionados.setBounds(68, 211, 244, 22);
		add(lblProductosSeleccionados);
		
		

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
