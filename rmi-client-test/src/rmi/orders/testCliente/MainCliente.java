package rmi.orders.testCliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import rmi.orders.api.IServidorCaja;
import rmi.orders.api.IServidorCocina;
import rmi.orders.api.IServidorMesa;


public class MainCliente {
	private static IServidorCocina servidor;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorCocina)registry.lookup("TestServer");
		
		metodoPrueba();
	}
	
	
	private static void metodoPrueba() throws RemoteException {
		servidor.conectar();
		try {
			servidor.obtenerPedidosPendientes();
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
