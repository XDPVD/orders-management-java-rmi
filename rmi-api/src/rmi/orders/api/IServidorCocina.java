package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IServidorCocina extends Remote{
	public int 		getIdPedido() 		throws RemoteException;
	public String 	getPersona() 		throws RemoteException;
	public boolean 	getDelivery() 		throws RemoteException;
	public Date 	getFechaPedido() 	throws RemoteException;
	public String 	getEstadoPediente() throws RemoteException;
	public int 		getIdComida() 		throws RemoteException;
	public int		getCantidad()		throws RemoteException;
}
