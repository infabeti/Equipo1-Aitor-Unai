package TestModelo;

import static org.junit.Assert.*;
import org.junit.Test;

import Modelo.Modelo;



public class ModeloTest {
	
	private Modelo modelo = new Modelo();

	@Test
	public void testUsuarioLocal() {
		modelo.usuarioLocal("Uno", "Hola", 'r');
		String usuario = modelo.getUsuario();
		String local = modelo.getLocal();
		char tipoLocal = modelo.getTipoLocal();
		String tipoLocalString = modelo.getTipoLocalCompleto();
		assertEquals("Uno", usuario);
		assertEquals("Hola", local);
		assertEquals('r', tipoLocal);
		assertEquals("Restaurante", tipoLocalString);
	}

}
