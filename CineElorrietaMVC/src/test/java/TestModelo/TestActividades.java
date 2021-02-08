package TestModelo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import Modelo.*;

//@RunWith(MockitoJUnitRunner.class)

public class TestActividades { //Testea los m�todos no constructores, getters o setters de las clases que heredan de actividad (de momento solo esDomicilio())
	
	private Date fecha = new Date(0); //Fecha de tipo Date para insertar en las actividades
	private Pedido p1 = new Pedido(11, fecha, "Primero");
	private Pedido p2 = new Pedido(34, fecha, "Segundo", "Casa");
	private Actividad act = new Actividad(11, fecha, "Primero");
	private Actividad mockAct = mock(Actividad.class);
	
	@Test
	public void testDomicilio() {
		boolean res = p1.esDomicilio();
		assertFalse(res);
		res = p2.esDomicilio();
		assertTrue(res);
	}
	
	@Test
	public void testEquals() {
		when(mockAct.getNumTransaccion()).thenReturn(11);
		boolean res = act.equals(mockAct);
		assertTrue(res);
		res = act.equals(p1);
		assertTrue(res);
		res = act.equals(p2);
		assertFalse(res);
	}
	
}
