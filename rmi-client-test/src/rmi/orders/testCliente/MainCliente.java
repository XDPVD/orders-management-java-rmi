package rmi.orders.testCliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import rmi.orders.api.IServidorCaja;
import rmi.orders.api.IServidorMesa;


public class MainCliente {
	private static IServidorCaja servidor;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorCaja)registry.lookup("TestServer");
		
		metodoPrueba();
	}
	
	
	private static void metodoPrueba() throws RemoteException {
		servidor.conectar();
		try {
			servidor.obtenerPlatos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servidor.desconectar();
	}
}
