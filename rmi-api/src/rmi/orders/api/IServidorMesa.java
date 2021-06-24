package rmi.orders.api;

import java.rmi.RemoteException;
import java.sql.SQLException;

import com.ordersmanagement.comun.*;

import java.util.*;

public interface IServidorMesa extends IServidor{
	
	/*
	 * Cabe decir que la id del pedido debe estar almacenada en la interfaz, con el fin de ejecutar las acciones terminarPedido(id) y
	 * archivarPedido(id)
	 * */
	
	/*Lista de pedidos con el estado terminado y con el atributo ARCHIVO igual a 0*/
	public List<PedidoDetailsDTO> obtenerPedidosTerminados()  
			throws RemoteException, SQLException;
	
	/*Cambia el atributo de ARCHIVADO a 1 para aquel pedido que tenga el valor "id" como id_pedido*/
	public void archivarPedido(int id) throws RemoteException, SQLException;
	
}
