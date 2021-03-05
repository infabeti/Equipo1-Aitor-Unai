package TestModelo;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

import Modelo.Modelo;
import Modelo.Usuario;

public class ModeloTest {
	
	private Modelo modelo;
	private Usuario usuarioMock = mock(Usuario.class);
	
	@Test
	public void constructorModeloTest() {
		modelo = new Modelo();
	}


}
