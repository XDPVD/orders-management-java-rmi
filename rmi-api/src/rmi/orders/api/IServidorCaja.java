package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

import com.ordersmanagement.comun.*; 

public interface IServidorCaja extends Remote{
	
	public List<Plato> obtenerPlatos();
	
	public void crearPedido(Pedido nuevoPedido);
	
}
