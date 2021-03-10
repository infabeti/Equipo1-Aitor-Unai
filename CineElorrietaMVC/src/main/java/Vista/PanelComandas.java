package Vista;

import javax.swing.JPanel;


import Controlador.ControladorPanelComandas;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.text.NumberFormatter;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class PanelComandas extends JPanel {

	private static final long serialVersionUID = -2457862673139031544L;
	private ControladorPanelComandas controladorPanelComandas;
	private JButton btnVolver;
	private JButton btnAnadirProducto;
	private JButton btnAnadirPlato;
	private JButton btnFinalizar;
	private JButton btnEliminarProducto;
	private JButton btnEliminarPlato;
	private JList listaProductos;
	private JList listaPlatos;
	private JList productosAnadidos;
	private JList platosAnadidos;
	private DefaultListModel<String> productosAnadidosString = new DefaultListModel<String>();
	private DefaultListModel<String> platosAnadidosString = new DefaultListModel<String>();
	private JFormattedTextField textCantidadProductos;
	private JFormattedTextField textCantidadPlatos;
	private JTextField textTotal;

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
			scrollPane.setBounds(363, 103, 216, 298);
			add(scrollPane);
			
			listaProductos = new JList(controladorPanelComandas.cogerListaProductos());
			scrollPane.setViewportView(listaProductos);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(605, 103, 216, 298);
			add(scrollPane_1);
			
			listaPlatos = new JList(controladorPanelComandas.cogerListaPlatos());
			scrollPane_1.setViewportView(listaPlatos);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(60, 103, 263, 108);
			add(scrollPane_2);
			
			productosAnadidos = new JList(productosAnadidosString);
			scrollPane_2.setViewportView(productosAnadidos);
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(60, 293, 263, 108);
			add(scrollPane_3);
			
			platosAnadidos = new JList(platosAnadidosString);
			scrollPane_3.setViewportView(platosAnadidos);
			
			btnAnadirProducto = new JButton("Anadir producto");
			btnAnadirProducto.setBounds(396, 413, 163, 25);
			add(btnAnadirProducto);
			
			btnAnadirPlato = new JButton("Anadir plato");
			btnAnadirPlato.setBounds(638, 413, 156, 25);
			add(btnAnadirPlato);
			
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setBounds(185, 481, 117, 25);
			add(btnFinalizar);
			
			btnEliminarProducto = new JButton("Eliminar Producto");
			btnEliminarProducto.setBounds(109, 223, 168, 25);
			add(btnEliminarProducto);
			
			btnEliminarPlato = new JButton("Eliminar Plato");
			btnEliminarPlato.setBounds(109, 260, 168, 25);
			add(btnEliminarPlato);
			
			NumberFormat format = NumberFormat.getInstance();
			NumberFormatter formatter = new NumberFormatter(format);
			formatter.setValueClass(Integer.class);
			formatter.setMinimum(1); // valor m�nimo
			formatter.setMaximum(99); // valor m�ximo
			formatter.setAllowsInvalid(false);
			// Si quieres comprobar que sea v�lido, cada vez que se pulse una tecla
			formatter.setCommitsOnValidEdit(true);

			textCantidadProductos = new JFormattedTextField(formatter);
			textCantidadProductos.setFont(new Font("Arial", Font.PLAIN, 12));
			textCantidadProductos.setBounds(455, 450, 51, 20);
			add(textCantidadProductos);
			textCantidadProductos.setText("1");

			textCantidadPlatos = new JFormattedTextField(formatter);
			textCantidadPlatos.setFont(new Font("Arial", Font.PLAIN, 12));
			textCantidadPlatos.setBounds(702, 450, 51, 20);
			add(textCantidadPlatos);
			textCantidadPlatos.setText("1");
			
			textTotal = new JTextField();
			textTotal.setBounds(139, 413, 114, 19);
			add(textTotal);
			textTotal.setColumns(10);
			textTotal.setText("0");
			
			initializeEvents();
	}
	
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolverPanelPrincipal(controladorPanelComandas));
		this.btnAnadirProducto.addActionListener(listenerBotonAnadirProducto(controladorPanelComandas));
		this.btnAnadirPlato.addActionListener(listenerBotonAnadirPlato(controladorPanelComandas));
		this.btnFinalizar.addActionListener(listenerBotonFinalizar(controladorPanelComandas));
		this.btnEliminarProducto.addActionListener(listenerBotonEliminarProducto(controladorPanelComandas));
		this.btnEliminarPlato.addActionListener(listenerBotonEliminarPlato(controladorPanelComandas));
	}
	
	private ActionListener listenerBotonVolverPanelPrincipal(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonAnadirProducto(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Anadir Producto");
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				String cantidad = textCantidadProductos.getText();
				try {
					producto = (String) listaProductos.getSelectedValue();
					if(producto != null) {
						existeProd = true;
					}
					
				} catch (Exception e) {
					System.out.println("No se ha seleccionado un producto");
				}
				if (existeProd) {
					try {
						if (controladorPanelComandas.existeProducto(producto) == -1) {
							productoAnadir = controladorPanelComandas.accionadoBotonAnnadirProducto(producto);
							productosAnadidosString.addElement(controladorPanelComandas.cantidadProducto(cantidad, productoAnadir));
							textTotal.setText(controladorPanelComandas.cantidadTotal(cantidad, textTotal.getText(), producto));
						} else {
							String yaAnnadido = productosAnadidosString.get(controladorPanelComandas.existeProducto(producto));
							productosAnadidosString.set(controladorPanelComandas.existeProducto(producto), controladorPanelComandas.cambiarCantidadProductos(yaAnnadido, Integer.parseInt(cantidad)));
							String total = Double.toString(Double.parseDouble(textTotal.getText()) + (Double.parseDouble(cantidad)* controladorPanelComandas.cogerPrecioString(producto)));
							textTotal.setText(total);
						}
					} catch (Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
					}
				}
			}
		};
	}
	
	private ActionListener listenerBotonAnadirPlato(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonFinalizar(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonEliminarProducto(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
	
	private ActionListener listenerBotonEliminarPlato(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
			}
		};
	}
}
