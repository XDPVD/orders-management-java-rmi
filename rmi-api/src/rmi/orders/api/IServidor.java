package rmi.orders.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidor extends Remote {
	public String prueba() throws RemoteException;
}
