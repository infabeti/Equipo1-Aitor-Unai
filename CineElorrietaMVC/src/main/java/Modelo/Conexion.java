package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String nombreBd="reto3";
	private String usuario="root";
	private String password="elorrieta";
	private String url="jdbc:mysql://localhost:33060/"+nombreBd+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	Connection conn=null;
	//constructor de la clase
	public Conexion(){
		try {
			//obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn==null) {
				System.out.println("******************NO SE PUDO CONECTAR "+nombreBd);
				System.exit(0);
			}
			else
			{
				System.out.println("Conectado correctamente a la base de datos " + nombreBd + " con el usuario " + usuario);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql esté encendido...");
			System.exit(0);
		}
	}
	public Connection getConnection(){
		return conn;
		
	}
	public void desconectar(){
		conn=null;
		System.out.println("Desconexion realizada correctamente");
	}
}
	

