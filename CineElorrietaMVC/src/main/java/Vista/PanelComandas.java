package Vista;

import javax.swing.JPanel;


import Controlador.ControladorPanelComandas;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelComandas extends JPanel {


	private static final long serialVersionUID = -2457862673139031544L;
	private ControladorPanelComandas controladorPanelComandas;
	private JButton btnVolver;
	


	public PanelComandas(ControladorPanelComandas controladorPanelComandas) {
		setBackground(SystemColor.activeCaption);

		
			this.controladorPanelComandas = controladorPanelComandas;
			
			setLayout(null);
			
			btnVolver = new JButton("Volver");
			btnVolver.setBounds(12, 481, 117, 25);
			add(btnVolver);
			
			JLabel lblComandas = new JLabel("COMANDAS");
			lblComandas.setFont(new Font("Dialog", Font.BOLD, 48));
			lblComandas.setBounds(12, 12, 469, 63);
			add(lblComandas);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(292, 103, 216, 298);
			add(scrollPane);
			
			JList listaProductos = new JList(controladorPanelComandas.cogerListaProductos());
			scrollPane.setViewportView(listaProductos);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(605, 103, 216, 298);
			add(scrollPane_1);
			
			JList listaPlatos = new JList(controladorPanelComandas.cogerListaPlatos());
			scrollPane_1.setViewportView(listaPlatos);
			
			initializeEvents();
	}
	
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolverPanelPrincipal(controladorPanelComandas));
	}
	
	private ActionListener listenerBotonVolverPanelPrincipal(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
}
