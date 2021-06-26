package rmi.orders.cocinaClient;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import com.ordersmanagement.comun.*;

import rmi.orders.api.IServidorCocina;


public class MainCocina {
	
	private static IServidorCocina servidor;
	private static GUICocina gui;
	private static List<PedidoDetailsDTO> listaPedidos;
	private static int exito;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		gui = new GUICocina();
		
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == gui.getBtnActualizar()) {
					try {
						obtenerPedidosPendientes();			
						gui.addPedidos(listaPedidos);
					}
					catch(RemoteException | SQLException ex) {
						ex.printStackTrace();
					}
				}	
				if(e.getSource() == gui.getBtnTerminar()) {
					try {
						terminarPedido();
						obtenerPedidosPendientes();			
						gui.addPedidos(listaPedidos);
					}
					catch(RemoteException | SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
			
		};
		
		gui.getBtnActualizar().addActionListener(action);
		gui.getBtnTerminar().addActionListener(action);
		
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorCocina)registry.lookup("TestServer");
		
		obtenerPedidosPendientes();
		
		gui.addPedidos(listaPedidos);

	}
	
	public static void obtenerPedidosPendientes() throws RemoteException, SQLException{
		listaPedidos = servidor.obtenerPedidosPendientes();
	}
	
	public static void terminarPedido() throws RemoteException, SQLException{
		exito = servidor.terminarPedido(Integer.parseInt(gui.getTxtTerminar().getText()));
	}

}
