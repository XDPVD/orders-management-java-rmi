package rmi.orders.testClient;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import rmi.orders.api.*;
import com.ordersmanagement.comun.*;


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
			
			//List<PlatoDetallesDTO> platos = servidor.obtenerPlatos();
			
			PedidoCrearDTO elem = new PedidoCrearDTO("Diego Vilca", false);
			elem.anadirLineaPedido(new LineaPedidoDTO(10, 3));
			System.out.println(servidor.crearPedido(elem));
			
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
