package Modelo;

import java.sql.Date;
import java.util.Arrays;

public class ListaActividades {
	
	private int punt = 0;
	private Actividad[] listaActividades = new Actividad[256];
	private final Date FECHADEFAULT = new Date(0);
	private final Actividad DEFAULT = new Actividad(0, FECHADEFAULT, "DEFAULT");
	
	public ListaActividades() {
		Arrays.fill(listaActividades, DEFAULT);
	}
	
	public boolean annadirActividad(Actividad act) {
		boolean ok = false;
		try {
			listaActividades[punt] = act;
			punt++;
			ok = true;
		}
		catch(Exception e) {
			System.out.println("No se ha podido insertar la actividad en la lista");
		}
		return ok;
	}
	
	public Actividad buscarTransaccion(int numTransaccion) {
		Actividad act = new Actividad();
		boolean ok = false;
		for(int i = 0; i<punt;i++) {
			if(numTransaccion == listaActividades[i].getNumTransaccion()) {
				act = listaActividades[i];
				ok = true;
			}
		}
		if(!ok) {
			act = null;
		}
		return act;
	}
	
	public boolean eliminarTransaccion(int numTransaccion) {
		boolean ok = false;
		int puntero = 0;
		for(int i = 0; i<punt;i++) {
			if(numTransaccion == listaActividades[i].getNumTransaccion()) {
				puntero = i;
				ok = true;
				i = listaActividades.length;
			}
		}
		if(ok) {
			for(int i = puntero; i<punt-1;i++) {
				listaActividades[i] = listaActividades[i+1];
			}
			listaActividades[punt] = DEFAULT;
			punt--;
		}
		return ok;
	}
	
}
