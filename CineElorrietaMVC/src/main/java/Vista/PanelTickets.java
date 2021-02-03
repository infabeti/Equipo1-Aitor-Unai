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
	private JScrollPane scrollPane_1;
	private JTextField textField_1;
	private JLabel lblCantidad;
	private JLabel lblError;
	private JTextField textLocal;
	private JTextField textFecha;
	private JButton btnEliminar;
	private JLabel lblTotal;
	private JTextField textTotal;
	
	
	public PanelTickets(ControladorPanelTickets controladorPanelTickets) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelTickets = controladorPanelTickets;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL TICKETS");
		lblTextoPanel.setFont(new Font("Arial", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(508, 451, 89, 23);
		add(btnVolver);
		
		textField = new JTextField();
		textField.setBounds(120, 76, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTransaccion.setBounds(30, 78, 102, 15);
		add(lblTransaccion);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(30, 410, 117, 25);
		add(btnFinalizar);
		
		btnAnadir = new JButton("Seleccionar\r\n");
		btnAnadir.setBounds(401, 261, 117, 25);
		add(btnAnadir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 244, 296, 153);
		add(scrollPane);
		
		listaAnnadidos = new JList(listaPAnnadidos);
		listaAnnadidos.setBackground(SystemColor.activeCaption);
		scrollPane.setViewportView(listaAnnadidos);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(380, 90, 150, 160);
		add(scrollPane_1);
		
		listaProductos = new JList(controladorPanelTickets.cogerListaProductos());
		listaProductos.setBackground(SystemColor.activeCaption);
		scrollPane_1.setViewportView(listaProductos);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantidad.setBounds(30, 124, 92, 22);
		add(lblCantidad);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 126, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblProdDisp = new JLabel("Productos");
		lblProdDisp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProdDisp.setBounds(401, 57, 92, 22);
		add(lblProdDisp);
		
		JLabel lblProductosSeleccionados = new JLabel("Productos Seleccionados");
		lblProductosSeleccionados.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProductosSeleccionados.setBounds(71, 209, 244, 22);
		add(lblProductosSeleccionados);
		
		lblError = new JLabel("");
		lblError.setBounds(30, 166, 332, 31);
		add(lblError);
		
		textLocal = new JTextField();
		textLocal.setBounds(642, 76, 114, 19);
		add(textLocal);
		textLocal.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(642, 126, 114, 19);
		add(textFecha);
		textFecha.setColumns(10);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(554, 78, 70, 15);
		add(lblLocal);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(554, 128, 70, 15);
		add(lblFecha);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(198, 410, 117, 25);
		add(btnEliminar);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(344, 309, 70, 15);
		add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(338, 352, 114, 19);
		add(textTotal);
		textTotal.setColumns(10);
		textTotal.setText("0");
		
		initializeEvents();

	}
	
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelTickets));
		this.btnAnadir.addActionListener(listenerBotonAnadir(this.controladorPanelTickets));
		this.btnEliminar.addActionListener(listenerBotonEliminar(this.controladorPanelTickets));
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
				/*Este es el mínimo de lógica que puede haber (va para todos las vistas)
				 * En el primer try catch necesita hacer un cast a String ya que no se puede pasar el valor directo
				 * de getSelectedValue()
				 * existeProd necesita estar para asegurarse de que se añade primero el producto antes de seguir 
				 * Todos estos comentarios valen para las tres vistas*/
				System.out.println("Ejecutando evento Boton Annadir");
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				try {
					producto = (String) listaProductos.getSelectedValue(); //Necesito hacer aquí el cast porque getSelectedValue() devuelve un objeto por lo que no se le puede pasar directamente a accionadoBotonAnadirProducto
					productoAnadir = controladorPanelTickets.accionadoBotonAnnadirProducto(producto);
					existeProd = true;
				}
				catch(Exception e) {
					System.out.println("No se ha seleccionado un producto");
					lblError.setText("No se ha escogido un producto");
				}
				String cantidad = textField_1.getText();
				listaPAnnadidos.addElement(controladorPanelTickets.cantidadProducto(cantidad, productoAnadir));
				if (existeProd) {
					try {
						textTotal.setText(controladorPanelTickets.cantidadTotal(cantidad, textTotal.getText(), productoAnadir));
					}
					catch(Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
						lblError.setText("No se ha introducido una cantidad");
					}
				}
			}
		};
	}
	
	private ActionListener listenerBotonEliminar(ControladorPanelTickets controladorPanelTickets) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					/*Este es el mínimo de lógica necesaria en la vista para eliminar un elemento
					 * Primero se coge el índice seleccionado, luego se le pasa al controlador junto al string que representa
					 * El producto a eliminar y el total actual
					 * Se elimina el producto de la lista y luego se cambia el total por el devuelto por el controlador */
					int pos = listaAnnadidos.getSelectedIndex();
					String total = controladorPanelTickets.accionadoBotonEliminar(pos, listaPAnnadidos.get(pos), textTotal.getText());
					listaPAnnadidos.remove(pos);
					textTotal.setText(total);
				}
				catch(Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionÃ³ ningÃºn producto");
					lblError.setText("No se pudo eliminar");
				}
			}
		};
	}
}
