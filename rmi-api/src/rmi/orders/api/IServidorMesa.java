package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IServidorMesa extends Remote {
	public String prueba() throws RemoteException;
	public int 		getIdPedido() 		throws RemoteException;
	public String 	getPersona() 		throws RemoteException;
	public String 	getDelivery() 		throws RemoteException;
	public int	 	getDni() 			throws RemoteException;
	public String 	getDireccion() 		throws RemoteException;
	public String 	getCelular() 		throws RemoteException;
	public boolean 	getPagoPendiente() 	throws RemoteException;
	public Date 	getFecha_Pedido() 	throws RemoteException;
	public String 	getEstadoPedido() 	throws RemoteException;
}
