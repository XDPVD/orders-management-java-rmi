package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

import com.ordersmanagement.comun.*;

public interface IServidorCocina extends Remote{

	public int terminarPedido(int id_pedido);
	
	public HashMap<Integer,Pedido> obtenerPedidosPendientes();
	
}
