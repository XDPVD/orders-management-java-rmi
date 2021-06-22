package rmi.orders.clCaja;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import rmi.orders.api.IServidor;


public class MainCliente {
	private static IServidor servidor;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidor)registry.lookup("TestServer");
		
		metodoPrueba();
	}
	
	
	private static void metodoPrueba() throws RemoteException {
		System.out.println(servidor.prueba());
	}
}
