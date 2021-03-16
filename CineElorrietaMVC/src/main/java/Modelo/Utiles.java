package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utiles {
	
	private Modelo modelo;
	
	public Utiles(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public double cantidadTotal(String cantidad, String producto, double total) {
		ListaProductos listaProd = this.modelo.getListaProductos();
		int cantidadInt = Integer.parseInt(cantidad);
		double precioTotalProducto = cantidadInt * listaProd.precioProductoString(producto);
		total = total + precioTotalProducto;
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		return total;
	}
	
	public String annadirProducto(String producto) {
		ListaProductos listaProd = modelo.getListaProductos();
		Producto prod = listaProd.devolverProductoPorString(producto);
		ListaProductos listaTemporal = modelo.getListaTemporal();
		listaTemporal.addProducto(prod);
		return prod.toString();
	}
	
	public String annadirPlato(String plato) {
		ListaPlatos listaPlatos = modelo.getListaPlatos();
		Plato plat = listaPlatos.devolverPlatoPorString(plato);
		ListaPlatos listaTemporal = modelo.getListaTemporalPlatos();
		listaTemporal.addPlato(plat);
		return plat.toString();
	}
	
	public String cambioProductos(String producto, int cantidadAnadir) {
		int pos = 0;
		for (int i = 0; Character.isDigit(producto.charAt(i)); i++) {
			pos = i;
		}
		String cantString = producto.substring(0, pos + 1);
		int cantidad = Integer.parseInt(cantString);
		cantidad = cantidad + cantidadAnadir;
		String cambiada = cantidad + producto.substring(pos + 1);
		return cambiada;
	}
	
	public String devolverNombreProducto(int i) {
		ListaProductos listaTemporal = this.modelo.getListaTemporal();
		String[] lista = listaTemporal.getListaProductosString();
		return lista[i];
	}
	
	public String devolverNombrePlato(int i) {
		ListaPlatos listaTemporal = this.modelo.getListaTemporalPlatos();
		String[] lista = listaTemporal.getListaPlatosString();
		return lista[i];
	}

	public double eliminarProducto(int pos, String eliminar, double total) {
		ListaProductos listaProd = modelo.getListaTemporal();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaProd.getPrecioProducto(pos);
		total = total - (precio * cantidad);
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		listaProd.eliminarProducto(pos);
		return total;
	}
	
	public double eliminarPlato(int pos, String eliminar, double total) {
		ListaPlatos listaPlatos = modelo.getListaTemporalPlatos();
		int cantidad = modelo.cogerCantidadString(eliminar);
		double precio = listaPlatos.getPrecioPlato(pos);
		total = total - (precio * cantidad);
		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_DOWN);
	    total = bd.doubleValue();
		listaPlatos.eliminarPlato(pos);
		return total;
	}
	
	public boolean comprobarNif(String nif) {
		boolean correcto = false;
		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher matcher = pattern.matcher(nif);
		if (matcher.matches()) {
			correcto = true;
		} else {
			correcto = false;
		}
		return correcto;
	}

	public boolean comprobarFormatoNombre(String nombre) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(nombre) && nombre.length() <= 20) {
			if (nombre.length() >= 3) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public boolean comprobarFormatoApellido(String apellido) {
		// Comprobar tamano nombre y apellido
		// nombre es un varchar de 20, por ello comprobamos el length
		if (contieneSoloLetras(apellido) && apellido.length() <= 25) {
			if (apellido.length() >= 2) {
				return true;
			}
			return false;
		} else {

			return false;
		}
	}
	
	public boolean comprobarCamposString(String NIF, String nombre, String apellido) {
		if(comprobarNif(NIF) && comprobarFormatoNombre(nombre) && comprobarFormatoApellido(apellido)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean contieneSoloLetras(String cadena) {
		for (int x = 0; x < cadena.length(); x++) {
			char c = cadena.charAt(x);
			// Si no est� entre a y z, ni entre A y Z, ni es un espacio
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}
		return true;
	}
	
	public String devolverFechaFormateada(String input) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateInString = input;
		try {
			java.util.Date date1 = formatter.parse(dateInString);
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	public String[] accionadoBotonAnnadirProducto(String producto, String cantidad, double total) {
		String[] devolver = new String[2];
		devolver[0] = cantidad + " " + annadirProducto(producto);
		devolver[1] = String.valueOf(cantidadTotal(cantidad, producto, total));
		return devolver;
	}

	public String[] cambiarCantidadProductos(String nombreProductoAnadido, int cantidadAnadir, String nombreProducto, double total) {
		String[] devolver = new String[2];
		devolver[0] = cambioProductos(nombreProductoAnadido, cantidadAnadir);
		devolver[1] = String.valueOf(cantidadTotal(Integer.toString(cantidadAnadir), nombreProducto, total));
		return devolver;
	}
	
	public String[] accionadoBotonAnnadirPlato(String plato, String cantidad, double total) {
		String[] devolver = new String[2];
		devolver[0] = cantidad + " " + annadirPlato(plato);
		devolver[1] = String.valueOf(cantidadTotal(cantidad, plato, total));
		return devolver;
	}
}
