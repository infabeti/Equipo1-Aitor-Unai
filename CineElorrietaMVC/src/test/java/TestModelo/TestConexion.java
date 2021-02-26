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
}
