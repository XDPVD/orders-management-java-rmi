package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IServidor extends Remote {
	public void 	conectar() 			throws RemoteException;
	public void 	desconectar() 		throws RemoteException;
	public void 	mostrarTablas() 	throws RemoteException, SQLException;
	public String 	enviarTablas() 		throws RemoteException, SQLException;
}
