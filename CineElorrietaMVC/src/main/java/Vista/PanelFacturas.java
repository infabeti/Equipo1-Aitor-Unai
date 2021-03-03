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
import Controlador.ControladorPanelFacturas;
import Controlador.ControladorPanelTickets;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;

public class PanelFacturas extends JPanel {

	private static final long serialVersionUID = -8519282282238553342L;
	private JLabel lblTextoPanel;
	private JButton btnVolver;
	private ControladorPanelFacturas controladorPanelFacturas;
	private JTextField textNIF;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textFieldFecha;
	private JTextField textFieldNumTrans;
	private JTextField textCantidad;
	private JList listaProductos;
	private JList listaAnnadidos;
	private DefaultListModel<String> annadidos = new DefaultListModel<String>();
	private JButton btnAnnadir;
	private JButton btnFinalizar;
	private JLabel lblProdDisp;
	private JLabel lblProductosSeleccionados;
	private JLabel lblError;
	private JTextField textLocal;
	private JLabel lblLocal;
	private JLabel lblFecha;
	private JButton btnEliminar;
	private JLabel lblTotal;
	private JTextField textTotal;

	public PanelFacturas(ControladorPanelFacturas controladorPanelFacturas) {
		setBackground(SystemColor.activeCaption);

		this.controladorPanelFacturas = controladorPanelFacturas;

		setLayout(null);

		lblTextoPanel = new JLabel("PANEL FACTURAS");
		lblTextoPanel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTextoPanel.setBounds(0, 0, 450, 67);
		add(lblTextoPanel);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(400, 108, 106, 30);
		add(textFieldFecha);
		textFieldFecha.setText(this.controladorPanelFacturas.devolverFechaHora());
		textFieldFecha.setEditable(false);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(555, 537, 89, 23);
		add(btnVolver);

		textNIF = new JTextField();
		textNIF.setBounds(131, 64, 113, 20);
		add(textNIF);
		textNIF.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(131, 94, 113, 20);
		add(textNombre);
		textNombre.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(131, 125, 113, 20);
		add(textApellido);
		textApellido.setColumns(10);

		textFieldNumTrans = new JTextField();
		textFieldNumTrans.setBounds(131, 156, 113, 20);
		add(textFieldNumTrans);
		textFieldNumTrans.setColumns(10);
		textFieldNumTrans.setText(controladorPanelFacturas.leerNumTransBBDD());
		textFieldNumTrans.setEditable(false);
		textFieldNumTrans.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNIF = new JLabel("NIF");
		lblNIF.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNIF.setBounds(25, 63, 67, 20);
		add(lblNIF);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombre.setBounds(25, 95, 82, 17);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		lblApellido.setBounds(25, 126, 82, 17);
		add(lblApellido);

		JLabel lblTransaccion = new JLabel("Transaccion");
		lblTransaccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTransaccion.setBounds(25, 157, 98, 17);
		add(lblTransaccion);

		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(380, 240, 150, 160);
		add(scrollPaneProductos);

		listaProductos = new JList(controladorPanelFacturas.cogerListaProductos());
		listaProductos.setBackground(Color.WHITE);
		scrollPaneProductos.setViewportView(listaProductos);

		JScrollPane scrollPaneAnnadidos = new JScrollPane();
		scrollPaneAnnadidos.setBounds(30, 244, 296, 153);
		add(scrollPaneAnnadidos);

		listaAnnadidos = new JList(annadidos);
		listaAnnadidos.setBackground(Color.WHITE);
		scrollPaneAnnadidos.setViewportView(listaAnnadidos);

		btnAnnadir = new JButton("Seleccionar");
		btnAnnadir.setBounds(400, 442, 128, 30);
		add(btnAnnadir);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(412, 536, 117, 25);
		add(btnFinalizar);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1); // valor m�nimo
		formatter.setMaximum(99); // valor m�ximo
		formatter.setAllowsInvalid(false);
		// Si quieres comprobar que sea v�lido, cada vez que se pulse una tecla
		formatter.setCommitsOnValidEdit(true);

		textCantidad = new JFormattedTextField(formatter);
		textCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
		textCantidad.setBounds(479, 411, 51, 20);
		add(textCantidad);
		textCantidad.setText("1");

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCantidad.setBounds(390, 411, 82, 14);
		add(lblCantidad);

		lblProdDisp = new JLabel("Productos");
		lblProdDisp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProdDisp.setBounds(401, 211, 92, 22);
		add(lblProdDisp);

		lblProductosSeleccionados = new JLabel("Productos Seleccionados");
		lblProductosSeleccionados.setFont(new Font("Arial", Font.PLAIN, 15));
		lblProductosSeleccionados.setBounds(68, 211, 244, 22);
		add(lblProductosSeleccionados);

		lblError = new JLabel("");
		lblError.setBounds(533, 277, 277, 15);
		add(lblError);

		textLocal = new JTextField();
		textLocal.setBounds(400, 59, 106, 30);
		add(textLocal);
		textLocal.setColumns(10);
		textLocal.setText(controladorPanelFacturas.conseguirLocal());
		textLocal.setEditable(false);
		textLocal.setHorizontalAlignment(SwingConstants.CENTER);

		lblLocal = new JLabel("Local");
		lblLocal.setBounds(326, 67, 70, 15);
		lblLocal.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblLocal);

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(326, 115, 70, 15);
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblFecha);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(110, 408, 117, 25);
		add(btnEliminar);

		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(30, 457, 70, 15);
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		add(lblTotal);

		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(79, 453, 114, 19);
		add(textTotal);
		textTotal.setColumns(10);
		textTotal.setText("0");

		initializeEvents();

	}

	private void initializeEvents() {
		this.btnVolver.addActionListener(listenerBotonVolver(this.controladorPanelFacturas));
		this.btnAnnadir.addActionListener(listenerBotonAnnadir(this.controladorPanelFacturas));
		this.btnEliminar.addActionListener(listenerBotonEliminar(this.controladorPanelFacturas));
		this.btnFinalizar.addActionListener(listenerBotonFinalizar(this.controladorPanelFacturas));
	}

	private ActionListener listenerBotonVolver(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Volver");
				controladorPanelFacturas.accionadoBottonVolverPanelPrincipal();
			}
		};
	}

	private ActionListener listenerBotonAnnadir(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Annadir");
				boolean existeProd = false;
				String producto = "";
				String productoAnadir = "";
				String cantidad = textCantidad.getText();
				try {
					producto = (String) listaProductos.getSelectedValue();
					if(producto != null) {
						existeProd = true;
					}
					
				} catch (Exception e) {
					System.out.println("No se ha seleccionado un producto");
					lblError.setText("No se ha escogido un producto");
				}
				if (existeProd) {
					try {
						if (controladorPanelFacturas.existeProducto(producto) == -1) {
							productoAnadir = controladorPanelFacturas.accionadoBotonAnnadirProducto(producto);
							annadidos.addElement(controladorPanelFacturas.cantidadProducto(cantidad, productoAnadir));
							textTotal.setText(
									controladorPanelFacturas.cantidadTotal(cantidad, producto));
							lblError.setText("");
						} else 
						{
							String yaAnnadido = annadidos.get(controladorPanelFacturas.existeProducto(producto));
							annadidos.set(controladorPanelFacturas.existeProducto(producto), controladorPanelFacturas
									.cambiarCantidadProductos(yaAnnadido, Integer.parseInt(cantidad)));
							String total = controladorPanelFacturas.cantidadTotal(cantidad, producto);
							textTotal.setText(total);
						}
					} catch (Exception e) {
						System.out.println("El campo cantidad no contiene un entero");
						lblError.setText("No se ha introducido una cantidad");
					}
				}
			}
		};
	}

	private ActionListener listenerBotonFinalizar(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento Boton Finalizar");
				// Comprobamos si los campos DNI, Nombre, Apellido y si hay algun articulo metido  
				if (Double.parseDouble(textTotal.getText()) > 0 && 
						controladorPanelFacturas.comprobarNif(textNIF.getText()) &&
						controladorPanelFacturas.comprobarFormatoApellido(textNombre.getText()) &&
						controladorPanelFacturas.comprobarFormatoApellido(textApellido.getText())
						) {
					
					
					// insertar datos en actividad
					controladorPanelFacturas.insertarActividad(Integer.parseInt(textFieldNumTrans.getText()),
							controladorPanelFacturas.devolverFechaFormateada(textFieldFecha.getText()),
							Double.parseDouble(textTotal.getText()), textLocal.getText());
					
					// insertar datos en la tabla comprador la cual tiene transaccion, nombre y
					// apellido
					controladorPanelFacturas.insertarComprador(textNIF.getText(),
							textNombre.getText(), textApellido.getText());

					// insertar datos en la tabla factura la cual solo tiene transaccion y nif
					controladorPanelFacturas.insertarFactura(Integer.parseInt(textFieldNumTrans.getText()),
							textNIF.getText());

					

					// insertar datos de productos
					for (int i = 0; i < annadidos.getSize(); i++) {
						String textoRecogido = annadidos.get(i);
						String textoSpliteado[] = textoRecogido.split(" ");

						int cantidad = Integer.parseInt(textoSpliteado[0]);

						int transaccion = Integer.parseInt(textFieldNumTrans.getText());

						String producto = controladorPanelFacturas.devolverNombreProducto(i);
						double precioFinal = controladorPanelFacturas.cogerPrecioString(producto);

						controladorPanelFacturas.insertarProductoActividad(producto, transaccion, cantidad,
								precioFinal);
					}

					JOptionPane.showMessageDialog(null, "Ticket introducido correctamente");
					controladorPanelFacturas.accionadoBottonVolverPanelPrincipal();

				} else {
					if (Double.parseDouble(textTotal.getText()) == 0) {
						JOptionPane.showMessageDialog(null, "Debes introducir articulos");
					}
					if (controladorPanelFacturas.comprobarNif(textNIF.getText()) == false ) {
						JOptionPane.showMessageDialog(null, "El nif introducido es incorrecto");
					}
					if (controladorPanelFacturas.comprobarFormatoNombre(textNombre.getText()) == false) {
						JOptionPane.showMessageDialog(null, "El nombre no puede contener caracteres que no sean letras ni puede ser mayor de 20 caracteres ni menor que 3");
					}
					if (controladorPanelFacturas.comprobarFormatoApellido(textApellido.getText()) == false) {
						JOptionPane.showMessageDialog(null, "El Apellido no puede contener caracteres que no sean letras ni puede ser mayor de 25 caracteres ni menor que 2");
					}

				}

			}

		};
	}

	private ActionListener listenerBotonEliminar(ControladorPanelFacturas controladorPanelFacturas) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Ejecutando evento eliminar");
				try {
					int pos = listaAnnadidos.getSelectedIndex();
					String total = controladorPanelFacturas.accionadoBotonEliminar(pos, annadidos.get(pos));
					annadidos.remove(pos);
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
