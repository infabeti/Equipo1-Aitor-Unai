package TestModelo;

import Modelo.*;
import java.sql.Connection;
import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

public class TestConexion {
	
	private Conexion con = new Conexion();
	
	@Test
	public void testDesconectar() {
		con.desconectar();
		Connection connection = con.getConn();
		assertNull(connection);
	}
	
	@Test
	public void testLogin() {
		Usuario user = con.login("12312122S", "Zampon");
		assertEquals("Jon", user.getNombre());
		assertEquals("Bar Buenavista", user.getLocal());
		assertEquals("BAR", user.getTipoLocal());
		assertEquals("12345678H", user.getNifLocal());
	}
	
	@Test
	public void testLoginFallido() {
		Usuario user = con.login("asdf", "asdf");
		assertEquals("", user.getNombre());
		assertEquals("", user.getLocal());
		assertEquals("", user.getTipoLocal());
		assertEquals("", user.getNifLocal());
	}
	
	@Test
	public void testLeerNumTrans() {
		int test = con.leerNumTransBBDD();
		//assertEquals(1, test);
	}
	
	@Test
	public void testObtenerCodigoAlimento() {
		String resultado = con.obtenerCodigoAlimentoProducto("Huevo");
		assertEquals("1", resultado);
	}
	
	@Test
	public void testObtenerCodigoAlimentoFail() {
		String resultado = con.obtenerCodigoAlimentoProducto("ASDF");
		assertNull(resultado);
	}
	
	@Test
	public void testExisteDNI() {
		boolean test = con.existeDNI("12312122S");
		assertTrue(test);
	}
	
	@Test
	public void testExisteDNIFalse() {
		boolean test = con.existeDNI("ASDF");
		assertFalse(test);
	}
	
	@Test
	public void testCogerProductosLocal() {
		ListaProductos lp = con.cogerProductosLocal("12345678H");
		assertNotNull(lp);
	}
	
	
}
