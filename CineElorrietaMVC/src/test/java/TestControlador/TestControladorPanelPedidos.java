package TestControlador;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import Modelo.LineaPedido;
import Modelo.Modelo;
import Modelo.Producto;

//Mockito Runner para junit
//@RunWith(MockitoJUnitRunner.class)
public class TestControladorPanelPedidos {

	private Date fecha = new Date(0); 
	private Producto p1 = new Producto("Prod1",fecha, "Unproducto", 1, 3);
	private LineaPedido l1 = new LineaPedido(p1, 2, 10.5);
	
}
