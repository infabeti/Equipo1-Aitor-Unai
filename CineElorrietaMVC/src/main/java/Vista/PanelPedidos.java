package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.Controlador;
import Controlador.ControladorPanelPedidos;
import Controlador.ControladorPanelProductos;

import java.awt.SystemColor;
import javax.swing.JTextField;

public class PanelPedidos extends JPanel {

	private ControladorPanelPedidos controladorPanelPedidos;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JButton btnProductos;
	private JTextField textFieldNumTrans;
	private JTextField textFieldFecha;
	private JTextField textFieldLocal;
	private JTextField textField;

	public PanelPedidos(ControladorPanelPedidos controladorPanelPedidos) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelPedidos = controladorPanelPedidos;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL PEDIDOS");
		lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
		lblTextoPanel.setBounds(195, 0, 187, 46);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(426, 358, 89, 23);
		add(btnVolver);
		
		JLabel lblNumTrans = new JLabel("Numero de transacci\u00F3n: \r\n");
		lblNumTrans.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNumTrans.setBounds(17, 75, 187, 23);
		add(lblNumTrans);
		
		textFieldNumTrans = new JTextField();
		textFieldNumTrans.setBounds(207, 73, 139, 30);
		add(textFieldNumTrans);
		textFieldNumTrans.setColumns(10);
		//textFieldNumTrans.setText("1");
		
		
		JLabel lblFecha = new JLabel("Fecha y hora: ");
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 17));
		lblFecha.setBounds(17, 129, 113, 23);
		add(lblFecha);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(207, 127, 139, 30);
		add(textFieldFecha);
		textFieldFecha.setText(Controlador.getFechaHoraSys());
		textFieldFecha.setEditable(false);
		
		JLabel lblLocal = new JLabel("Local:\r\n");
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 17));
		lblLocal.setBounds(17, 184, 113, 23);
		add(lblLocal);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setColumns(10);
		textFieldLocal.setBounds(207, 182, 139, 30);
		add(textFieldLocal);
		
		JLabel lblTipoPed = new JLabel("* Solo rellenar si el pedido es a domicilio:");
		lblTipoPed.setFont(new Font("Arial", Font.PLAIN, 17));
		lblTipoPed.setBounds(17, 247, 385, 23);
		add(lblTipoPed);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(27, 281, 487, 30);
		add(textField);
		
		btnProductos = new JButton("Productos\r\n");
		btnProductos.setFont(new Font("Arial", Font.PLAIN, 15));
		btnProductos.setBounds(402, 121, 113, 40);
		add(btnProductos);
		
		

		initializeEvents();

	}
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelPedidos));
		this.btnProductos.addActionListener(listenerBotonProductos(this.controladorPanelPedidos));
	}
	
	private ActionListener listenerBotonVolver(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelPedidos.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonProductos(ControladorPanelPedidos controladorPanelPedidos) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Productos");
				controladorPanelPedidos.accionadoBottonmostrarPanelProductos();
			}
		};
	}
}
