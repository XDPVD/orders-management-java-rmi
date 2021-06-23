package rmi.orders.api;

import java.rmi.RemoteException;
import java.sql.SQLException;

import com.ordersmanagement.comun.*;

import java.util.*;

public interface IServidorMesa extends IServidor{
	
	// SELECT * WHERE *
	public HashMap<Integer,PedidoCrearDTO> obtenerPedidosTerminados()  
			throws RemoteException, SQLException;
	
	public void marcarPedidos() throws RemoteException, SQLException;
	
}
