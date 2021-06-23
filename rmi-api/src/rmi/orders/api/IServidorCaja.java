package rmi.orders.api;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import com.ordersmanagement.comun.*; 

public interface IServidorCaja extends IServidor {
	
	public List<Plato> obtenerPlatos() throws RemoteException, SQLException;
	
	public void crearPedido(Pedido nuevoPedido) throws RemoteException, SQLException;
	
}
