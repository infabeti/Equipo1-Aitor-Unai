package Modelo;


public class Carro_Stock {
	
	private int CodigoAlimento ;
	private int cantidad;

	
	public Carro_Stock(int CodigoAlimento , int cantidad) { 
		this.CodigoAlimento  = CodigoAlimento ;
		this.cantidad = cantidad;
	}
	
	public int getCodigoAlimento () {
		return CodigoAlimento ;
	}
	
	public void setCodigoAlimento (int CodigoAlimento ) {
		this.CodigoAlimento  = CodigoAlimento ;
	}
	
	public int getcantidad() {
		return cantidad;
	}
	
	public void setcantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public String toString() {
		return "Todo el Stock--> [CodigoAlimento --> " + CodigoAlimento  + ", "
									+ "Cantidad --> " + cantidad + "]";
	}
	
}