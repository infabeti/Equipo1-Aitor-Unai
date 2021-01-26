package TestControlador;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import Modelo.LineaPedido;
import Modelo.Producto;

public class TestControladorPanelPedidos {

	private Date fecha = new Date(0); 
	private Producto p1 = new Producto("Prod1",fecha, "Unproducto", 1, 3);
	private LineaPedido l1 = new LineaPedido(p1, 2, 10.5);
	
	@Test
	public void TestaccionadoBotonAnnadirProducto()
	{
		System.out.println(l1.toString());
		assertEquals(l1.toString(), "2 x Prod1 10.5€");
		
	}
	
	@Test
	public void TestdevolverProducto() {
		
		
		
	}
	
	
}
