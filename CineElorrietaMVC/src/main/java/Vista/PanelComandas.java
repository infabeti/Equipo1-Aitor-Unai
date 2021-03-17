package Vista;

import javax.swing.JPanel;


import Controlador.ControladorPanelComandas;
import Controlador.ControladorPanelFacturas;

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
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JTextField textFieldNumTrans;
	private JTextField textFieldFecha;
	private JTextField textLocal;

	public PanelComandas(ControladorPanelComandas controladorPanelComandas) {
		setBackground(SystemColor.activeCaption);

		
			this.controladorPanelComandas = controladorPanelComandas;
			
			setLayout(null);
			
			btnVolver = new JButton("Volver");
			btnVolver.setBounds(67, 574, 117, 25);
			add(btnVolver);
			
			JLabel lblComandas = new JLabel("COMANDAS");
			lblComandas.setFont(new Font("Dialog", Font.BOLD, 48));
			lblComandas.setBounds(12, 12, 469, 63);
			add(lblComandas);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(367, 194, 216, 298);
			add(scrollPane);
			
			listaProductos = new JList(controladorPanelComandas.cogerListaProductos());
			scrollPane.setViewportView(listaProductos);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(610, 194, 216, 298);
			add(scrollPane_1);
			
			listaPlatos = new JList(controladorPanelComandas.cogerListaPlatos());
			scrollPane_1.setViewportView(listaPlatos);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(67, 182, 263, 108);
			add(scrollPane_2);
			
			productosAnadidos = new JList(productosAnadidosString);
			scrollPane_2.setViewportView(productosAnadidos);
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(67, 384, 263, 108);
			add(scrollPane_3);
			
			platosAnadidos = new JList(platosAnadidosString);
			scrollPane_3.setViewportView(platosAnadidos);
			
			btnAnadirProducto = new JButton("Anadir producto");
			btnAnadirProducto.setBounds(390, 510, 163, 25);
			add(btnAnadirProducto);
			
			btnAnadirPlato = new JButton("Anadir plato");
			btnAnadirPlato.setBounds(628, 510, 156, 25);
			add(btnAnadirPlato);
			
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setBounds(226, 574, 117, 25);
			add(btnFinalizar);
			
			btnEliminarProducto = new JButton("Eliminar Producto");
			btnEliminarProducto.setBounds(109, 301, 168, 25);
			add(btnEliminarProducto);
			
			btnEliminarPlato = new JButton("Eliminar Plato");
			btnEliminarPlato.setBounds(109, 337, 168, 25);
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
			textCantidadProductos.setBounds(447, 546, 51, 20);
			add(textCantidadProductos);
			textCantidadProductos.setText("1");

			textCantidadPlatos = new JFormattedTextField(formatter);
			textCantidadPlatos.setFont(new Font("Arial", Font.PLAIN, 12));
			textCantidadPlatos.setBounds(706, 546, 51, 20);
			add(textCantidadPlatos);
			textCantidadPlatos.setText("1");
			
			textTotal = new JTextField();
			textTotal.setBounds(143, 503, 114, 19);
			add(textTotal);
			textTotal.setColumns(10);
			textTotal.setText("0");
			textTotal.setEditable(false);

			textLocal = new JTextField();
			textLocal.setBounds(205, 128, 125, 25);
			add(textLocal);
			textLocal.setColumns(10);
			textLocal.setText(controladorPanelComandas.conseguirLocal());
			textLocal.setEditable(false);
			textLocal.setHorizontalAlignment(SwingConstants.CENTER);
			
			textFieldFecha = new JTextField();
			textFieldFecha.setColumns(10);
			textFieldFecha.setBounds(463, 86, 117, 25);
			add(textFieldFecha);
			textFieldFecha.setText(this.controladorPanelComandas.devolverFechaHora());
			textFieldFecha.setEditable(false);
			
			textFieldNumTrans = new JTextField();
			textFieldNumTrans.setBounds(205, 86, 125, 25);
			add(textFieldNumTrans);
			textFieldNumTrans.setColumns(10);
			textFieldNumTrans.setText(controladorPanelComandas.leerNumTransBBDD());
			textFieldNumTrans.setEditable(false);
			textFieldNumTrans.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel lblNumTrans = new JLabel("Numero de transacci\u00F3n: \r\n");
			lblNumTrans.setFont(new Font("Arial", Font.PLAIN, 17));
			lblNumTrans.setBounds(22, 86, 187, 23);
			add(lblNumTrans);
			
			JLabel lblLocal = new JLabel("Local:\r\n");
			lblLocal.setFont(new Font("Arial", Font.PLAIN, 17));
			lblLocal.setBounds(21, 125, 113, 23);
			add(lblLocal);
			
			JLabel lblFecha = new JLabel("Fecha y hora: ");
			lblFecha.setFont(new Font("Arial", Font.PLAIN, 17));
			lblFecha.setBounds(352, 88, 113, 23);
			add(lblFecha);
			
			JLabel lblProductos = new JLabel("Productos");
			lblProductos.setFont(new Font("Arial", Font.PLAIN, 17));
			lblProductos.setBounds(416, 160, 113, 23);
			add(lblProductos);
			
			JLabel lblPlatos = new JLabel("Platos");
			lblPlatos.setFont(new Font("Arial", Font.PLAIN, 17));
			lblPlatos.setBounds(676, 160, 113, 23);
			add(lblPlatos);
			
			initializeEvents();
	}
	
	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolverPanelPrincipal(controladorPanelComandas));
		this.btnAnadirProducto.addActionListener(listenerBotonAnadirProducto(controladorPanelComandas));
		this.btnAnadirPlato.addActionListener(listenerBotonAnadirPlato(controladorPanelComandas));
		this.btnFinalizar.addActionListener(listenerBotonFinalizar(controladorPanelComandas));
		this.btnEliminarProducto.addActionListener(listenerBotonEliminar(controladorPanelComandas));
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
				String[] productosAnadir = new String[2];
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
							productosAnadir = controladorPanelComandas.accionadoBotonAnnadirProducto(producto, cantidad);
							productosAnadidosString.addElement(productosAnadir[0]);
							textTotal.setText(productosAnadir[1]);
						} else {
							int indice = controladorPanelComandas.existeProducto(producto);
							String yaAnnadido = productosAnadidosString.get(indice);
							productosAnadir = controladorPanelComandas.cambiarCantidadProductos(yaAnnadido, Integer.parseInt(cantidad), producto);
							productosAnadidosString.set(indice, productosAnadir[0]);
							textTotal.setText(productosAnadir[1]);
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
				System.out.println("Ejecutando evento Boton Anadir Plato");
				boolean existePlato = false;
				String plato = "";
				String[] platosAnadir = new String[2];
				String cantidad = textCantidadPlatos.getText();
				try {
					plato = (String) listaPlatos.getSelectedValue();
					if(plato != null) {
						existePlato = true;
					}
					
				} catch (Exception e) {
					System.out.println("No se ha seleccionado un producto");
				}
				if (existePlato) {
					try {
						if (controladorPanelComandas.existePlato(plato) == -1) {
							platosAnadir = controladorPanelComandas.accionadoBotonAnnadirPlato(plato, cantidad);
							productosAnadidosString.addElement(platosAnadir[0]);
							textTotal.setText(platosAnadir[1]);
						} else {
							int indice = controladorPanelComandas.existePlato(plato);
							String yaAnnadido = productosAnadidosString.get(indice);
							platosAnadir = controladorPanelComandas.cambiarCantidadProductos(yaAnnadido, Integer.parseInt(cantidad), plato);
							productosAnadidosString.set(indice, platosAnadir[0]);
							textTotal.setText(platosAnadir[1]);
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("El campo cantidad no contiene un entero");
					}
				}
			}
		};
	}
	
	private ActionListener listenerBotonEliminar(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					int pos = productosAnadidos.getSelectedIndex();
					String total = controladorPanelComandas.accionadoBotonEliminar(pos, productosAnadidosString.get(pos));
					productosAnadidosString.remove(pos);
					textTotal.setText(total);
				} catch (Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionó ningún producto");
				}
			}
		};
	}
	
	private ActionListener listenerBotonEliminarPlato(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					int pos = platosAnadidos.getSelectedIndex();
					String total = controladorPanelComandas.accionadoBotonEliminarPlato(pos, platosAnadidosString.get(pos));
					platosAnadidosString.remove(pos);
					textTotal.setText(total);
				} catch (Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionó ningún producto");
				}
			}
		};
	}
	
	private ActionListener listenerBotonFinalizar(ControladorPanelComandas controladorPanelComandas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Pedidos");
				System.out.println("Ejecutando evento Boton Finalizar");
				try {
					
					if (Double.parseDouble(textTotal.getText()) > 0) {
						// insertar datos en actividad
						controladorPanelComandas.insertarComanda(Integer.parseInt(textFieldNumTrans.getText()),controladorPanelComandas.devolverFechaFormateada(textFieldFecha.getText()),Double.parseDouble(textTotal.getText()), textLocal.getText());
	
						// insertar datos de productos
						for (int i = 0; i < productosAnadidosString.getSize(); i++) {
							String textoRecogido = productosAnadidosString.get(i);
							String textoSpliteado[] = textoRecogido.split(" ");
	
							int cantidad = Integer.parseInt(textoSpliteado[0]);
	
							int transaccion = Integer.parseInt(textFieldNumTrans.getText());
	
							String producto = controladorPanelComandas.devolverNombreProducto(i);
							double precioFinal = controladorPanelComandas.cogerPrecioString(producto);
	
							controladorPanelComandas.insertarProductoActividad(producto, transaccion, cantidad, precioFinal);
						}
						
						for (int i = 0; i < platosAnadidosString.getSize(); i++) {
							String textoRecogido = platosAnadidosString.get(i);
							String textoSpliteado[] = textoRecogido.split(" ");
	
							int cantidad = Integer.parseInt(textoSpliteado[0]);
	
							int transaccion = Integer.parseInt(textFieldNumTrans.getText());
	
							String plato = controladorPanelComandas.devolverNombrePlato(i);
	
							controladorPanelComandas.insertarPlatoActividad(plato, transaccion, cantidad);
						}
	
						JOptionPane.showMessageDialog(null, "Comanda introducida correctamente");
						controladorPanelComandas.accionadoBotonVolverPanelPrincipal();
	
					} else {
						JOptionPane.showMessageDialog(null, "Debes introducir articulos");
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Error al introducir la comanda");
				}

			}
		};
	}
}
