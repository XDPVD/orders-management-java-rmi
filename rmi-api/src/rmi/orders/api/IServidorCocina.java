package rmi.orders.api;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import com.ordersmanagement.comun.*;

public interface IServidorCocina extends IServidor{

	// UPDATE *
	public int terminarPedido(int id_pedido) 
			throws RemoteException, SQLException;
	
	//SELECT * WHERE *
	public HashMap<Integer,PedidoCrearDTO> obtenerPedidosPendientes() 
			throws RemoteException, SQLException;

}
