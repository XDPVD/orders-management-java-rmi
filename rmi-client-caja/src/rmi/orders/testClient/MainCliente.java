package rmi.orders.testClient;

import com.ordersmanagement.comun.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import rmi.orders.api.*;
import com.ordersmanagement.comun.*;


public class MainCliente{
	
	/*Deben utilizar la excepcion RemoteException cada vez que utilicen el objeto servidor*/
	
	private static IServidorCaja servidor;
	private static PedidoCrearDTO pedido;
	private static LineaPedidoDTO lineaPedido;
	private static GUICliente gui;
	private static int exitoPedido;
	
	public static void main(String[] args) throws Exception {
		
		gui = new GUICliente();
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == gui.getBtnAddOrder()) {
					pedido = gui.getPedidoGUI();
					lineaPedido = gui.getLineaPedidoGUI();
					pedido.anadirLineaPedido(lineaPedido);
				}
				
				if(e.getSource() == gui.getBtnSendOrder()) {
					try {
						if(pedido != null) {
							crearPedido();
							System.out.print(exitoPedido);	
						}
					}
					catch(RemoteException | SQLException ex) {
						ex.printStackTrace();
					}
				}
				
			}
			
		};
		
		gui.addAL(action);
		
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorCaja)registry.lookup("TestServer");
		
	}
	

	
	public static void crearPedido() throws RemoteException, SQLException{
		exitoPedido = servidor.crearPedido(pedido);
	}
	
	
}
