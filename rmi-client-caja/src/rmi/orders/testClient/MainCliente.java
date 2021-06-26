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
import java.util.ArrayList;
import java.util.Iterator;

public class MainCliente{
	
	/*Deben utilizar la excepcion RemoteException cada vez que utilicen el objeto servidor*/
	
	private static IServidorCaja servidor;
	private static PedidoCrearDTO pedido;
	private static List<LineaPedidoDTO> lineasPedido = new ArrayList<LineaPedidoDTO>();
	private static List<PlatoDetallesDTO> listaPlatos = new ArrayList<PlatoDetallesDTO>();
	private static GUICliente gui;
	private static int exitoPedido;
	
	public static void main(String[] args) throws Exception {
		
		gui = new GUICliente();
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == gui.getBtnAddOrder()) {
					lineasPedido.add(gui.getLineaPedidoGUI());
					gui.showOrderLine();
				}
				
				if(e.getSource() == gui.getBtnSendOrder()) {
					pedido = gui.getPedidoGUI();
					pedido.setLineasPedido(lineasPedido);
					try {
						if(pedido != null) {
							crearPedido();
							System.out.print(exitoPedido);
							
						}
						gui.clearFieldsOrderLine();
						lineasPedido.clear();
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
		
		obtenerPlatos();
		gui.showListOfFood(listaPlatos);
		
	}
	

	
	public static void crearPedido() throws RemoteException, SQLException{
		exitoPedido = servidor.crearPedido(pedido);
	}
	
	public static void obtenerPlatos() throws RemoteException, SQLException{
		listaPlatos = servidor.obtenerPlatos();
	}
}
