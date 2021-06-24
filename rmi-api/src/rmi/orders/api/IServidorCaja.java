package rmi.orders.api;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.*;

import com.ordersmanagement.comun.*; 

public interface IServidorCaja extends IServidor {
	
	/*Devuelve una lista de PlatoDetallesDTO (Para mas info buscar el .java respectivo)*/
	public List<PlatoDetallesDTO> obtenerPlatos() throws RemoteException, SQLException;
	
	/*Igualmente, PedidoCreaDTO, hay mas informacion en el .java respectivo*/
	public int crearPedido(PedidoCrearDTO nuevoPedido) throws RemoteException, SQLException;
	
}
