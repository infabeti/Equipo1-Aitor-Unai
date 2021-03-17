package Modelo;

public class SentenciasBBDD {

	// CONSULTAS BBDD
		protected final String CONSULTALOGUEAR = "Select e.nombre, es.nombre, tipoNegocio, e.NIF from empleado e join establecimiento es on e.NIF = es.NIF where dni=? and contrasena=?";
		protected final String CONSULTANIF = "Select nif from empleado where NIF=?";
		protected final String CONSULATDNI = "Select dni from empleado where dni=?";
		protected final String CONSULTAACTIVIDAD = "select * from actividad;";
		protected final String CONSULTAALIMENTO = "select * from alimento ;";
		protected final String INSERTARACTIVIDAD = "insert into actividad " + "values(?,?,?,?,?);";
		protected final String INSERTAREMPLEADO = "insert into empleado " + "values(?, ?, ?, ?, ?)";
		protected final String INSERTARPRODUCTOACTIVIDAD = "insert into lineaproducto (codigoalimento,transaccion,cantidad,preciofinal)" + "values(?,?,?,?);";
		protected final String CONSULTAPRODUCTOLOCAL = "Select a.Nombre, a.PCompra, p.PVenta, a.Tipo, a.FeCad from alimento a join producto p on a.CodigoAlimento = p.CodigoAlimento join stock s on a.CodigoAlimento = s.CodigoAlimento where s.NIF=?";
		protected final String INSERTARPEDIDO = "insert into pedido " + "values(?, ?)";
		protected final String INSERTARFACTURA = "insert into factura " + "values(?,?);";
		protected final String INSERTARCOMPRADOR = "insert into comprador " + "values(?,?,?);";
		protected final String EXISTECOMPRADOR = "select * from comprador where NIF=?;";
		protected final String CONSEGUIRCANTIDADSTOCK = "select Cantidad from stock where NIF =? and CodigoAlimento = ?";
		protected final String REPLACESTOCK = "replace into stock " + "values(?, ?, ?)";
		protected final String INSERTARCOMANDA = "insert into comanda " + "values(?)";
		protected final String CONSULTAPLATO = "select * from plato ;";
		protected final String ALIMENTOORDENADO = "Select a.Nombre, a.PCompra, a.Tipo, a.FeCad from alimento a order by a.CodigoAlimento asc";
		protected final String PLATOJOINCARTA = "Select p.Nombre, p.pvp from plato p join carta c on p.codigoplato = c.codigoplato where c.nif=?";
		
		
		static final String NIF = "SELECT NIF FROM empleado where dni = ";
		static final String Transaccion="SELECT max(Transaccion) from actividad";
		static final String CodigoAlimento = "SELECT cantidad from stock where CodigoAlimento =";
		static final String ComprobarStock = "select CodigoAlimento ,Transaccion from lineaproducto where CodigoAlimento = ";
		static final String actividad = "INSERT INTO actividad VALUES";
		static final String Stock = "INSERT INTO lineaproducto VALUES";
		static final String actualizarStock = "update lineaproducto set N_Unidades =";
		static final String actualizacion50 = "update stock set cantidad = cantidad + 50 where CodigoAlimento = ";
}

	