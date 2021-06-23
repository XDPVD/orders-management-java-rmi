package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

import com.ordersmanagement.comun.*;

import java.util.*;

public interface IServidorMesa extends Remote {
	
	public void 	conectar() 			throws RemoteException;
	public void 	desconectar() 		throws RemoteException;
	public void 	mostrarTablas() 	throws RemoteException, SQLException;
	public String 	enviarTablas() 		throws RemoteException, SQLException;
	public HashMap<Integer,Pedido> obtenerPedidosTerminados();
	
}
