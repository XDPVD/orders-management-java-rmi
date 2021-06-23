package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import com.ordersmanagement.comun.*;

import java.util.*;

public interface IServidorMesa extends Remote {
	
	public HashMap<Integer,Pedido> obtenerPedidosTerminados();
	
}
