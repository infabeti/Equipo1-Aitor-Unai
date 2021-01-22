package Modelo;

public class Local {
	
	private String NIF;
	private String nombre;
	private String direccion;
	private String tipoNegocio;
	private ListaActividades listaAct;
	
	public Local() {}
	
	public Local(ListaActividades listaAct, String NIF, String nombre, String direccion, String tipoNegocio) {
		this.listaAct = listaAct;
		this.NIF = NIF;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tipoNegocio = tipoNegocio;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipoNegocio() {
		return tipoNegocio;
	}

	public void setTipoNegocio(String tipoNegocio) {
		this.tipoNegocio = tipoNegocio;
	}

	public ListaActividades getListaAct() {
		return listaAct;
	}

	public void setListaAct(ListaActividades listaAct) {
		this.listaAct = listaAct;
	}
	
	public boolean addActividad(Actividad act) {
		boolean ok = listaAct.annadirActividad(act);
		return ok;
	}
	
	public Actividad buscarActividad(int numTransaccion) {
		return listaAct.buscarTransaccion(numTransaccion);
	}
	
	public boolean eliminarActividad(int numTransaccion) {
		boolean ok = listaAct.eliminarTransaccion(numTransaccion);
		return ok;
	}
	
}
