package rmi.orders.testCliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import rmi.orders.api.IServidorMesa;


public class MainCliente {
	private static IServidorMesa servidor;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorMesa)registry.lookup("TestServer");
		
		metodoPrueba();
	}
	
	
	private static void metodoPrueba() throws RemoteException {
		System.out.println(servidor.prueba());
	}
}
