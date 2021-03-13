package TestModelo;

import Modelo.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestLogin {
	
	private Login log = new Login("user", "contra");
	
	@Test
	public void testConstructorLogin() {
		assertEquals("user", log.getUsuario());
		assertEquals("contra", log.getContra());
	}
	
	@Test
	public void testSetUsuario() {
		log.setUsuario("usuario");
		assertEquals("usuario", log.getUsuario());
	}
	
	@Test
	public void testSetContra() {
		log.setContra("contrasena");
		assertEquals("contrasena", log.getContra());
	}
}
