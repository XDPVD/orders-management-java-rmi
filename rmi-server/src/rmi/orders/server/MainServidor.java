package rmi.orders.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import rmi.orders.api.IServidorMesa;
import rmi.orders.api.Utils;

public class MainServidor {
	public static void main(String[] args) throws Exception {
		Utils.setCodeBase(IServidorMesa.class);
		
		Servidor servidor = new Servidor();
		
		IServidorMesa remote = (IServidorMesa)UnicastRemoteObject
				.exportObject(servidor,3333);
		
		Registry registry = LocateRegistry.createRegistry(3333);
		
		registry.rebind("TestServer", remote);
		
		System.out.println("Servidor en marcha");
		System.in.read();
		
		registry.unbind("TestServer");
		UnicastRemoteObject.unexportObject(servidor, true);
		System.out.println("Detenido");
	}
}
