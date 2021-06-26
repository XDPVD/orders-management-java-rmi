package com.ordersmanagement.cocina;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import rmi.orders.api.*;
import com.ordersmanagement.comun.*;


public class MainCliente {
	
	/*Deben utilizar la excepcion RemoteException cada vez que utilicen el objeto servidor*/
	
	private static IServidorCocina servidor;
	
	public static void main(String[] args) throws Exception {
		
		Registry registry = LocateRegistry.getRegistry(3333);
		servidor = (IServidorCocina)registry.lookup("TestServer");
		
	}
	
	
}
