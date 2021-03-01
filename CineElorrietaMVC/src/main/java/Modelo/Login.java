package Modelo;

public class Login {

	private String usuario,contra;
	
	public Login(String user, String contra) {
		this.usuario = user;
		this.contra = contra;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}
	
	
	
}
