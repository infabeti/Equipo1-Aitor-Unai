package TestModelo;

import Modelo.Local;
import Modelo.Actividad;
import Modelo.ListaActividades;
import java.sql.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestLocal {
	
	
	private Date fecha = new Date(0); //Fecha de tipo Date para insertar en las actividades
	private Actividad a1 = new Actividad(11, fecha, "Primero");
	private Actividad a2 = new Actividad(34, fecha, "Segundo");
	private ListaActividades lista = new ListaActividades();
	private Local loc = new Local(lista, "46564187J", "Primero", "Calle Pepito", "Restaurante");
	
	@Test
	public void testAnnadir() {
		boolean test = loc.addActividad(a1);
		assertTrue(test);
		test = loc.addActividad(a2);
		assertTrue(test);
		//A partir de aquí se intenta hacer un test sobre la excepción
		int i = 2;
		while(i<256) {
			loc.addActividad(a1);
			i++;
		}
		test = loc.addActividad(a1);
		assertFalse(test);
		//Funciona
	}
	
	@Test
	public void testBuscar() {
		loc.addActividad(a1);
		Actividad test = loc.buscarActividad(11);
		assertEquals(a1.getNumTransaccion(), test.getNumTransaccion());
		test = loc.buscarActividad(98);
		assertNull(test);
	}
	
	@Test
	public void testEliminar() {
		loc.addActividad(a1);
		boolean test = loc.eliminarActividad(11);
		assertTrue(test);
		test = loc.eliminarActividad(11);
		assertFalse(test);
		Actividad testact = loc.buscarActividad(11);
		assertNull(testact);
		loc.addActividad(a1);
		loc.addActividad(a2);
		test = loc.eliminarActividad(11);
		assertTrue(test);
	}
	
}
