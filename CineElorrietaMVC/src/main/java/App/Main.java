package App;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Controlador.Controlador;
import Modelo.Modelo;
import Modelo.Producto;
import Vista.Vista;

public class Main {
	
	private static Modelo modelo;
	private static Vista vista;
	@SuppressWarnings("unused")
	private static Controlador controlador;
	
	public static void main(String[] args) {
		modelo = new Modelo();    
		vista = new Vista();              
		controlador = new Controlador(modelo, vista);
		
	}

			
			

}
