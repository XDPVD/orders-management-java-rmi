package rmi.orders.api;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import com.ordersmanagement.comun.*; 

public interface IServidorCaja extends IServidor {
	
	public List<PlatoDetallesDTO> obtenerPlatos() throws RemoteException, SQLException;
	
	public int crearPedido(PedidoCrearDTO nuevoPedido) throws RemoteException, SQLException;
	
}
