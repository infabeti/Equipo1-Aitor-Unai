package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String NOMBREBD="reto3";
	private static final String USUARIO="root";
	private static final String PASSWORD="elorrieta";
	private static final String URL="jdbc:mysql://localhost:33060/"+NOMBREBD+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	Connection conn=null;
	//constructor de la clase
	public Conexion(){
		try {
			//obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(URL,USUARIO,PASSWORD);
			if (conn==null) {
				System.out.println("******************NO SE PUDO CONECTAR "+NOMBREBD);
				System.exit(0);
			}
			else
			{
				System.out.println("Conectado correctamente a la base de datos " + NOMBREBD + " con el usuario " + USUARIO);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql est� encendido...");
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
	

