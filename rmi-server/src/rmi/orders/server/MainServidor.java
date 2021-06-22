package rmi.orders.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.orders.api.IServidor;
import rmi.orders.api.Utils;

public class MainServidor {
	public static void main(String[] args) throws Exception {
		Utils.setCodeBase(IServidor.class);
		
		Servidor servidor = new Servidor();
		
		IServidor remote = (IServidor)UnicastRemoteObject.exportObject(servidor,3333);
		
		Registry registry = LocateRegistry.createRegistry(3333);
		
		registry.rebind("TestServer", remote);
		
		System.out.println("Servidor en marcha");
		System.in.read();
		
		registry.unbind("TestServer");
		UnicastRemoteObject.unexportObject(servidor, true);
		System.out.println("Detenido");
	}
}
