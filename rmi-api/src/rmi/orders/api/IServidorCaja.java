package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorCaja extends Remote{
	public void getListaComida() 	throws RemoteException; //Esto debe ser public void List<Comida> getListaComida()
	
	public void setIdComida() 		throws RemoteException;
	public void setCantidadComida() throws RemoteException;
	public void setLineaPedido() 	throws RemoteException;
	
	public void setPersona() 		throws RemoteException;
	public void setDelivery() 		throws RemoteException;
	public void setDni() 			throws RemoteException;
	public void setDireccion() 		throws RemoteException;
	public void setCelular() 		throws RemoteException;
	public void setPagoPendiente() 	throws RemoteException;
	public void setFechaPedido() 	throws RemoteException;
	public void setPedido() 		throws RemoteException;
}
