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
import Controlador.ControladorPanelPedidos;
import Controlador.ControladorPanelTickets;
import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JFormattedTextField;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.Color;

public class PanelPedidos extends JPanel {

	private static final long serialVersionUID = -6410388862910126406L;
	private ControladorPanelPedidos controladorPanelPedidos;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JTextField textFieldNumTrans;
	private JTextField textFieldFecha;
	private JTextField textFieldLocal;
	private JTextField textField;
	private JList productosAlmacenados = new JList();
	private JList listaAnnadidos;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listaPAnnadidos = new DefaultListModel<String>();
	private JFormattedTextField TextFieldCantidad;
	private JButton btnSeleccionar;
	private JLabel lblError;
	private JButton btnEliminar;
	private JTextField textTotal;
	

	public PanelPedidos(ControladorPanelPedidos controladorPanelPedidos) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelPedidos = controladorPanelPedidos;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL PEDIDOS");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(195, 0, 187, 46);
		add(lblTextoPanel);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(494, 416, 89, 23);
		add(btnVolver);

		JLabel lblNumTrans = new JLabel("Numero de transacci\u00F3n: \r\n");
		lblNumTrans.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNumTrans.setBounds(17, 75, 187, 23);
		add(lblNumTrans);

		textFieldNumTrans = new JTextField();
		textFieldNumTrans.setBounds(207, 73, 125, 30);
		add(textFieldNumTrans);
		textFieldNumTrans.setColumns(10);
		// textFieldNumTrans.setText("1");

		JLabel lblFecha = new JLabel("Fecha y hora: ");
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 17));
		lblFecha.setBounds(342, 75, 113, 23);
		add(lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(458, 73, 106, 30);
		add(textFieldFecha);
		textFieldFecha.setText(this.controladorPanelPedidos.devolverFechaHora());
		textFieldFecha.setEditable(false);

		JLabel lblLocal = new JLabel("Local:\r\n");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 17));
		lblLocal.setBounds(17, 117, 113, 23);
		add(lblLocal);

		textFieldLocal = new JTextField();
		textFieldLocal.setColumns(10);
		textFieldLocal.setBounds(207, 115, 125, 30);
		add(textFieldLocal);

		JLabel lblTipoPed = new JLabel("* Solo rellenar si el pedido es a domicilio:");
		lblTipoPed.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTipoPed.setBounds(17, 165, 385, 23);
		add(lblTipoPed);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(17, 200, 487, 30);
		add(textField);

		productosAlmacenados = new JList(controladorPanelPedidos.pasarListaProductos());
		productosAlmacenados.setBackground(Color.WHITE);
		productosAlmacenados.setBounds(17, 271, 144, 135);
		add(productosAlmacenados);

		JLabel lblCantidad = new JLabel("Cantidad: \r\n");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCantidad.setBounds(17, 417, 79, 17);
		add(lblCantidad);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0); //valor m�nimo
	    formatter.setMaximum(Integer.MAX_VALUE); //valor m�ximo
	    formatter.setAllowsInvalid(false);
	    // Si quieres comprobar que sea v�lido, cada vez que se pulse una tecla
	    formatter.setCommitsOnValidEdit(true);
		
		TextFieldCantidad = new JFormattedTextField(formatter);
		TextFieldCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		TextFieldCantidad.setBounds(89, 414, 40, 27);
		add(TextFieldCantidad);
		TextFieldCantidad.setText("1");

		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(48, 455, 113, 33);
		add(btnSeleccionar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 271, 194, 110);
		add(scrollPane);
		
		listaAnnadidos = new JList(listaPAnnadidos);
		scrollPane.setViewportView(listaAnnadidos);
		
		JLabel lblProd = new JLabel("Productos disponibles\r\n");
		lblProd.setFont(new Font("Arial", Font.PLAIN, 17));
		lblProd.setBounds(17, 240, 187, 23);
		add(lblProd);
		
		JLabel lblProdAdd = new JLabel("Productos a\u00F1adidos\r\n");
		lblProdAdd.setFont(new Font("Arial", Font.PLAIN, 17));
		lblProdAdd.setBounds(268, 240, 187, 23);
		add(lblProdAdd);
		
		lblError = new JLabel("");
		lblError.setBounds(12, 473, 330, 15);
		add(lblError);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(467, 267, 117, 25);
		add(btnEliminar);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(271, 393, 70, 15);
		add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(327, 393, 114, 19);
		add(textTotal);
		textTotal.setColumns(10);
		textTotal.setText("0");
		
		

		initializeEvents();

	}
	

	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelPedidos));
		this.btnSeleccionar.addActionListener(listenerBotonSeleccionar(this.controladorPanelPedidos));
		this.btnEliminar.addActionListener(listenerBotonEliminar(this.controladorPanelPedidos));
	}
	
	private ActionListener listenerBotonSeleccionar(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Annadir");
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				String cantidad = TextFieldCantidad.getText();
				try {
					producto = (String) productosAlmacenados.getSelectedValue();
					productoAnadir = controladorPanelPedidos.accionadoBotonAnnadirProducto(producto);
					existeProd = true;
				}
				catch(Exception e) {
					System.out.println("No se ha seleccionado un producto");
					lblError.setText("No se ha escogido un producto");
				}
				if (existeProd) {
					try {
						listaPAnnadidos.addElement(controladorPanelPedidos.cantidadProducto(cantidad, productoAnadir));
						textTotal.setText(controladorPanelPedidos.cantidadTotal(cantidad, textTotal.getText(), producto));
					}
					catch(Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
						lblError.setText("No se ha introducido una cantidad");
					}
				}
				
			}
		};
	}

	private ActionListener listenerBotonVolver(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelPedidos.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonEliminar(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					int pos = listaAnnadidos.getSelectedIndex();
					String total = controladorPanelPedidos.accionadoBotonEliminar(pos, listaPAnnadidos.get(pos), textTotal.getText());
					listaPAnnadidos.remove(pos);
					textTotal.setText(total);
				}
				catch(Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionó ningún producto");
					lblError.setText("No se pudo eliminar");
				}
			}
		};
	}
}
