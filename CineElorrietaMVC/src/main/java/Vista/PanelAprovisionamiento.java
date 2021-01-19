package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelAprovisionamiento;



public class PanelAprovisionamiento extends JPanel {

	private ControladorPanelAprovisionamiento controladorPanelAprovisionamiento;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	
	
	public PanelAprovisionamiento(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		
		setBackground(SystemColor.activeCaption);

		this.controladorPanelAprovisionamiento = controladorPanelAprovisionamiento;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL APROVISIONAMIENTO");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(425, 374, 89, 23);
		add(btnVolver);
		
		

		initializeEvents();

	}
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelAprovisionamiento));
	}
	
	private ActionListener listenerBotonVolver(ControladorPanelAprovisionamiento controladorPanelAprovisionamiento) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelAprovisionamiento.accionadoBottonVolverPanelPrincipal();
			}
		};
	}

}
