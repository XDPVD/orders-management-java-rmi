package rmi.orders.server;

import java.rmi.RemoteException;

import rmi.orders.api.IServidor;

public class Servidor implements IServidor {

	@Override
	public String prueba() throws RemoteException {
		return "Esto es una prueba";
	}

	

}
