package rmi.orders.api;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import com.ordersmanagement.comun.*;

public interface IServidorCocina extends IServidor{

	/*
	 * Cabe decir que la id del pedido debe estar almacenada en la interfaz, con el fin de ejecutar las acciones terminarPedido(id) y
	 * archivarPedido(id)
	 * */
	
	
	/*Accion que cambia el estado de un pedido (pendiente -> terminado)*/
	public int terminarPedido(int id_pedido) 
			throws RemoteException, SQLException;
	
	/*Lista de pedidos que estan con el estado pendiente +info en el .java de pedidoDetailsDTO*/
	public List<PedidoDetailsDTO> obtenerPedidosPendientes() 
			throws RemoteException, SQLException;

}
