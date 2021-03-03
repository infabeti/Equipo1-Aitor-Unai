package Vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controlador.ControladorPanelTickets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class PanelTickets extends JPanel {

	private static final long serialVersionUID = -4866340972661290326L;
	private ControladorPanelTickets controladorPanelTickets;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private JTextField textFieldNumTrans;
	private JButton btnFinalizar;
	private JLabel lblTransaccion;
	private JList listaProductos;
	private JButton btnAnadir;
	private JList listaAnnadidos;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listaPAnnadidos = new DefaultListModel<String>();
	private JScrollPane scrollPane_1;
	private JFormattedTextField TextFieldCantidad;
	private JLabel lblCantidad;
	private JLabel lblError;
	private JTextField textLocal;
	private JButton btnEliminar;
	private JLabel lblTotal;
	private JTextField textTotal;
	private JTextField textFieldFecha;

	public PanelTickets(ControladorPanelTickets controladorPanelTickets) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelTickets = controladorPanelTickets;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL TICKETS");
		lblTextoPanel.setFont(new Font("Arial", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(714, 546, 89, 23);
		add(btnVolver);

		textFieldNumTrans = new JTextField();
		textFieldNumTrans.setBounds(120, 76, 114, 19);
		add(textFieldNumTrans);
		textFieldNumTrans.setColumns(10);
		textFieldNumTrans.setText(controladorPanelTickets.leerNumTransBBDD());
		textFieldNumTrans.setEditable(false);
		textFieldNumTrans.setHorizontalAlignment(SwingConstants.CENTER);

		lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTransaccion.setBounds(30, 78, 102, 15);
		add(lblTransaccion);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(568, 545, 117, 25);
		add(btnFinalizar);

		btnAnadir = new JButton("Seleccionar\r\n");
		btnAnadir.setBounds(411, 459, 117, 25);
		add(btnAnadir);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 244, 296, 153);
		add(scrollPane);

		listaAnnadidos = new JList(listaPAnnadidos);
		listaAnnadidos.setBackground(Color.WHITE);
		scrollPane.setViewportView(listaAnnadidos);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(380, 240, 150, 160);
		add(scrollPane_1);

		listaProductos = new JList(controladorPanelTickets.cogerListaProductos());
		listaProductos.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(listaProductos);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantidad.setBounds(350, 411, 92, 22);
		add(lblCantidad);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1); // valor m�nimo
		formatter.setMaximum(99); // valor m�ximo
		formatter.setAllowsInvalid(false);
		// Si quieres comprobar que sea v�lido, cada vez que se pulse una tecla
		formatter.setCommitsOnValidEdit(true);

		TextFieldCantidad = new JFormattedTextField(formatter);
		TextFieldCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		TextFieldCantidad.setBounds(425, 411, 40, 27);
		add(TextFieldCantidad);
		TextFieldCantidad.setText("1");

		JLabel lblProdDisp = new JLabel("Productos");
		lblProdDisp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProdDisp.setBounds(411, 207, 92, 22);
		add(lblProdDisp);

		JLabel lblProductosSeleccionados = new JLabel("Productos Seleccionados");
		lblProductosSeleccionados.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProductosSeleccionados.setBounds(71, 209, 244, 22);
		add(lblProductosSeleccionados);

		lblError = new JLabel("");
		lblError.setBounds(30, 166, 332, 31);
		add(lblError);

		textLocal = new JTextField();
		textLocal.setBounds(120, 126, 114, 19);
		add(textLocal);
		textLocal.setColumns(10);
		textLocal.setText(controladorPanelTickets.conseguirLocal());
		textLocal.setEditable(false);
		textLocal.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(448, 71, 106, 30);
		add(textFieldFecha);
		textFieldFecha.setText(this.controladorPanelTickets.devolverFechaHora());
		textFieldFecha.setEditable(false);

		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(30, 128, 70, 15);
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblLocal);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(380, 78, 70, 15);
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblFecha);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(105, 408, 117, 25);
		add(btnEliminar);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(52, 464, 70, 15);
		add(lblTotal);

		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(92, 461, 114, 19);
		add(textTotal);
		textTotal.setColumns(10);
		textTotal.setText("0");

		initializeEvents();

	}

	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelTickets));
		this.btnAnadir.addActionListener(listenerBotonAnadir(this.controladorPanelTickets));
		this.btnEliminar.addActionListener(listenerBotonEliminar(this.controladorPanelTickets));
		this.btnFinalizar.addActionListener(listenerBotonFinalizar(this.controladorPanelTickets));

	}

	private ActionListener listenerBotonFinalizar(ControladorPanelTickets controladorPanelTickets) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Finalizar");

				if (Double.parseDouble(textTotal.getText()) > 0) {
					// insertar datos en actividad
					controladorPanelTickets.insertarTicket(Integer.parseInt(textFieldNumTrans.getText()),
							textFieldFecha.getText(),
							Double.parseDouble(textTotal.getText()), textLocal.getText());

					// insertar datos de productos
					for (int i = 0; i < listaPAnnadidos.getSize(); i++) {
						String textoRecogido = listaPAnnadidos.get(i);
						String textoSpliteado[] = textoRecogido.split(" ");

						int cantidad = Integer.parseInt(textoSpliteado[0]);

						int transaccion = Integer.parseInt(textFieldNumTrans.getText());

						String producto = controladorPanelTickets.devolverNombreProducto(i);
						double precioFinal = controladorPanelTickets.cogerPrecioString(producto);

						controladorPanelTickets.insertarProductoActividad(producto, transaccion, cantidad, precioFinal);
					}

					JOptionPane.showMessageDialog(null, "Ticket introducido correctamente");
					controladorPanelTickets.accionadoBottonVolverPanelPrincipal();

				} else {
					JOptionPane.showMessageDialog(null, "Debes introducir articulos");
				}

			}

		};
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
				/*
				 * Este es el m�nimo de l�gica que puede haber (va para todos las vistas) En
				 * el primer try catch necesita hacer un cast a String ya que no se puede pasar
				 * el valor directo de getSelectedValue() existeProd necesita estar para
				 * asegurarse de que se a�ade primero el producto antes de seguir Todos estos
				 * comentarios valen para las tres vistas
				 */
				System.out.println("Ejecutando evento Boton Annadir");
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				String cantidad = TextFieldCantidad.getText();
				System.out.println(cantidad);
				try {
					producto = (String) listaProductos.getSelectedValue(); // Necesito hacer aqu� el cast porque
																			// getSelectedValue() devuelve un objeto por
																			// lo que no se le puede pasar directamente
																			// a accionadoBotonAnadirProducto
					if(producto !=null) {
						existeProd = true;
					}
				} catch (Exception e) {
					System.out.println("No se ha seleccionado un producto");
					lblError.setText("No se ha escogido un producto");
				}
				if (existeProd) {
					try {
						if (controladorPanelTickets.existeProducto(producto) == -1) {
							productoAnadir = controladorPanelTickets.accionadoBotonAnnadirProducto(producto);
							listaPAnnadidos.addElement(controladorPanelTickets.cantidadProducto(cantidad, productoAnadir));
							textTotal.setText(
									controladorPanelTickets.cantidadTotal(cantidad, producto));
							lblError.setText("");
						} else {
							String yaAnnadido = listaPAnnadidos.get(controladorPanelTickets.existeProducto(producto));
							listaPAnnadidos.set(controladorPanelTickets.existeProducto(producto), controladorPanelTickets
									.cambiarCantidadProductos(yaAnnadido, Integer.parseInt(cantidad)));
							String total = controladorPanelTickets.cantidadTotal(cantidad, producto);
							textTotal.setText(total);
						}
					} catch (Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
						e.printStackTrace();
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
					/*
					 * Este es el m�nimo de l�gica necesaria en la vista para eliminar un
					 * elemento Primero se coge el �ndice seleccionado, luego se le pasa al
					 * controlador junto al string que representa El producto a eliminar y el total
					 * actual Se elimina el producto de la lista y luego se cambia el total por el
					 * devuelto por el controlador
					 */
					int pos = listaAnnadidos.getSelectedIndex();
					String total = controladorPanelTickets.accionadoBotonEliminar(pos, listaPAnnadidos.get(pos));
					listaPAnnadidos.remove(pos);
					textTotal.setText(total);
					lblError.setText("");
				} catch (Exception e) {
					System.out.println("No se pudo borrar el producto seleccionado/No se seleccionó ningún producto");
					lblError.setText("No se pudo eliminar");
				}
			}
		};
	}
}
