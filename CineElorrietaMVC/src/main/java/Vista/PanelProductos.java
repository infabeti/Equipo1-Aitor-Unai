package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;


import Controlador.ControladorPanelProductos;
import Modelo.Producto;
import Modelo.Modelo;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelProductos extends JPanel {

	private ControladorPanelProductos controladorPanelProductos;
	
	private JLabel lblTextoPanel;
	private JList<?> productosAlmacenados = new JList<Object>(  );
	private JFormattedTextField TextFieldCantidad;
	private JButton btnVolver;
		

	public PanelProductos(ControladorPanelProductos controladorPanelProductos) {
		setBackground(SystemColor.activeCaption);

		
			this.controladorPanelProductos = controladorPanelProductos;
			
			setLayout(null);
			
			lblTextoPanel = new JLabel("PANEL PRODUCTOS\r\n");
			lblTextoPanel.setFont(new Font("Arial", Font.BOLD, 20));
			lblTextoPanel.setBounds(195, 0, 228, 46);
			add(lblTextoPanel);
			productosAlmacenados.setBackground(SystemColor.activeCaption);
			productosAlmacenados.setModel(new AbstractListModel() {
				String[] values = controladorPanelProductos.pasarListaProductos();
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			
			productosAlmacenados.setBounds(132, 73, 249, 135);
			add(productosAlmacenados);
			
			JButton btnSeleccionar = new JButton("Seleccionar");
			btnSeleccionar.setBounds(132, 300, 113, 33);
			add(btnSeleccionar);
			
			JLabel lblCantidad = new JLabel("Cantidad: \r\n");
			lblCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
			lblCantidad.setBounds(142, 220, 79, 17);
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
			TextFieldCantidad.setBounds(220, 217, 93, 27);
			add(TextFieldCantidad);
			
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnVolver.setBounds(388, 325, 89, 23);
			add(btnVolver);
			
			
			
			
			
			//initializeEvents();
		
		
	}
	
	
	
	private void initializeEvents() {
		
	}
}
