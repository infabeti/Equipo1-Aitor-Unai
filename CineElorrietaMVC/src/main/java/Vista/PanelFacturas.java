package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.Controlador;
import Controlador.ControladorPanelFacturas;
import Controlador.ControladorPanelPedidos;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;

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
	private JLabel lblError;
	private JTextField textLocal;
	private JTextField textFecha;
	private JLabel lblLocal;
	private JLabel lblFecha;
	private JButton btnEliminar;
	private JLabel lblTotal;
	private JTextField textTotal;

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
		listaProductos.setBackground(Color.WHITE);
		scrollPaneProductos.setViewportView(listaProductos);
		
		JScrollPane scrollPaneAnnadidos = new JScrollPane();
		scrollPaneAnnadidos.setBounds(30, 244, 296, 153);
		add(scrollPaneAnnadidos);
		
		listaAnnadidos = new JList(annadidos);
		listaAnnadidos.setBackground(Color.WHITE);
		scrollPaneAnnadidos.setViewportView(listaAnnadidos);
		
		btnAnnadir = new JButton("Seleccionar");
		btnAnnadir.setBounds(390, 319, 128, 30);
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
		
		lblError = new JLabel("");
		lblError.setBounds(344, 287, 277, 15);
		add(lblError);
		
		textLocal = new JTextField();
		textLocal.setBounds(706, 94, 114, 19);
		add(textLocal);
		textLocal.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(706, 156, 114, 19);
		add(textFecha);
		textFecha.setColumns(10);
		
		lblLocal = new JLabel("Local");
		lblLocal.setBounds(592, 96, 70, 15);
		add(lblLocal);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(592, 158, 70, 15);
		add(lblFecha);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(25, 409, 117, 25);
		add(btnEliminar);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(160, 414, 70, 15);
		add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(212, 412, 114, 19);
		add(textTotal);
		textTotal.setColumns(10);
		textTotal.setText("0");
		
		

		initializeEvents();

	}
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelFacturas));
		this.btnAnnadir.addActionListener(listenerBotonAnnadir(this.controladorPanelFacturas));
		this.btnEliminar.addActionListener(listenerBotonEliminar(this.controladorPanelFacturas));
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
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				try {
					producto = (String) listaProductos.getSelectedValue();
					System.out.println("Producto " + producto);
					productoAnadir = controladorPanelFacturas.accionadoBotonAnnadirProducto(producto);
					existeProd = true;
				}
				catch(Exception e) {
					System.out.println("No se ha seleccionado un producto");
					lblError.setText("No se ha escogido un producto");
				}
				String texto = textCantidad.getText();
				if (existeProd) {
					try {
						int cantidad = Integer.parseInt(texto);
						annadidos.addElement(cantidad +  " " +productoAnadir);
						double total = Double.parseDouble(textTotal.getText());
						double precioTotalProducto = cantidad * Controlador.devolverPrecioProducto(producto);
						textTotal.setText(String.valueOf(total + precioTotalProducto));
					}
					catch(Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
						lblError.setText("No se ha introducido una cantidad");
					}
				}
			}
		};
	}
	
	private ActionListener listenerBotonEliminar(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					int pos = listaAnnadidos.getSelectedIndex();
					String eliminar = annadidos.get(pos);
					int punt = 0;
					for(int i = 0; eliminar.charAt(i)!= ' ';i++) {
						punt = i;
					}
					punt++;
					System.out.println("Cantidad");
					int cantidad = Integer.parseInt(eliminar.substring(0, punt));
					double precio = Controlador.devolverPrecioProducto(pos);
					double total = Double.parseDouble(textTotal.getText());
					textTotal.setText(String.valueOf(total - (precio * cantidad)));
					controladorPanelFacturas.accionadoBotonEliminar(pos);
					annadidos.remove(pos);
				}
				catch(Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionó ningún producto");
					lblError.setText("No se pudo eliminar");
				}
			}
		};
	}
}
