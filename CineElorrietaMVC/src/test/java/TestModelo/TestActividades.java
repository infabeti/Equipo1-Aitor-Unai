package TestModelo;

import Modelo.*;
import java.sql.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestActividades { //Testea los m�todos no constructores, getters o setters de las clases que heredan de actividad (de momento solo esDomicilio())
	
	private Date fecha = new Date(0); //Fecha de tipo Date para insertar en las actividades
	private Pedido p1 = new Pedido(11, fecha, "Primero");
	private Pedido p2 = new Pedido(34, fecha, "Segundo", "Casa");
	private Actividad act = new Actividad(11, fecha, "Primero");
	private Actividad act2 = new Actividad(11);
	
	@Test
	public void testDomicilio() {
		boolean res = p1.esDomicilio();
		assertFalse(res);
		res = p2.esDomicilio();
		assertTrue(res);
	}
	
	@Test
	public void testEquals() {
		boolean res = act.equals(act2);
		assertTrue(res);
		res = act.equals(p1);
		assertTrue(res);
		res = act.equals(p2);
		assertFalse(res);
	}
	
}
