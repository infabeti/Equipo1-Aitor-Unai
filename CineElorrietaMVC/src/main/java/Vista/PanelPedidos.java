package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.Controlador;
import Controlador.ControladorPanelPedidos;
import Controlador.ControladorPanelProductos;
import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JFormattedTextField;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.NumberFormatter;

public class PanelPedidos extends JPanel {

	private ControladorPanelPedidos controladorPanelPedidos;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JTextField textFieldNumTrans;
	private JTextField textFieldFecha;
	private JTextField textFieldLocal;
	private JTextField textField;
	private JList productosAlmacenados = new JList();
	private JList productosSeleccionados = new JList();
	private JFormattedTextField TextFieldCantidad;
	

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
		textFieldFecha.setText(Controlador.getFechaHoraSys());
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
		textField.setBounds(17, 199, 487, 30);
		add(textField);

		productosAlmacenados = new JList(controladorPanelPedidos.pasarListaProductos());
		productosAlmacenados.setBackground(SystemColor.activeCaption);
		productosAlmacenados.setBounds(17, 260, 144, 135);
		add(productosAlmacenados);

		JLabel lblCantidad = new JLabel("Cantidad: \r\n");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCantidad.setBounds(17, 417, 79, 17);
		add(lblCantidad);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0); //valor mínimo
	    formatter.setMaximum(Integer.MAX_VALUE); //valor máximo
	    formatter.setAllowsInvalid(false);
	    // Si quieres comprobar que sea válido, cada vez que se pulse una tecla
	    formatter.setCommitsOnValidEdit(true);
		
		TextFieldCantidad = new JFormattedTextField(formatter);
		TextFieldCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		TextFieldCantidad.setBounds(89, 414, 40, 27);
		add(TextFieldCantidad);
		TextFieldCantidad.setText("1");

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(productosAlmacenados.getSelectedValue());
				System.out.println(TextFieldCantidad.getText());
				
				System.out.println(controladorPanelPedidos.devolverProducto((String) productosAlmacenados.getSelectedValue()));
				
				LineaPedido l1 = new LineaPedido(
						controladorPanelPedidos.devolverProducto((String) productosAlmacenados.getSelectedValue()), 
						Integer.parseInt(TextFieldCantidad.getText()),
						controladorPanelPedidos.devolverProducto((String) productosAlmacenados.getSelectedValue()).getPrecioVenta() * Integer.parseInt(TextFieldCantidad.getText())
						);
				
				System.out.println(l1.toString());

			}
		});
		btnSeleccionar.setBounds(139, 411, 113, 33);
		add(btnSeleccionar);

		initializeEvents();

	}
	

	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelPedidos));
	}

	private ActionListener listenerBotonVolver(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelPedidos.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
}
