package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelAprovisionamiento;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class PanelAprovisionamiento extends JPanel {

	private static final long serialVersionUID = -8048832364382290273L;
	private ControladorPanelAprovisionamiento controladorPanelAprovisionamiento;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JList listaProductos;
	private JFormattedTextField textFieldCantidad;
	private JButton btnAnnadir;

	public PanelAprovisionamiento(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {

		setBackground(SystemColor.activeCaption);

		this.controladorPanelAprovisionamiento = controladorPanelAprovisionamiento;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL APROVISIONAMIENTO");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(37, 471, 89, 23);
		add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 79, 295, 342);
		add(scrollPane);
		
		listaProductos = new JList(this.controladorPanelAprovisionamiento.pasarListaProductos());
		
		scrollPane.setViewportView(listaProductos);
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1); // valor m�nimo
		formatter.setMaximum(99); // valor m�ximo
		formatter.setAllowsInvalid(false);
		// Si quieres comprobar que sea v�lido, cada vez que se pulse una tecla
		formatter.setCommitsOnValidEdit(true);
		
		textFieldCantidad = new JFormattedTextField(formatter);
		textFieldCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		textFieldCantidad.setBounds(58, 329, 114, 19);
		add(textFieldCantidad);
		textFieldCantidad.setText("1");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(56, 302, 70, 15);
		add(lblCantidad);
		
		btnAnnadir = new JButton("Annadir");
		btnAnnadir.setBounds(58, 376, 117, 25);
		add(btnAnnadir);

		initializeEvents();

	}

	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelAprovisionamiento));
		this.btnAnnadir.addActionListener(listenerBotonAnnadir(this.controladorPanelAprovisionamiento));
	}

	private ActionListener listenerBotonVolver(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelAprovisionamiento.accionadoBottonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonAnnadir(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Annadir");
				int cantidad = Integer.parseInt(textFieldCantidad.getText());
				int seleccionado = listaProductos.getSelectedIndex();
				controladorPanelAprovisionamiento.accionadoBotonAnnadir(cantidad, seleccionado);
			}
		};
	}
}
