package TestModelo;

import Modelo.*;
import java.sql.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestActividades { //Testea los métodos no constructores, getters o setters de las clases que heredan de actividad (de momento solo esDomicilio())
	
	private Date fecha = new Date(0); //Fecha de tipo Date para insertar en las actividades
	private Pedido p1 = new Pedido(11, fecha, "Primero");
	private Pedido p2 = new Pedido(34, fecha, "Segundo", "Casa");
	
	@Test
	public void testDomicilio() {
		boolean res = p1.esDomicilio();
		assertFalse(res);
		res = p2.esDomicilio();
		assertTrue(res);
	}
	
}
